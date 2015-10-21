package com.p2s.vending.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.p2s.vending.R;
import com.p2s.vending.classes.VendingProduct;

import java.util.List;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public class ProductsAdapter extends ArrayAdapter<VendingProduct> {


    private final List<VendingProduct> items;
    private final Context context;
    private final LayoutInflater vi;

    public ProductsAdapter(Context context, int resource, List<VendingProduct> products) {
        super(context, resource, products);
        this.items = products;

        this.context = context;
        this.vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v==null){
            v = vi.inflate(android.R.layout.simple_list_item_2, null);
        }
        VendingProduct item = items.get(position);
        if (item != null)  {
            TextView txt1 = (TextView) v.findViewById(android.R.id.text1);
            txt1.setText(item.name());
            TextView txt2 = (TextView) v.findViewById(android.R.id.text2);
            txt2.setText("$"+item.price());
            txt2.setText("$"+((double)item.price()/100));

            if (item.stock()<=0) {
                v.setBackgroundColor(Color.GRAY);

             }
            return v;

        }
        return null;
    }


}

