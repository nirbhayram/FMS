package sen.mukul.com.fms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMaterial extends AppCompatActivity {
    int to = 0;
    int course = 0;
    Button submit;
    EditText materialname,discription,amount;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        progressDialog = new ProgressDialog(this);
        submit = (Button) this.findViewById(R.id.submit);
        materialname = (EditText) this.findViewById(R.id.materialname);
        discription = (EditText) this.findViewById(R.id.discription);
        amount = (EditText) this.findViewById(R.id.amount);
        Spinner spinner1 = (Spinner) findViewById(R.id.coursespinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.course_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkVaild()) {
                    progressDialog.setMessage("Loading");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    addMaterial();
                    progressDialog.dismiss();
                    onBackPressed();
                }
            }
        });

    }


    public void addMaterial(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("MATERIAL").child("BOOK");
        myRef = myRef.push();
        myRef.child("MATERIALNAME").setValue(materialname.getText().toString());
        myRef.child("DISCRIPTION").setValue(discription.getText().toString());
        myRef.child("AMOUNT").setValue(amount.getText().toString());
        myRef.child("OWNERID").setValue(User.getEmail());

        if (course == 0){
            myRef.child("COURSE").setValue("VEGETABLES");
        }
        else if (course == 1){
            myRef.child("COURSE").setValue("FRUITS");
        }
        else if (course ==2){
            myRef.child("COURSE").setValue("GRAINS");
        }

    }

    public boolean checkVaild(){
        if (materialname.getText().toString().equals("")){
            Toast.makeText(AddMaterial.this,"Please fill all entry",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (discription.getText().toString().equals("")){
            Toast.makeText(AddMaterial.this,"Please fill all entry",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (amount.getText().toString().equals("")){
            Toast.makeText(AddMaterial.this,"Please fill all entry",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
