package ir.adicom.app.greendaoapplication;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ir.adicom.app.greendaoapplication.Models.City;
import ir.adicom.app.greendaoapplication.Models.CityDao;
import ir.adicom.app.greendaoapplication.Models.Event;
import ir.adicom.app.greendaoapplication.Models.EventDao;

public class CityActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private CityDao cityDao;
    private String str[];
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Cities");

        cityDao = ((DemoApp)getApplication()).getDaoSession().getCityDao();
        init();
        listView = (ListView) findViewById(R.id.lv_event);
        if (listView != null) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    City city = cityDao.queryBuilder()
                            .where(CityDao.Properties.Title.eq(str[position])).list().get(0);
                    FragmentManager fm = getSupportFragmentManager();
                    CityDialogFragment cityDialogFragment = new CityDialogFragment();
                    Bundle args = new Bundle();
                    args.putLong("id", city.getId());
                    cityDialogFragment.setArguments(args);
                    cityDialogFragment.show(fm, "fragment_city");
                }
            });
        }
    }

    private void init() {
        List<City> cityList = cityDao.loadAll();
        str = new String[cityList.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = cityList.get(i).getTitle();
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
                CityDialogFragment cityDialogFragment = new CityDialogFragment();
                cityDialogFragment.show(fm, "fragment_city");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
