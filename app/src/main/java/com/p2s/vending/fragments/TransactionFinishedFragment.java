package com.p2s.vending.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.p2s.vending.R;
import com.p2s.vending.VendingApp;
import com.p2s.vending.adapters.ChangeAdapter;
import com.p2s.vending.adapters.CoinAdapter;
import com.p2s.vending.classes.Coin;
import com.p2s.vending.classes.Vender;
import com.p2s.vending.classes.VendingProduct;

import java.util.ArrayList;

public class TransactionFinishedFragment extends ListFragment {


    private static final String TAG = "TransactionFinished";
    private static int mBalance = 0;
    private static VendingProduct mProduct;
    private TextView txt_pname;
    private TextView txt_pprice;
    private TextView txt_changedo;

    public static TransactionFinishedFragment newInstance() {
        TransactionFinishedFragment fragment = new TransactionFinishedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TransactionFinishedFragment() {
        // Required empty public constructor
    }

    public static void setBalance(int balance) {
        mBalance = balance;
    }

    public void setProduct(VendingProduct product) {
        mProduct = product;


        int changeDo = mBalance - mProduct.price();
        Log.d(TAG, "change due: $" + changeDo);



        txt_pname.setText(product.name());
        txt_pprice.setText("$"+(double)product.price()/100);
        txt_changedo.setText("$"+(double)changeDo/100);
        setListAdapter(new ChangeAdapter(getActivity(), android.R.layout.simple_list_item_2, Vender.getChange(changeDo)));
        Toast.makeText(getActivity(),"Thank you for your purchaise!",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_finished, container, false);
        txt_pname =(TextView)v.findViewById(R.id.txt_pname);
        txt_pprice =(TextView)v.findViewById(R.id.txt_pprice);
        txt_changedo =(TextView)v.findViewById(R.id.txt_changedo);
        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
