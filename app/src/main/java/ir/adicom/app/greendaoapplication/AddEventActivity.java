package ir.adicom.app.greendaoapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.adicom.app.greendaoapplication.Models.Event;
import ir.adicom.app.greendaoapplication.Models.EventDao;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final EventDao eventDao = ((DemoApp)getApplication()).getDaoSession().getEventDao();
        final EditText etTitle = (EditText) findViewById(R.id.et_title);
        Button btnCancel = (Button) findViewById(R.id.btn_cancel);
        Button btnAdd = (Button) findViewById(R.id.btn_add);

        final Intent intent = getIntent();
        if (intent.getExtras() != null) {
            final Event event = eventDao.load(intent.getExtras().getLong("id"));
            etTitle.setText(event.getName());
            btnAdd.setText("Edit");
            if (btnAdd != null) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        event.setName(etTitle.getText().toString());
                        eventDao.update(event);
                        finish();
                    }
                });
            }
        } else {
            btnAdd.setText("Add");
            if (btnAdd != null) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventDao.insert(new Event(null, etTitle != null ? etTitle.getText().toString() : null));
                        finish();
                    }
                });
            }
        }

        if (btnCancel != null) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
