package com.maple.rimaproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maple.rimaproject.R;
import com.maple.rimaproject.data.SharedPreferenceHelper;
import com.maple.rimaproject.data.StaticConfig;
import com.maple.rimaproject.model.Consersation;
import com.maple.rimaproject.model.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseStorage storage;
    StorageReference storageReference,imageRef;
    private static final int Selected = 100;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    Uri uriImage;

    ImageButton PickImage;
    TextView YourUrlImage;

    private RecyclerView recyclerChat;
    public static final int VIEW_TYPE_USER_MESSAGE = 0;
    public static final int VIEW_TYPE_FRIEND_MESSAGE = 1;
    private ListMessageAdapter adapter;
    private String roomId;
    private ArrayList<CharSequence> idFriend;
    private Consersation consersation;
    private ImageButton btnSend;
    private EditText editWriteMessage;
    private LinearLayoutManager linearLayoutManager;
    public static HashMap<String, Bitmap> bitmapAvataFriend;
    public Bitmap bitmapAvataUser;
    private Button btnChoose, btnUpload;


    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        PickImage = findViewById(R.id.attach);


        PickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        Intent intentData = getIntent();
        idFriend = intentData.getCharSequenceArrayListExtra(StaticConfig.INTENT_KEY_CHAT_ID);
        roomId = intentData.getStringExtra(StaticConfig.INTENT_KEY_CHAT_ROOM_ID);
        String nameFriend = intentData.getStringExtra(StaticConfig.INTENT_KEY_CHAT_FRIEND);

        consersation = new Consersation();
        btnSend = (ImageButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        String base64AvataUser = SharedPreferenceHelper.getInstance(this).getUserInfo().avata;
        if (!base64AvataUser.equals(StaticConfig.STR_DEFAULT_BASE64)) {
            byte[] decodedString = Base64.decode(base64AvataUser, Base64.DEFAULT);
            bitmapAvataUser = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } else {
            bitmapAvataUser = null;
        }

        editWriteMessage = (EditText) findViewById(R.id.editWriteMessage);
        if (idFriend != null && nameFriend != null) {
//            getSupportActionBar().setTitle(nameFriend);
            linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerChat = (RecyclerView) findViewById(R.id.recyclerChat);
            recyclerChat.setLayoutManager(linearLayoutManager);
            adapter = new ListMessageAdapter(this, consersation, bitmapAvataFriend, bitmapAvataUser);
//            FirebaseDatabase.getInstance().getReference().child("message/"+ roomId).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.getValue() != null) {
//
//                        HashMap mapMessage = (HashMap) dataSnapshot.getValue();
//                        Message newMessage = new Message();
//
//
//                        newMessage.idSender = (String) mapMessage.get("idSender");
//                        newMessage.idReceiver = (String) mapMessage.get("idReceiver");
//                        newMessage.text = (String) mapMessage.get("text");
//                        newMessage.type = (String) mapMessage.get("type");
//                        newMessage.timestamp = (long) mapMessage.get("timestamp");
//                        consersation.getListMessageData().add(newMessage);
//                        adapter.notifyDataSetChanged();
//                        linearLayoutManager.scrollToPosition(consersation.getListMessageData().size() - 1);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
            FirebaseDatabase.getInstance().getReference().child("message/" + roomId).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (dataSnapshot.getValue() != null) {

                        HashMap mapMessage = (HashMap) dataSnapshot.getValue();
                        Message newMessage = new Message();



                        newMessage.idSender = (String) mapMessage.get("idSender");
                        newMessage.idReceiver = (String) mapMessage.get("idReceiver");
                        newMessage.text = (String) mapMessage.get("text");
                        newMessage.type = (String) mapMessage.get("type");
                        newMessage.timestamp = (long) mapMessage.get("timestamp");
                        consersation.getListMessageData().add(newMessage);
                        for (int i=0; i < consersation.getListMessageData().size(); i++){
                            Log.e("sdfsdfsdf", consersation.getListMessageData().get(i).text);
//                            Log.e("sdfsdfsdf", consersation.getListMessageData().get(i).type);
                        }
                        adapter.notifyDataSetChanged();
                        linearLayoutManager.scrollToPosition(consersation.getListMessageData().size() - 1);
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            recyclerChat.setAdapter(adapter);
        }
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
       
   
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Log.e("onActivityResult", "onActivityResult: "+bitmap );
                uploadImage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());

            ref.putFile(filePath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        Uri downUri = task.getResult();
                        String content = downUri.toString();
                        if (content.length() > 0) {
                            editWriteMessage.setText("");
                            Message newMessage = new Message();
                            newMessage.text = content;
                            newMessage.type = "image";
                            newMessage.idSender = StaticConfig.UID;
                            newMessage.idReceiver = roomId;
                            newMessage.timestamp = System.currentTimeMillis();

                            FirebaseDatabase.getInstance().getReference().child("message/" + roomId).push().setValue(newMessage);
                        }

                        Log.d("sdfsdfdsf", "onComplete: Url: "+ downUri.toString());
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Log.e("onFailure", "onFailure: "+e.getMessage() );
                            Toast.makeText(ChatActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

//            ref.putFile(filePath)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            progressDialog.dismiss();
//                            Toast.makeText(ChatActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            progressDialog.dismiss();
//                            Log.e("onFailure", "onFailure: "+e.getMessage() );
//                            Toast.makeText(ChatActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
//                                    .getTotalByteCount());
//                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
//                        }
//                    });
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent result = new Intent();
            result.putExtra("idFriend", idFriend.get(0));
            setResult(RESULT_OK, result);
            this.finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra("idFriend", idFriend.get(0));
        setResult(RESULT_OK, result);
        this.finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSend) {
            String content = editWriteMessage.getText().toString().trim();
            if (content.length() > 0) {
                editWriteMessage.setText("");
                Message newMessage = new Message();
                newMessage.text = content;
                newMessage.type = "text";
                newMessage.idSender = StaticConfig.UID;
                newMessage.idReceiver = roomId;
                newMessage.timestamp = System.currentTimeMillis();

                FirebaseDatabase.getInstance().getReference().child("message/" + roomId).push().setValue(newMessage);
            }

        }
    }
}

class ListMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Consersation consersation;
    private HashMap<String, Bitmap> bitmapAvata;
    private HashMap<String, DatabaseReference> bitmapAvataDB;
    private Bitmap bitmapAvataUser;

    public ListMessageAdapter(Context context, Consersation consersation, HashMap<String, Bitmap> bitmapAvata, Bitmap bitmapAvataUser) {
        this.context = context;
        this.consersation = consersation;
        this.bitmapAvata = bitmapAvata;
        this.bitmapAvataUser = bitmapAvataUser;
        bitmapAvataDB = new HashMap<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ChatActivity.VIEW_TYPE_FRIEND_MESSAGE) {
            View view = LayoutInflater.from(context).inflate(R.layout.rc_item_message_friend, parent, false);
            return new ItemMessageFriendHolder(view);
        } else if (viewType == ChatActivity.VIEW_TYPE_USER_MESSAGE) {
            View view = LayoutInflater.from(context).inflate(R.layout.rc_item_message_user, parent, false);
            return new ItemMessageUserHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemMessageFriendHolder) {

//            Log.e("fgdfgfdg", consersation.getListMessageData().get(position).text);
//            if(consersation.getListMessageData().get(position).type.equals("image")){
//                ((ItemMessageUserHolder) holder).imgContent.setVisibility(View.VISIBLE);
//                ((ItemMessageUserHolder) holder).txtContent.setVisibility(View.GONE);
//                Picasso.get().load(consersation.getListMessageData().get(position).text).into(((ItemMessageFriendHolder) holder).imgContent);
//            } else {
                ((ItemMessageFriendHolder) holder).txtContent.setText(consersation.getListMessageData().get(position).text);
                Bitmap currentAvata = bitmapAvata.get(consersation.getListMessageData().get(position).idSender);
                if (currentAvata != null) {
                    ((ItemMessageFriendHolder) holder).avata.setImageBitmap(currentAvata);
                } else {
                    final String id = consersation.getListMessageData().get(position).idSender;
                    if (bitmapAvataDB.get(id) == null) {
                        bitmapAvataDB.put(id, FirebaseDatabase.getInstance().getReference().child("user/" + id + "/avata"));
                        bitmapAvataDB.get(id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getValue() != null) {
                                    String avataStr = (String) dataSnapshot.getValue();
                                    if (!avataStr.equals(StaticConfig.STR_DEFAULT_BASE64)) {
                                        byte[] decodedString = Base64.decode(avataStr, Base64.DEFAULT);
                                        ChatActivity.bitmapAvataFriend.put(id, BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
                                    } else {
                                        ChatActivity.bitmapAvataFriend.put(id, BitmapFactory.decodeResource(context.getResources(), R.drawable.default_avata));
                                    }
                                    notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
//            }
        } else if (holder instanceof ItemMessageUserHolder) {
//            if(consersation.getListMessageData().get(position).type.equals("image")){
//                ((ItemMessageUserHolder) holder).homeprogress.setVisibility(View.VISIBLE);

//                ((ItemMessageUserHolder) holder).txtContent.setVisibility(View.GONE);
//                Picasso.get().load(consersation.getListMessageData().get(position).text)
//                        .into(((ItemMessageUserHolder) holder).imgContent);

                if(consersation.getListMessageData().get(position).type.equals("image")){
                    ((ItemMessageUserHolder) holder).imgContent.setVisibility(View.VISIBLE);
                    ((ItemMessageUserHolder) holder).txtContent.setVisibility(View.GONE);
                    ((ItemMessageUserHolder) holder).homeprogress.setVisibility(View.VISIBLE);
                } else if (consersation.getListMessageData().get(position).type.equals("text")){
                    ((ItemMessageUserHolder) holder).imgContent.setVisibility(View.GONE);
                    ((ItemMessageUserHolder) holder).txtContent.setVisibility(View.VISIBLE);
                    ((ItemMessageUserHolder) holder).homeprogress.setVisibility(View.GONE);
                }


                Picasso.get().load(consersation.getListMessageData().get(position).text)
                        .into(((ItemMessageUserHolder) holder).imgContent, new Callback() {
                            @Override
                            public void onSuccess() {
                                ((ItemMessageUserHolder) holder).homeprogress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {

                            }

                        });
//            }
                ((ItemMessageUserHolder) holder).txtContent.setText(consersation.getListMessageData().get(position).text);
//            }
            if (bitmapAvataUser != null) {
                ((ItemMessageUserHolder) holder).avata.setImageBitmap(bitmapAvataUser);
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        return consersation.getListMessageData().get(position).idSender.equals(StaticConfig.UID) ? ChatActivity.VIEW_TYPE_USER_MESSAGE : ChatActivity.VIEW_TYPE_FRIEND_MESSAGE;
    }

    @Override
    public int getItemCount() {
        return consersation.getListMessageData().size();
    }
}

class ItemMessageUserHolder extends RecyclerView.ViewHolder {
    public TextView txtContent;
    public ImageView imgContent;
    public ProgressBar homeprogress;
    public CircleImageView avata;

    public ItemMessageUserHolder(View itemView) {
        super(itemView);
        txtContent = (TextView) itemView.findViewById(R.id.textContentUser);
        imgContent = (ImageView) itemView.findViewById(R.id.imgContentUser);
        homeprogress = (ProgressBar) itemView.findViewById(R.id.homeprogress);
        avata = (CircleImageView) itemView.findViewById(R.id.imageView2);
    }
}

class ItemMessageFriendHolder extends RecyclerView.ViewHolder {
    public TextView txtContent;
    public ImageView imgContent;
    public ProgressBar homeprogress;
    public CircleImageView avata;

    public ItemMessageFriendHolder(View itemView) {
        super(itemView);
        txtContent = (TextView) itemView.findViewById(R.id.textContentFriend);
        imgContent = (ImageView) itemView.findViewById(R.id.ImgContentFriend);
        homeprogress = (ProgressBar) itemView.findViewById(R.id.homeprogress);
        avata = (CircleImageView) itemView.findViewById(R.id.imageView3);
    }
}
