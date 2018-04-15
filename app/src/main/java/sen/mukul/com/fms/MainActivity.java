package sen.mukul.com.fms;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FragmentBook fragmentBook;
    FragmentArticle fragmentArticle;
    FragmentAccount fragmentQPaper;
    LinearLayout book,article,lnotes,qpaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book = (LinearLayout) findViewById(R.id.book);
        article = (LinearLayout) findViewById(R.id.article);
        qpaper = (LinearLayout) findViewById(R.id.qpaper);
        book.setOnClickListener(this);
        article.setOnClickListener(this);
        qpaper.setOnClickListener(this);
        onClick(book);
    }

    @Override
    public void onClick(View view) {
        getSupportFragmentManager().popBackStack();
        int id = view.getId();
        if (id == R.id.book){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            fragmentBook = new FragmentBook();
            transaction.replace(R.id.container,fragmentBook);
            transaction.addToBackStack(null);
            transaction.commit();
            book.setBackgroundColor(getResources().getColor(R.color.gray));
            article.setBackgroundColor(getResources().getColor(R.color.white));
            qpaper.setBackgroundColor(getResources().getColor(R.color.white));
        }
        else if (id == R.id.article){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            fragmentArticle = new FragmentArticle();
            transaction.replace(R.id.container,fragmentArticle);
            transaction.addToBackStack(null);
            transaction.commit();
            article.setBackgroundColor(getResources().getColor(R.color.gray));
            book.setBackgroundColor(getResources().getColor(R.color.white));
            qpaper.setBackgroundColor(getResources().getColor(R.color.white));
        }
        else if (id==R.id.qpaper){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            fragmentQPaper = new FragmentAccount();
            transaction.replace(R.id.container,fragmentQPaper);
            transaction.addToBackStack(null);
            transaction.commit();
            qpaper.setBackgroundColor(getResources().getColor(R.color.gray));
            article.setBackgroundColor(getResources().getColor(R.color.white));
            book.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getFragments().size()==1) {
            this.finish();
        }
        super.onBackPressed();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addcar:
                startActivity(new Intent(MainActivity.this,AddMaterial.class));
                break;
            default:
                break;
        }

        return true;
    }
}
