package ir.adicom.app.greendaoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import ir.adicom.app.greendaoapplication.Models.Event;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView) findViewById(R.id.txt);

        List<Event> eventList = ((DemoApp)getApplication()).getDaoSession()
                .getEventDao().loadAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Event event : eventList) {
            stringBuilder.append(event.getName() + "\n");
        }

        txt.setText(stringBuilder.toString());

    }
}
