package com.p2s.vending.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.p2s.vending.R;
import com.p2s.vending.adapters.ProductsAdapter;
import com.p2s.vending.classes.VendingProduct;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ProductsFragment extends ListFragment {

    private static int mBalance = 0;
    private ArrayList<VendingProduct> mProducts;
    private OnFragmentInteractionListener mListener;
    private static TextView txt_balance;

    // TODO: Rename and change types of parameters
    public static ProductsFragment newInstance(ArrayList<VendingProduct> _products) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putSerializable("products", _products);

        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mProducts = (ArrayList<VendingProduct>) getArguments().getSerializable("products");
        }


    }

    public static void setBalance(int balance) {
        mBalance = balance;
        if (null != txt_balance)
         txt_balance.setText("$"+(double)mBalance/100);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_balance, container, false);

        setListAdapter(new ProductsAdapter(getActivity(), android.R.layout.simple_expandable_list_item_2, mProducts));
        txt_balance = (TextView) rootView.findViewById(R.id.txt_balance);
        txt_balance.setText("$"+(double)mBalance/100);

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        VendingProduct selected = mProducts.get(position);

        if (selected.stock()<=0){
            Toast.makeText(getActivity(), "product sold out", Toast.LENGTH_LONG).show();
            return;
        }

        if (selected.price() > mBalance) {
            Toast.makeText(getActivity(), "Insufficient balance", Toast.LENGTH_LONG).show();
            return;
        }
        if (null != mListener) {
            mListener.selectProduct(mProducts.get(position));
        }
        ((ProductsAdapter)getListAdapter()).notifyDataSetChanged();
    }


    public interface OnFragmentInteractionListener {
         void selectProduct(VendingProduct product);
    }

}
