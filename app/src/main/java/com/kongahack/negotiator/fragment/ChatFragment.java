package com.kongahack.negotiator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kongahack.negotiator.R;
import com.kongahack.negotiator.model.ChatItem;

/**
 * Created by brenda on 10/29/16.
 * This view is for is invoked from the chatListFragment
 *
 */
public class ChatFragment extends Fragment{

    private EditText inputMsg;
    private FloatingActionButton sendMessage;
    FirebaseDatabase chatDatabase;
    DatabaseReference databaseReference;
    private ChatItem chatItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chat,container,false);

        inputMsg=(EditText)view.findViewById(R.id.input_message);
        sendMessage=(FloatingActionButton) view.findViewById(R.id.send_button);

        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        chatDatabase=FirebaseDatabase.getInstance();
        databaseReference=chatDatabase.getReference();
    }
}
