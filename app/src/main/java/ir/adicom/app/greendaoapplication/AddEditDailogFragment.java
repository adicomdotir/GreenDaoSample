package ir.adicom.app.greendaoapplication;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.adicom.app.greendaoapplication.Models.Event;
import ir.adicom.app.greendaoapplication.Models.EventDao;
import ir.adicom.app.greendaoapplication.Models.Province;
import ir.adicom.app.greendaoapplication.Models.ProvinceDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEditDailogFragment extends DialogFragment {

    private EditText mEditText;
    private long id;

    public AddEditDailogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_add_edit_dailog, container, false);
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
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if(getActivity() instanceof EventActivity) {
            final EventDao eventDao = ((DemoApp) getActivity().getApplication()).getDaoSession().getEventDao();
            Event event = null;
            if (id > 0) {
                event = eventDao.load(id);
                etTitle.setText(event.getName());
                btnAdd.setText(R.string.edit_text);
            }
            final EventActivity eventActivity = (EventActivity) getActivity();
            final Event finalEvent = event;
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id > 0) {
                        finalEvent.setName(etTitle.getText().toString());
                        eventDao.update(finalEvent);
                    } else {
                        eventDao.insert(new Event(null, etTitle.getText().toString()));
                    }
                    eventActivity.onResume();
                    dismiss();
                }
            });
        }
        if(getActivity() instanceof ProvinceActivity) {
            final ProvinceDao provinceDao = ((DemoApp) getActivity().getApplication()).getDaoSession().getProvinceDao();
            Province province = null;
            if (id > 0) {
                province = provinceDao.load(id);
                etTitle.setText(province.getTitle());
                btnAdd.setText(R.string.edit_text);
            }
            final ProvinceActivity eventActivity = (ProvinceActivity) getActivity();
            final Province finalProvince = province;
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id > 0) {
                        finalProvince.setTitle(etTitle.getText().toString());
                        provinceDao.update(finalProvince);
                    } else {
                        provinceDao.insert(new Province(null, etTitle.getText().toString()));
                    }
                    eventActivity.onResume();
                    dismiss();
                }
            });
        }
    }


}
