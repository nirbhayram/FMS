package sen.mukul.com.fms;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nirbhay on 14-04-2018.
 */

public class ShowDialougFragment extends DialogFragment {

    TextView materialname,discription,amount,course,category,writtenby,isbn,articleno,paperid,year,ownerid;
    LinearLayout l_writtenby,l_isbn,l_articleno,l_paperid,l_year,l_ownerid;
    Button order;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View thisView = inflater.inflate(R.layout.fragment_show,container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        order = (Button) thisView.findViewById(R.id.order);
        materialname = (TextView) thisView.findViewById(R.id.tx_materialname);
        discription = (TextView) thisView.findViewById(R.id.tx_discription);
        amount = (TextView) thisView.findViewById(R.id.tx_amount);
        course = (TextView) thisView.findViewById(R.id.tx_course);
        category = (TextView) thisView.findViewById(R.id.tx_category);


        materialname.setText(Data.getMaterialname());
        discription.setText(Data.getDiscription());
        amount.setText(Data.getAmount());
        course.setText(Data.getCourse());
        category.setText(Data.getOwnerid());

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Sorry this functionality is not yet implemented",Toast.LENGTH_SHORT).show();
            }
        });

        return thisView;
    }

    @Override
    public void onResume() {

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onResume();

    }
}
