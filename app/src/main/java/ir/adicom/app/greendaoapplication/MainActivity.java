package ir.adicom.app.greendaoapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ir.adicom.app.greendaoapplication.Models.Event;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView txt = (TextView) findViewById(R.id.txt);

        List<Event> eventList = ((DemoApp)getApplication()).getDaoSession()
                .getEventDao().loadAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Event event : eventList) {
            stringBuilder.append(event + "\n");
        }

        txt.setText(stringBuilder.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_addevent:
                startActivity(new Intent(this, AddEventActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
