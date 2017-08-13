package ir.adicom.app.greendaoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_event:
                startActivity(new Intent(MainActivity.this, EventActivity.class));
                break;
            case R.id.btn_province:
                FragmentManager fm = getSupportFragmentManager();
                AddEditDailogFragment addEditDailogFragment = new AddEditDailogFragment();
                addEditDailogFragment.show(fm, "fragment_edit_name");
                break;
        }
    }
}
