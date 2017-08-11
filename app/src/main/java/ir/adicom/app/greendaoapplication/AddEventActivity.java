package ir.adicom.app.greendaoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ir.adicom.app.greendaoapplication.Models.Event;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final EditText etTitle = (EditText) findViewById(R.id.et_title);

        Button btnCancel = (Button) findViewById(R.id.btn_cancel);
        if (btnCancel != null) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        Button btnAdd = (Button) findViewById(R.id.btn_add);
        if (btnAdd != null) {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((DemoApp)getApplication()).getDaoSession().getEventDao()
                            .insert(new Event(null, etTitle != null ? etTitle.getText().toString() : null));
                    finish();
                }
            });
        }
    }
}
