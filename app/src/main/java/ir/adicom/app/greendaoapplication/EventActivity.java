package ir.adicom.app.greendaoapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ir.adicom.app.greendaoapplication.Models.Event;
import ir.adicom.app.greendaoapplication.Models.EventDao;

public class EventActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private EventDao eventDao;
    private String str[];
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        eventDao = ((DemoApp)getApplication()).getDaoSession().getEventDao();
        init();
        listView = (ListView) findViewById(R.id.lv_event);
        if (listView != null) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Event event = eventDao.queryBuilder()
                            .where(EventDao.Properties.Name.eq(str[position])).list().get(0);
                    FragmentManager fm = getSupportFragmentManager();
                    AddEditDailogFragment addEditDailogFragment = new AddEditDailogFragment();
                    Bundle args = new Bundle();
                    args.putLong("id", event.getId());
                    addEditDailogFragment.setArguments(args);
                    addEditDailogFragment.show(fm, "fragment_event");
                }
            });
        }
    }

    private void init() {
        List<Event> eventList = eventDao.loadAll();
        str = new String[eventList.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = eventList.get(i).getName();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, str);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                FragmentManager fm = getSupportFragmentManager();
                AddEditDailogFragment addEditDailogFragment = new AddEditDailogFragment();
                addEditDailogFragment.show(fm, "fragment_event");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
