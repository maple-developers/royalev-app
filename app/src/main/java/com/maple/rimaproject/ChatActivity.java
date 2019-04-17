package com.maple.rimaproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.TextView;
import android.widget.Toast;

import com.maple.rimaproject.data.FriendDB;
import com.maple.rimaproject.data.SharedPreferenceHelper;
import com.maple.rimaproject.data.StaticConfig;
import com.maple.rimaproject.model.Consersation;
import com.maple.rimaproject.model.Friend;
import com.maple.rimaproject.model.ListFriend;
import com.maple.rimaproject.model.Message;
import com.maple.rimaproject.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.maple.rimaproject.data.FriendDB;
import com.maple.rimaproject.data.SharedPreferenceHelper;
import com.maple.rimaproject.data.StaticConfig;
import com.maple.rimaproject.model.Consersation;
import com.maple.rimaproject.model.Friend;
import com.maple.rimaproject.model.ListFriend;
import com.maple.rimaproject.model.Message;
import com.maple.rimaproject.model.User;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
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

    private ListFriend dataListFriend = null;
    private ArrayList<String> listFriendID = null;

    TextView txtTitle;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intentData = getIntent();
        idFriend = intentData.getCharSequenceArrayListExtra(StaticConfig.INTENT_KEY_CHAT_ID);
        roomId = intentData.getStringExtra(StaticConfig.INTENT_KEY_CHAT_ROOM_ID);
//        Log.e("ewewewccc", roomId);
        String nameFriend = intentData.getStringExtra(StaticConfig.INTENT_KEY_CHAT_FRIEND);

        bundle = getIntent().getExtras();
        consersation = new Consersation();
        btnSend = findViewById(R.id.btnSend);
        txtTitle = findViewById(R.id.txtTitle);
//        Toast.makeText(this,bundle.getString("project_name") , Toast.LENGTH_SHORT).show();
        txtTitle.setText(bundle.getString("project_name"));
        btnSend.setOnClickListener(this);

        String base64AvataUser = SharedPreferenceHelper.getInstance(this).getUserInfo().avata;
        if (!base64AvataUser.equals(StaticConfig.STR_DEFAULT_BASE64)) {
            byte[] decodedString = Base64.decode(base64AvataUser, Base64.DEFAULT);
            bitmapAvataUser = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } else {
            bitmapAvataUser = null;
        }


        if (dataListFriend == null) {
            dataListFriend = FriendDB.getInstance(ChatActivity.this).getListFriend();
            if (dataListFriend.getListFriend().size() > 0) {
                listFriendID = new ArrayList<>();
                for (Friend friend : dataListFriend.getListFriend()) {
                    listFriendID.add(friend.id);
                }
//                detectFriendOnline.start();
            }
        }

        if (listFriendID == null) {
            listFriendID = new ArrayList<>();
            getListFriendUId();
        }



//
//        FirebaseDatabase.getInstance().getReference().child("user").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    User user = snapshot.getValue(User.class);
//                    if(user.type.equals("agent")){
//                        findIDEmail(user.email);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//

        Log.e("dlfhgdfg", String.valueOf(dataListFriend.getListFriend().size()));


        editWriteMessage = (EditText) findViewById(R.id.editWriteMessage);
//        if (dataListFriend.getListFriend().size() != 0) {

