package sen.mukul.com.fms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Nirbhay on 12-04-2018.
 */

public class FragmentBook extends Fragment {
    ArrayList<Book> arrayList;
    ListView listView;
    ProgressDialog progressDialog;
    BookAdapter bookAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_book, container, false);

        progressDialog = new ProgressDialog(getActivity());
        listView = (ListView)thisView.findViewById(R.id.listview);

        list();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book temp = arrayList.get(i);
                Data.reset();
                Data.setCourse(temp.getCourse());
                Data.setMaterialname(temp.getTitle());
                Data.setDiscription(temp.getDiscription());
                Data.setAmount(temp.getPrice());
                Data.setOwnerid(temp.getOwnerid());
                ShowDialougFragment dialougFragmentConfirm = new ShowDialougFragment();
                dialougFragmentConfirm.show(getActivity().getFragmentManager(),"confirm");
            }
        });

        return thisView;
    }

    public void changeAdapter(ArrayList<Book> list){
        arrayList = list;
        listView.setAdapter(null);
        bookAdapter = new BookAdapter(getActivity(),list);
        listView.setAdapter(bookAdapter);
    }

    public void list(){
        final ArrayList<Book> list = new ArrayList<Book>();
        getBookList(new OnGetDataListener() {
            @Override
            public void onStart() {
                progressDialog.setMessage("Loading");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void onSuccess(DataSnapshot data) {
                for (DataSnapshot snapshot: data.getChildren()){
                    Log.e("++++++",snapshot.child("MATERIALNAME")+" this");
                    try {
                        list.add(new Book(
                                snapshot.child("MATERIALNAME").getValue().toString(),
                                snapshot.child("COURSE").getValue().toString(),
                                snapshot.child("AMOUNT").getValue().toString(),
                                snapshot.child("DISCRIPTION").getValue().toString(),
                                snapshot.child("OWNERID").getValue().toString()
                        ));
                    }
                    catch (Exception e){
                        Log.e("++++++",e.toString()+" Nirbhay");
                    }
                }
                changeAdapter(list);
                progressDialog.dismiss();
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

    public void getBookList(final OnGetDataListener onGetDataListener){
        onGetDataListener.onStart();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("MATERIAL").child("BOOK");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onGetDataListener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetDataListener.onFailed(databaseError);
            }
        });
    }
    @Override
    public void onResume() {
        listView.setAdapter(null);
        list();
        super.onResume();
    }
}
