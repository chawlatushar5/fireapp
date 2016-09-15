package chawla.fireapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tushar on 9/15/16.
 */
public class ChoreList_apdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] itemname;
    private final Integer[] imgid;
    private final Integer[] signid;

    public ChoreList_apdapter(Context context, String[] itemname, Integer[] imgid, Integer[] signid) {
        super(context, R.layout.list_for_chore, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.signid = signid;

    }
    public View getView(int position, View view, ViewGroup parent) {
       // LayoutInflater inflater=context.getLayoutInflater();
        View rowView=View.inflate(context, R.layout.list_for_chore, null);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.info);
        ImageView logo = (ImageView) rowView.findViewById(R.id.logo);
        ImageView sign = (ImageView) rowView.findViewById(R.id.sign);

        txtTitle.setText(itemname[position]);
        logo.setImageResource(imgid[position]);
        sign.setImageResource(signid[position]);
        return rowView;

    }
}