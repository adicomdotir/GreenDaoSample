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

import ir.adicom.app.greendaoapplication.Models.Event;
import ir.adicom.app.greendaoapplication.Models.EventDao;
import ir.adicom.app.greendaoapplication.Models.Province;
import ir.adicom.app.greendaoapplication.Models.ProvinceDao;

public class ProvinceActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ProvinceDao provinceDao;
    private String str[];
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        provinceDao = ((DemoApp)getApplication()).getDaoSession().getProvinceDao();
        init();
        listView = (ListView) findViewById(R.id.lv_event);
        if (listView != null) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Province province = provinceDao.queryBuilder()
                            .where(ProvinceDao.Properties.Title.eq(str[position])).list().get(0);
                    FragmentManager fm = getSupportFragmentManager();
                    AddEditDailogFragment addEditDailogFragment = new AddEditDailogFragment();
                    Bundle args = new Bundle();
                    args.putLong("id", province.getId());
                    addEditDailogFragment.setArguments(args);
                    addEditDailogFragment.show(fm, "fragment_province");
                }
            });
        }

    }

    private void init() {
        List<Province> provinceList = provinceDao.loadAll();
        str = new String[provinceList.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = provinceList.get(i).getTitle();
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
            case R.id.action_addevent:
                FragmentManager fm = getSupportFragmentManager();
                AddEditDailogFragment addEditDailogFragment = new AddEditDailogFragment();
                addEditDailogFragment.show(fm, "fragment_province");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
