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

public class FragmentArticle extends Fragment {
    ArrayList<Article> arrayList;
    ListView listView;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_article, container, false);

        progressDialog =new ProgressDialog(getActivity());
        listView = (ListView)thisView.findViewById(R.id.listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article temp = arrayList.get(i);
                Data.reset();
                Data.setCourse(temp.getCourse());
                Data.setMaterialname(temp.getTitle());
                Data.setDiscription(temp.getDiscription());
                Data.setAmount(temp.getPrice());
                Data.setCategory("Article");
                Data.setWrittenby(temp.getWrittenby());
                Data.setArticleno(temp.getArtticleno());
                Data.setOwnerid(temp.getOwnerid());
                //ShowDialougFragment dialougFragmentConfirm = new ShowDialougFragment();
                //dialougFragmentConfirm.show(getActivity().getFragmentManager(),"confirm");
            }
        });

        changeAdapter(getList());
        return thisView;
    }


    public void changeAdapter(ArrayList<Article> list){
        arrayList = list;
        listView.setAdapter(null);
        ArticleAdapter bookAdapter = new ArticleAdapter(getActivity(),list);
        listView.setAdapter(bookAdapter);
    }

    public ArrayList<Article> getList(){
        ArrayList<Article> list = new ArrayList<Article>();
        list.add(new Article("Potato","MishraJi","10","12-04-2018"));
        list.add(new Article("Wheat","MukulBhai","5","12-04-2018"));
        list.add(new Article("Brown rice","SidharthBhai","12","12-04-2018"));
        list.add(new Article("Carrot","MishraJi","8","12-04-2018"));
        list.add(new Article("Spinach","JoshiSir","5","12-04-2018"));
        list.add(new Article("Bell Paper","Nirbhay","6","12-04-2018"));
        return list;
    }

}
