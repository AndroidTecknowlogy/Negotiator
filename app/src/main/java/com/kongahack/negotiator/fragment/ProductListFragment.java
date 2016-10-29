package com.kongahack.negotiator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kongahack.negotiator.app.GlobalVariables;
import com.kongahack.negotiator.R;
import com.kongahack.negotiator.adapter.ProductAdapter;

/**
 * Created by nezspencer on 10/29/16.
 */

public class ProductListFragment extends Fragment {

    private RecyclerView productRecycler;
    private ProductAdapter productAdapter;
    private GlobalVariables app;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_products,container,false);

        productRecycler=(RecyclerView)view.findViewById(R.id.product_recycler);
        app=GlobalVariables.appInstance;
        productAdapter=new ProductAdapter(getActivity(),app.getMyProducts());
        productRecycler.setAdapter(productAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        productAdapter.notifyDataSetChanged();
    }
}
