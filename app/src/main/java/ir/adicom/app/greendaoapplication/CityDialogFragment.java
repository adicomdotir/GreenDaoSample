package ir.adicom.app.greendaoapplication;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.adicom.app.greendaoapplication.Models.City;
import ir.adicom.app.greendaoapplication.Models.CityDao;
import ir.adicom.app.greendaoapplication.Models.EventDao;
import ir.adicom.app.greendaoapplication.Models.Province;
import ir.adicom.app.greendaoapplication.Models.ProvinceDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityDialogFragment extends DialogFragment {

    private long id;
    private Province tempProvince;

    public CityDialogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_city_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        try {
            id = getArguments().getLong("id", 0L);
        } catch (Exception e) {
            id = 0;
        }
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button dismiss = (Button) view.findViewById(R.id.btn_cancel);
        Button btnAdd = (Button) view.findViewById(R.id.btn_add);
        final EditText etTitle = (EditText) view.findViewById(R.id.et_title);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        ProvinceDao provinceDao = ((DemoApp) getActivity().getApplication()).getDaoSession().getProvinceDao();
        final List<Province> list = provinceDao.loadAll();
        List<String> categories = new ArrayList<>();
        for (Province p :list) {
            categories.add(p.getTitle());
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                for (Province p :list) {
                    if(p.getTitle().equals(item)) {
                        tempProvince = p;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        final CityDao cityDao = ((DemoApp) getActivity().getApplication()).getDaoSession().getCityDao();
        City city = null;
        if (id > 0) {
            city = cityDao.load(id);
            Province province = provinceDao.load(city.getProvinceId());
            for (int i = 0; i < categories.size(); i++) {
                if (province.getTitle().equals(categories.get(i))) {
                    spinner.setSelection(i);
                }
            }
            etTitle.setText(city.getTitle());
            btnAdd.setText(R.string.edit_text);
        }
        final CityActivity cityActivity = (CityActivity) getActivity();
        final City finalCity = city;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id > 0) {
                    finalCity.setProvinceId(tempProvince.getId());
                    finalCity.setTitle(etTitle.getText().toString());
                    cityDao.update(finalCity);
                } else {
                    cityDao.insert(new City(null, etTitle.getText().toString(), tempProvince.getId()));
                }
                cityActivity.onResume();
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
