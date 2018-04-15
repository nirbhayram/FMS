package sen.mukul.com.fms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nirbhay on 12-04-2018.
 */

public class BookAdapter extends BaseAdapter {

    Context context;
    ArrayList<Book> list;
    public BookAdapter(Context c, ArrayList<Book> l){
        this.context = c;
        this.list = l;
    }

    private class ViewHolder {
        //declare items in single item of list view here
        TextView title,course,writtenby,price;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BookAdapter.ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(view==null) {
            view = mInflater.inflate(R.layout.single_book_item, null);
            holder = new BookAdapter.ViewHolder();
            holder.title = (TextView)view.findViewById(R.id.title);
            holder.course = (TextView)view.findViewById(R.id.course);
            holder.writtenby = (TextView)view.findViewById(R.id.writtenby);
            holder.price = (TextView)view.findViewById(R.id.price);
            view.setTag(holder);
        }
        else {
            holder = (BookAdapter.ViewHolder) view.getTag();
        }
        Book temp = list.get(i);
        holder.title.setText(temp.getTitle());
        holder.course.setText(temp.getCourse());
        holder.writtenby.setText("Quantity : "+temp.getDiscription());
        holder.price.setText("Price "+temp.getPrice()+" Rs/Kg");

        return view;
    }
}
