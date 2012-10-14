package au.com.dius.resilience.ui.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import au.com.dius.resilience.R;
import au.com.dius.resilience.model.Incident;

import java.util.List;

/**
 * @author georgepapas
 */
public class ListViewAdapter extends ArrayAdapter<Incident> {

  private List<Incident> incidents;

  public ListViewAdapter(Context context, int textViewResourceId, List<Incident> incidents) {
    super(context, textViewResourceId, incidents);
    this.incidents = incidents;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View rowView = inflater.inflate(R.layout.fragment_incident_list_view_item, null);

    Incident incident = incidents.get(position);

    TextView nameField = (TextView) rowView.findViewById(R.id.list_view_item_name);
    nameField.setText(incident.getName());

    TextView reportedTime = (TextView) rowView.findViewById(R.id.list_view_item_time_reported);
    reportedTime.setText(DateUtils.getRelativeDateTimeString(
            getContext(),
            incident.getDateCreated().longValue(),
            DateUtils.SECOND_IN_MILLIS,
            DateUtils.YEAR_IN_MILLIS, 0));

    return rowView;
  }


}