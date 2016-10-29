package com.kongahack.negotiator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kongahack.negotiator.app.GlobalVariables;
import com.kongahack.negotiator.R;
import com.kongahack.negotiator.adapter.ProductAdapter;

/**
 * Created by brenda on 10/29/16.
 */
public class AddNewUserFragment extends DialogFragment {
    EditText editText;
    Button button;
    private GlobalVariables app;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_user,container,false);

        app =GlobalVariables.appInstance;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //productAdapter.notifyDataSetChanged();
    }
}