//            getSupportActionBar().setTitle(nameFriend);
            linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerChat = (RecyclerView) findViewById(R.id.recyclerChat);
            recyclerChat.setLayoutManager(linearLayoutManager);
            adapter = new ListMessageAdapter(this, consersation, bitmapAvataFriend, bitmapAvataUser);


            if (dataListFriend.getListFriend().size() != 0){


            for (int i=0; i < dataListFriend.getListFriend().size(); i++){
                Log.e("sfdgfdgfdgfdg", dataListFriend.getListFriend().get(i).idRoom);
                FirebaseDatabase.getInstance().getReference().child("message").child(dataListFriend.getListFriend().get(i).idRoom).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.e("eeeeeeeq", dataSnapshot.getKey());

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if (!snapshot.child("idSender").getValue().equals(StaticConfig.UID)){
                                Log.e("rewtertre", dataSnapshot.getKey());

                                FirebaseDatabase.getInstance().getReference().child("message/" + dataSnapshot.getKey()).addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        if (dataSnapshot.getValue() != null) {
                                            HashMap mapMessage = (HashMap) dataSnapshot.getValue();
                                            Message newMessage = new Message();
                                            newMessage.idSender = (String) mapMessage.get("idSender");
                                            newMessage.idReceiver = (String) mapMessage.get("idReceiver");
                                            newMessage.text = (String) mapMessage.get("text");
                                            newMessage.timestamp = (long) mapMessage.get("timestamp");
                                            consersation.getListMessageData().add(newMessage);
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
                            } else {

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


            if (consersation.getListMessageData().size() == 0){
                FirebaseDatabase.getInstance().getReference().child("message/" + dataListFriend.getListFriend().get(0).idRoom).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot5, String s) {
                        if (dataSnapshot5.getValue() != null) {
                            HashMap mapMessage = (HashMap) dataSnapshot5.getValue();
                            Message newMessage = new Message();
                            newMessage.idSender = (String) mapMessage.get("idSender");
                            newMessage.idReceiver = (String) mapMessage.get("idReceiver");
                            newMessage.text = (String) mapMessage.get("text");
                            newMessage.timestamp = (long) mapMessage.get("timestamp");
                            consersation.getListMessageData().add(newMessage);
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
            }
                } else {

            }


            recyclerChat.setAdapter(adapter);
//        }

        for (int i=0; i < dataListFriend.getListFriend().size(); i++) {
            getFriend(dataListFriend.getListFriend().get(i).id);
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

    /**
     * Lay danh sach ban be tren server
     */
    private void getListFriendUId() {
        FirebaseDatabase.getInstance().getReference().child("friend/" + StaticConfig.UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    HashMap mapRecord = (HashMap) dataSnapshot.getValue();
                    Iterator listKey = mapRecord.keySet().iterator();
                    while (listKey.hasNext()) {
                        String key = listKey.next().toString();
                        listFriendID.add(mapRecord.get(key).toString());
                    }
                    getAllFriendInfo(0);
                } else {
//                    dialogFindAllFriend.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * Truy cap bang user lay thong tin id nguoi dung
     */
    private void getAllFriendInfo(final int index) {
        if (index == listFriendID.size()) {
            //save list friend
            adapter.notifyDataSetChanged();
//            dialogFindAllFriend.dismiss();
//            mSwipeRefreshLayout.setRefreshing(false);
//            detectFriendOnline.start();
        } else {
            final String id = listFriendID.get(index);
            FirebaseDatabase.getInstance().getReference().child("user/" + id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        Friend user = new Friend();
                        HashMap mapUserInfo = (HashMap) dataSnapshot.getValue();
                        user.name = (String) mapUserInfo.get("name");
                        user.email = (String) mapUserInfo.get("email");
                        user.avata = (String) mapUserInfo.get("avata");
                        user.id = id;
                        user.idRoom = id.compareTo(StaticConfig.UID) > 0 ? (StaticConfig.UID + id).hashCode() + "" : "" + (id + StaticConfig.UID).hashCode();
                        dataListFriend.getListFriend().add(user);
                        FriendDB.getInstance(ChatActivity.this).addFriend(user);
                    }
                    getAllFriendInfo(index + 1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
//        Intent result = new Intent();
//        result.putExtra("idFriend", idFriend.get(0));
//        setResult(RESULT_OK, result);
        this.finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSend) {
            String content = editWriteMessage.getText().toString().trim();
            if (content.length() > 0) {

                for (int i=0; i < dataListFriend.getListFriend().size(); i++){
//                    Log.e("ghfggdfgdf", "ewerwer "+ dataListFriend.getListFriend().get(i).email);
//
//
//                        deleteFriend(dataListFriend.getListFriend().get(i).id);
//                        Log.e("ghfggdfgdf", "ewerwer "+ dataListFriend.getListFriend().get(i).id);
//
//
//
                    editWriteMessage.setText("");
                    Message newMessage = new Message();
                    newMessage.text = content;
                    newMessage.idSender = StaticConfig.UID;
                    newMessage.idReceiver = dataListFriend.getListFriend().get(i).idRoom;
                    newMessage.timestamp = System.currentTimeMillis();
                    FirebaseDatabase.getInstance().getReference().child("message/" + dataListFriend.getListFriend().get(i).idRoom).push().setValue(newMessage);
                }


//                editWriteMessage.setText("");
//                Message newMessage = new Message();
//                newMessage.text = content;
//                newMessage.idSender = StaticConfig.UID;
//                newMessage.idReceiver = roomId;
//                newMessage.timestamp = System.currentTimeMillis();
//                FirebaseDatabase.getInstance().getReference().child("message/" + roomId).push().setValue(newMessage);


            }
        }
    }

    private void deleteFriend(final String idFriend) {
        if (idFriend != null) {
            FirebaseDatabase.getInstance().getReference().child("friend").child(idFriend).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getValue() == null) {

                    } else {
//                        String idRemoval = ((HashMap) dataSnapshot.getValue()).keySet().iterator().next().toString();
                        for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (!dataSnapshot1.getValue().equals(StaticConfig.UID)){
                                final String keyDeleted = dataSnapshot1.getKey();
                                FirebaseDatabase.getInstance().getReference().child("friend")

                                        .child(idFriend).child(dataSnapshot1.getKey()).removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                FirebaseDatabase.getInstance().getReference().child("friend").child(String.valueOf(dataSnapshot1.getValue())).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        for (final DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                                            if (dataSnapshot2.getValue().equals(idFriend)) {
                                                                FirebaseDatabase.getInstance().getReference().child("friend").child(String.valueOf(dataSnapshot1.getValue())).child(dataSnapshot2.getKey()).removeValue();
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
//                                                Intent intentDeleted = new Intent(FriendsFragment.ACTION_DELETE_FRIEND);
//                                                intentDeleted.putExtra("idFriend", idFriend);
//                                                ChatActivity.this.sendBroadcast(intentDeleted);
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
//
                                            }
                                        });
                            }

                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
//            dialogWaitDeleting.dismiss();
//            new LovelyInfoDialog(context)
//                    .setTopColorRes(R.color.colorPrimary)
//                    .setTitle("Error")
//                    .setMessage("Error occurred during deleting friend")
//                    .show();
        }
    }

    private void getFriend(final String idFriend) {
        if (idFriend != null) {
            FirebaseDatabase.getInstance().getReference().child("friend").child(idFriend).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getValue() == null) {

                    } else {
//                        String idRemoval = ((HashMap) dataSnapshot.getValue()).keySet().iterator().next().toString();
                        for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (!dataSnapshot1.getValue().equals(StaticConfig.UID)){
                                FirebaseDatabase.getInstance().getReference().child("friend")
                                        .child(idFriend).child(dataSnapshot1.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        FirebaseDatabase.getInstance().getReference().child("friend").child(String.valueOf(dataSnapshot.getValue())).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                                Log.e("tryghfghg", String.valueOf(dataSnapshot.getKey()));
                                                for (final DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
//                                                    if (dataSnapshot.getValue().equals(idFriend)) {
//
//                                                        FirebaseDatabase.getInstance().getReference().child("friend").child(dataSnapshot2.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
//                                                            @Override
//                                                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                                                Log.e("tryghfghg", String.valueOf(dataSnapshot.getValue()));
//                                                            }
//
//                                                            @Override
//                                                            public void onCancelled(DatabaseError databaseError) {
//
//                                                            }
//                                                        });
//                                                    }
                                                    Log.e("tryghfghg1", String.valueOf(dataSnapshot1.getValue()));
                                                    Log.e("tryghfghg2", String.valueOf(idFriend));
                                                    if (dataSnapshot2.getValue().equals(idFriend)) {
                                                        Log.e("tryghfghg", String.valueOf(dataSnapshot2.getKey()));
                                                    }

                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                Log.e("glghlkf", String.valueOf(dataSnapshot1.getValue()));
                            }

                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
//            dialogWaitDeleting.dismiss();
//            new LovelyInfoDialog(context)
//                    .setTopColorRes(R.color.colorPrimary)
//                    .setTitle("Error")
//                    .setMessage("Error occurred during deleting friend")
//                    .show();
        }
    }


    /**
     * TIm id cua email tren server
     *
     * @param email
     */
    private void findIDEmail(String email) {
//        dialogWait.setCancelable(false)
//                .setIcon(R.drawable.ic_add_friend)
//                .setTitle("Finding friend....")
//                .setTopColorRes(R.color.colorPrimary)
//                .show();
        FirebaseDatabase.getInstance().getReference().child("user").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                dialogWait.dismiss();
                if (dataSnapshot.getValue() == null) {
                    //email not found
//                    new LovelyInfoDialog(context)
//                            .setTopColorRes(R.color.colorAccent)
//                            .setIcon(R.drawable.ic_add_friend)
//                            .setTitle("Fail")
//                            .setMessage("Email not found")
//                            .show();
                } else {
                    String id = ((HashMap) dataSnapshot.getValue()).keySet().iterator().next().toString();
                    if (id.equals(StaticConfig.UID)) {
//                        new LovelyInfoDialog(context)
//                                .setTopColorRes(R.color.colorAccent)
//                                .setIcon(R.drawable.ic_add_friend)
//                                .setTitle("Fail")
//                                .setMessage("Email not valid")
//                                .show();
                    } else {
                        HashMap userMap = (HashMap) ((HashMap) dataSnapshot.getValue()).get(id);
                        Friend user = new Friend();
                        user.name = (String) userMap.get("name");
                        user.email = (String) userMap.get("email");
                        user.avata = (String) userMap.get("avata");
                        user.id = id;
                        user.idRoom = id.compareTo(StaticConfig.UID) > 0 ? (StaticConfig.UID + id).hashCode() + "" : "" + (id + StaticConfig.UID).hashCode();
                        checkBeforAddFriend(id, user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Lay danh sach friend cua một UID
     */
    private void checkBeforAddFriend(final String idFriend, Friend userInfo) {
////        dialogWait.setCancelable(false)
//                .setIcon(R.drawable.ic_add_friend)
//                .setTitle("Add friend....")
//                .setTopColorRes(R.color.colorPrimary)
//                .show();

        //Check xem da ton tai id trong danh sach id chua
        if (listFriendID.contains(idFriend)) {
//            dialogWait.dismiss();
//            new LovelyInfoDialog(ChatActivity.this)
//                    .setTopColorRes(R.color.colorPrimary)
//                    .setIcon(R.drawable.ic_add_friend)
//                    .setTitle("Friend")
//                    .setMessage("User "+userInfo.email + " has been friend")
//                    .show();
        } else {
            addFriend(idFriend, true);
            listFriendID.add(idFriend);
            dataListFriend.getListFriend().add(userInfo);
            FriendDB.getInstance(ChatActivity.this).addFriend(userInfo);
//            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Add friend
     *
     * @param idFriend
     */
    private void addFriend(final String idFriend, boolean isIdFriend) {
        if (idFriend != null) {
            if (isIdFriend) {
                FirebaseDatabase.getInstance().getReference().child("friend/" + StaticConfig.UID).push().setValue(idFriend)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    addFriend(idFriend, false);
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                                dialogWait.dismiss();
                                new LovelyInfoDialog(ChatActivity.this)
                                        .setTopColorRes(R.color.colorAccent)
                                        .setIcon(R.drawable.ic_add_friend)
                                        .setTitle("False")
                                        .setMessage("False to add friend success")
                                        .show();
                            }
                        });
            } else {
                FirebaseDatabase.getInstance().getReference().child("friend/" + idFriend).push().setValue(StaticConfig.UID).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            addFriend(null, false);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                                dialogWait.dismiss();

                            }
                        });
            }
        } else {
//            dialogWait.dismiss();
//            new LovelyInfoDialog(context)
//                    .setTopColorRes(R.color.colorPrimary)
//                    .setIcon(R.drawable.ic_add_friend)
//                    .setTitle("Success")
//                    .setMessage("Add friend success")
//                    .show();
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemMessageFriendHolder) {
            ((ItemMessageFriendHolder) holder).txtContent.setText(consersation.getListMessageData().get(position).text);

//            Bitmap currentAvata = bitmapAvata.get(consersation.getListMessageData().get(position).idSender);
//            if (currentAvata != null) {
//                ((ItemMessageFriendHolder) holder).avata.setImageBitmap(currentAvata);
//            } else {
                final String id = consersation.getListMessageData().get(position).idSender;
                if(bitmapAvataDB.get(id) == null){
                    bitmapAvataDB.put(id, FirebaseDatabase.getInstance().getReference().child("user/" + id + "/avata"));
                    bitmapAvataDB.get(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {
                                String avataStr = (String) dataSnapshot.getValue();
//                                if(!avataStr.equals(StaticConfig.STR_DEFAULT_BASE64)) {
                                    byte[] decodedString = Base64.decode(avataStr, Base64.DEFAULT);
//                                    ChatActivity.bitmapAvataFriend.put(id, BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
//                                }else{
//                                    ChatActivity.bitmapAvataFriend.put(id, BitmapFactory.decodeResource(context.getResources(), R.drawable.default_avata));
//                                }
                                notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
//                }
            }
        } else if (holder instanceof ItemMessageUserHolder) {
            ((ItemMessageUserHolder) holder).txtContent.setText(consersation.getListMessageData().get(position).text);
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
    public CircleImageView avata;

    public ItemMessageUserHolder(View itemView) {
        super(itemView);
        txtContent = (TextView) itemView.findViewById(R.id.textContentUser);
        avata = (CircleImageView) itemView.findViewById(R.id.imageView2);
    }
}

class ItemMessageFriendHolder extends RecyclerView.ViewHolder {
    public TextView txtContent;
    public CircleImageView avata;

    public ItemMessageFriendHolder(View itemView) {
        super(itemView);
        txtContent = (TextView) itemView.findViewById(R.id.textContentFriend);
        avata = (CircleImageView) itemView.findViewById(R.id.imageView3);
    }

}
