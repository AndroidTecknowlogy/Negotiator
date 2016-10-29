package com.kongahack.negotiator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongahack.negotiator.R;

/**
 * Created by AGBOMA franklyn on 10/29/16.
 */
public class BoarderFragment extends Fragment {

    TextView companyName, online;
    ImageView action, chat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View boarder = inflater.inflate(R.layout.fragment_boarder, container, false);

        //initialize
        initialize(boarder);

        return boarder;
    }

    public void initialize(View boarder) {

        companyName = (TextView) boarder.findViewById(R.id.company_name);
        online = (TextView) boarder.findViewById(R.id.online);
        action = (ImageView) boarder.findViewById(R.id.ic_action);
        chat = (ImageView) boarder.findViewById(R.id.ic_chat);


    }
}
