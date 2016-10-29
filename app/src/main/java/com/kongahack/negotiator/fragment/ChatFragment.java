package com.kongahack.negotiator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kongahack.negotiator.R;
import com.kongahack.negotiator.app.GlobalVariables;
import com.kongahack.negotiator.helper.Constants;
import com.kongahack.negotiator.model.Chat;
import com.kongahack.negotiator.model.ChatItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by brenda on 10/29/16.
 * This view is for is invoked from the chatListFragment
 *
 */
public class ChatFragment extends Fragment{

    private EditText inputMsg;
    //private FloatingActionButton sendMessage;
    private FirebaseListAdapter<ChatItem> chatAdapter;
    private FloatingActionButton sendMessage;
    FirebaseDatabase chatDatabase;
    DatabaseReference databaseReference;
    DatabaseReference chatRef;
    private ChatItem chatItem;

    private String sellerID;

    private ArrayList<Chat> chatArrayList;

    private ListView chatRecycler;
    DatabaseReference refBuyer;
    DatabaseReference refSeller;
    int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chat,container,false);

        position=getArguments().getInt(Constants.KEY_ITEM_POSITION);

        inputMsg=(EditText)view.findViewById(R.id.input_message);
        sendMessage=(FloatingActionButton) view.findViewById(R.id.send_button);
        chatRecycler=(ListView)view.findViewById(R.id.chat_list);
        chatArrayList=new ArrayList<>();

        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sellerID=GlobalVariables.appInstance.getMyProducts().get(position).getSellerChatID
                ();


        databaseReference= GlobalVariables.appInstance.getDatabaseReference();


        refBuyer =databaseReference.child(Constants.BUYER);
        refSeller=databaseReference.child(Constants.SELLER);

        refSeller.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //chatArrayList.add(new Chat(dataSnapshot.getValue().toString()));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        chatRef =databaseReference.child(Constants.CHAT_REF+"/"+Constants
                .THIS_USER+"/"+sellerID+"/");
        chatAdapter=new FirebaseListAdapter<ChatItem>(getActivity(),ChatItem.class,
                R.layout.layout_chat_item, chatRef) {
            @Override
            protected void populateView(View v, ChatItem model, int position) {
                Log.e("here"," populate view "+model.toString());
                TextView msgView=(TextView)v.findViewById(R.id.message);
                if (model.getIsUser().equalsIgnoreCase("yes"))
                {
                    //this message was sent by user
                    RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)msgView.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    msgView.setLayoutParams(params);
                    msgView.setText(chatItem.getMessage());

                }
                else {
                    //was not sent by user
                    RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)msgView.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    msgView.setLayoutParams(params);
                    msgView.setText(chatItem.getMessage());

                }
            }
        };
        chatRecycler.setAdapter(chatAdapter);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=inputMsg.getText().toString();
                if (TextUtils.isEmpty(msg))
                {
                    return;
                }
                sendMyMessage(msg);
            }
        });
    }

    public void sendMyMessage(String mesg)
    {
        refBuyer.setValue(mesg);

        ChatItem item=new ChatItem(mesg,"yes");
        HashMap<String,Object> chatMap=item.toMap();
        HashMap<String,Object> objMap=new HashMap<>();
        objMap.put("/"+Constants
                .THIS_USER+"/"+sellerID+"/",chatMap);


        chatRef.updateChildren(objMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                Toast.makeText(getActivity(),"Message sent sucessfully",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
