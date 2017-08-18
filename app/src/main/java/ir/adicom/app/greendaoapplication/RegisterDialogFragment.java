package ir.adicom.app.greendaoapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.adicom.app.greendaoapplication.Models.City;
import ir.adicom.app.greendaoapplication.Models.CityDao;
import ir.adicom.app.greendaoapplication.Models.Event;
import ir.adicom.app.greendaoapplication.Models.EventDao;
import ir.adicom.app.greendaoapplication.Models.Province;
import ir.adicom.app.greendaoapplication.Models.ProvinceDao;
import ir.adicom.app.greendaoapplication.Models.Register;
import ir.adicom.app.greendaoapplication.Models.RegisterDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterDialogFragment extends DialogFragment {

    private long id;
    private Province tempProvince;
    private City tempCity;
    private Event tempEvent;


    public RegisterDialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        try {
            id = getArguments().getLong("id", 0L);
        } catch (Exception e) {
            id = 0;
        }
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button dismiss = (Button) view.findViewById(R.id.btn_cancel);
        Button btnAdd = (Button) view.findViewById(R.id.btn_add);
        final EditText etTitle = (EditText) view.findViewById(R.id.et_title);
        Spinner spinnerEvent = (Spinner) view.findViewById(R.id.spinnerEvent);
        final Spinner spinnerCity = (Spinner) view.findViewById(R.id.spinnerCity);
        Spinner spinnerProvince = (Spinner) view.findViewById(R.id.spinnerProvince);

        final CityDao cityDao = ((DemoApp) getActivity().getApplication()).getDaoSession().getCityDao();
        final List<String> cityCategories = new ArrayList<>();

        final ProvinceDao provinceDao = ((DemoApp) getActivity().getApplication()).getDaoSession().getProvinceDao();
        List<Province> list = provinceDao.loadAll();
        final List<String> provinceCategories = new ArrayList<>();
        for (Province p :list) {
            provinceCategories.add(p.getTitle());
        }
        ArrayAdapter<String> dataAdapterProvince = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, provinceCategories);
        dataAdapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(dataAdapterProvince);
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Province province = provinceDao.queryBuilder()
                        .where(ProvinceDao.Properties.Title.eq(provinceCategories.get(position)))
                        .list().get(0);
                List<City> cityList = cityDao.queryBuilder()
                        .where(CityDao.Properties.ProvinceId.eq(province.getId())).list();
                cityCategories.clear();
                for (City item :cityList) {
                    cityCategories.add(item.getTitle());
                }
                ArrayAdapter<String> dataAdapterCity = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, cityCategories);
                dataAdapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCity.setAdapter(dataAdapterCity);
                tempProvince = province;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EventDao eventDao = ((DemoApp) getActivity().getApplication()).getDaoSession().getEventDao();
        List<Event> eventList = eventDao.loadAll();
        final List<String> eventCategories = new ArrayList<>();
        for (Event item :eventList) {
            eventCategories.add(item.getName());
        }
        ArrayAdapter<String> dataAdapterEvent = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, eventCategories);
        dataAdapterEvent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvent.setAdapter(dataAdapterEvent);
        spinnerEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempEvent = eventDao.queryBuilder()
                        .where(EventDao.Properties.Name.eq(eventCategories.get(position)))
                        .list().get(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempCity = cityDao.queryBuilder()
                        .where(CityDao.Properties.Title.eq(cityCategories.get(position)))
                        .list().get(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final RegisterDao registerDao = ((DemoApp) getActivity().getApplication())
                .getDaoSession().getRegisterDao();
        Register register = null;
        if (id > 0) {
            register = registerDao.load(id);
            Province province = provinceDao.load(register.getProvinceId());
            for (int i = 0; i < provinceCategories.size(); i++) {
                if (province.getTitle().equals(provinceCategories.get(i))) {
                    spinnerProvince.setSelection(i);
                }
            }
            Event event = eventDao.load(register.getEventId());
            for (int i = 0; i < eventCategories.size(); i++) {
                if (event.getName().equals(eventCategories.get(i))) {
                    spinnerEvent.setSelection(i);
                }
            }
            City city = cityDao.load(register.getCityId());
            for (int i = 0; i < cityCategories.size(); i++) {
                if (city.getTitle().equals(cityCategories.get(i))) {
                    spinnerCity.setSelection(i);
                }
            }
            etTitle.setText(register.getFullName());
            btnAdd.setText(R.string.edit_text);
        }
        final RegisterActivity registerActivity = (RegisterActivity) getActivity();
        final Register finalRegister = register;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id > 0) {
                    finalRegister.setProvinceId(tempProvince.getId());
                    finalRegister.setCityId(tempCity.getId());
                    finalRegister.setEventId(tempEvent.getId());
                    finalRegister.setFullName(etTitle.getText().toString());
                    registerDao.update(finalRegister);
                } else {
                    registerDao.insert(new Register(null, etTitle.getText().toString(), tempProvince.getId(), tempCity.getId(), tempEvent.getId()));
                }
                registerActivity.onResume();
                dismiss();
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
