package chawla.fireapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tushar on 9/10/16.
 */
public class ProductListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Product> mProductList;

    //Constructor

    public ProductListAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v=View.inflate(mContext, R.layout.item_product_list, null);
        TextView tvname = (TextView)v.findViewById(R.id.name);
        RelativeLayout rel = (RelativeLayout) v.findViewById(R.id.trya);
        Log.e("List Adapter", "I am here");

        TextView tvprice = (TextView)v.findViewById(R.id.price);
        TextView tvdescription = (TextView)v.findViewById(R.id.description);
        //Set text for TextView
        tvname.setText(mProductList.get(position).getName());
        if (tvname.getText().toString().equalsIgnoreCase("Assigned")||tvname.getText().toString().equalsIgnoreCase("Unassigned")||tvname.getText().toString().equalsIgnoreCase("Finished") ){
            tvname.setGravity(Gravity.CENTER);
            rel.setBackgroundColor(Color.parseColor("#00aa75"));

            tvname.setTextColor(Color.parseColor("#ffffff"));
            tvname.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
            //tvname.setBackgroundColor(Color.parseColor("#00aa75"));
        }
        tvprice.setText(String.valueOf(mProductList.get(position).getPrice()));
        tvdescription.setText(mProductList.get(position).getDescription());

        //Save product id to tag
        v.setTag(mProductList.get(position).getId());
        return v;
    }
}

