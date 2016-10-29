package com.kongahack.negotiator.fragment;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
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
import com.kongahack.negotiator.provider.SmackContact;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by brenda on 10/29/16.
 * This view is for is invoked from the chatListFragment
 *
 */
public class ChatFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>{

    private final String LOG_TAG = ChatFragment.class.getSimpleName();


    public static ReloadInterface reload;

    private ListView listView;
    private SimpleCursorAdapter cursorAdapter;


    private EditText inputMsg;
    //private FloatingActionButton sendMessage;
    private FirebaseListAdapter<ChatItem> chatAdapter;
    private FloatingActionButton sendMessage;
    FirebaseDatabase chatDatabase;
    DatabaseReference databaseReference;
    DatabaseReference chatRef;
    private ChatItem chatItem;
    int position;



    String[] columns ={
            SmackContact.SmackColumn.CHAT
    } ;


    public interface ReloadInterface {

        void reloadFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            reload = (ReloadInterface) context;
        }
        catch (ClassCastException email) {
            throw new ClassCastException(context.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chat,container,false);

        position = getArguments().getInt(Constants.KEY_ITEM_POSITION);
        initialize(view);

        cursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.chat_items,
                null,
                columns,
                new int[] {R.id.chat_text},
                0
        );


        listView = (ListView) view.findViewById(R.id.list_chat);
        listView.setAdapter(cursorAdapter);


        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0,null,this);

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

    public void initialize(View view) {

        inputMsg=(EditText)view.findViewById(R.id.input_message);
        sendMessage=(FloatingActionButton) view.findViewById(R.id.send_button);


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getMsg = inputMsg.getText().toString();
                if(!getMsg.isEmpty()) {

                    ContentValues values = new ContentValues();
                    values.put(SmackContact.Smack.CHAT, getMsg);


                    getActivity().getContentResolver()
                            .insert(SmackContact.Smack.CONTENT_URI, values);
                    values.clear();

                    reload.reloadFragment();
                }
            }
        });
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(
                getActivity(),
                SmackContact.Smack.CONTENT_URI,
                SmackContact.Smack.PROJECTION,
                null,
                null,
                SmackContact.Smack.SORT_ORDER
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if(data.moveToFirst()) {

            cursorAdapter.swapCursor(data);
            cursorAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        cursorAdapter.swapCursor(null);
    }
}
