package ir.adicom.app.greendaoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ir.adicom.app.greendaoapplication.Models.City;
import ir.adicom.app.greendaoapplication.Models.CityDao;
import ir.adicom.app.greendaoapplication.Models.Event;
import ir.adicom.app.greendaoapplication.Models.EventDao;
import ir.adicom.app.greendaoapplication.Models.Province;
import ir.adicom.app.greendaoapplication.Models.ProvinceDao;
import ir.adicom.app.greendaoapplication.Models.Register;
import ir.adicom.app.greendaoapplication.Models.RegisterDao;

/**
 *
 * Created by adicom on 8/18/17.
 */

public class CustomRegisterAdapter extends ArrayAdapter<Register> implements View.OnClickListener {

    private ArrayList<Register> dataSet;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtFullName;
        TextView txtCity;
        TextView txtEvent;
    }

    public CustomRegisterAdapter(ArrayList<Register> data, Context context) {
        super(context, R.layout.register_row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Register register = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.register_row_item, parent, false);
            viewHolder.txtFullName = (TextView) convertView.findViewById(R.id.txt_fullname);
            viewHolder.txtCity = (TextView) convertView.findViewById(R.id.txt_city);
            viewHolder.txtEvent = (TextView) convertView.findViewById(R.id.txt_event);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        EventDao eventDao = ((DemoApp)mContext).getDaoSession().getEventDao();
        ProvinceDao provinceDao = ((DemoApp)mContext).getDaoSession().getProvinceDao();
        CityDao cityDao = ((DemoApp)mContext).getDaoSession().getCityDao();
        Event event = eventDao.load(register.getEventId());
        City city = cityDao.load(register.getCityId());
        Province province = provinceDao.load(register.getProvinceId());

        viewHolder.txtFullName.setText(register.getFullName());
        viewHolder.txtCity.setText(province.getTitle() + ", " + city.getTitle());
        viewHolder.txtEvent.setText(event.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
