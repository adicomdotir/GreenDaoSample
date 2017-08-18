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
import ir.adicom.app.greendaoapplication.Models.Province;
import ir.adicom.app.greendaoapplication.Models.ProvinceDao;
import ir.adicom.app.greendaoapplication.Models.Register;
import ir.adicom.app.greendaoapplication.Models.RegisterDao;

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private RegisterDao registerDao;
    private String str[];
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Registers");

        registerDao = ((DemoApp)getApplication()).getDaoSession().getRegisterDao();
        init();
        listView = (ListView) findViewById(R.id.lv_event);
        if (listView != null) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Register register = registerDao.queryBuilder()
                            .where(RegisterDao.Properties.FullName.eq(str[position])).list().get(0);
                    FragmentManager fm = getSupportFragmentManager();
                    RegisterDialogFragment registerDialogFragment = new RegisterDialogFragment();
                    Bundle args = new Bundle();
                    args.putLong("id", register.getId());
                    registerDialogFragment.setArguments(args);
                    registerDialogFragment.show(fm, "fragment_register");
                }
            });
        }
    }

    private void init() {
        List<Register> registerList = registerDao.loadAll();
        str = new String[registerList.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = registerList.get(i).getFullName();
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
                RegisterDialogFragment registerDialogFragment = new RegisterDialogFragment();
                registerDialogFragment.show(fm, "fragment_register");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
