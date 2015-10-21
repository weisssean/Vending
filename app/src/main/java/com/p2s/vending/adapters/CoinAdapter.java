package com.p2s.vending.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.p2s.vending.classes.Coin;

import java.util.List;

/**
 * Created by MonkeyFish on 10/21/15.
 */
public class CoinAdapter extends ArrayAdapter<Coin> {


    private final List<Coin> items;
    private final Context context;
    private final LayoutInflater vi;

    public CoinAdapter(Context context, int resource, List<Coin> coins) {
        super(context, resource, coins);
        this.items = coins;

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
        Coin item = items.get(position);
        if (item != null)  {
            TextView txt1 = (TextView) v.findViewById(android.R.id.text1);
            txt1.setText(item.name());
            TextView txt2 = (TextView) v.findViewById(android.R.id.text2);
            txt2.setText("$"+((double)item.value()/100));
            return v;

        }
        return null;
    }


}

