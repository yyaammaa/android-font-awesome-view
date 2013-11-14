
package com.stackrmobile.fontawesomeview.sample;

import com.stackrmobile.fontawesomeview.FontAwesome;
import com.stackrmobile.fontawesomeview.IconView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Map<String, String> map = FontAwesome.getCharMap();
    ArrayList<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>();
    list.addAll(map.entrySet());

    GridView gridView = (GridView) findViewById(R.id.activity_main_grid_view);
    IconAdapter adapter = new IconAdapter(this);
    gridView.setAdapter(adapter);
    adapter.setList(list);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return false;
  }

  private static String convertToUnicode(String original) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < original.length(); i++) {
      sb.append(String.format("\\u%04X", Character.codePointAt(original, i)));
    }
    String unicode = sb.toString();
    return unicode;
  }

  private class IconAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Map.Entry<String, String>> mList;

    private IconAdapter(Activity activity) {
      mContext = activity;
      mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      mList = new ArrayList<Map.Entry<String, String>>();
    }

    private void setList(ArrayList<Map.Entry<String, String>> list) {
      mList = list;
    }

    @Override
    public int getCount() {
      if (mList == null) {
        return 0;
      }
      return mList.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int position) {
      if (mList == null) {
        return null;
      }
      return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
      return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Map.Entry<String, String> entry = getItem(position);
      ViewHolder holder;
      if (convertView != null) {
        holder = (ViewHolder) convertView.getTag();
      } else {
        convertView = mInflater.inflate(R.layout.grid_view_item, parent, false);
        holder = new ViewHolder(convertView);
        convertView.setTag(holder);
      }

      holder.mIconView.setText(entry.getValue());
      holder.mNameView.setText(entry.getKey());
      holder.mUnicodeView.setText(convertToUnicode(entry.getValue()));

      return convertView;
    }

  }

  private static class ViewHolder {
    IconView mIconView;
    TextView mNameView;
    TextView mUnicodeView;

    private ViewHolder(View view) {
      mIconView = (IconView) view.findViewById(R.id.grid_view_icon_text);
      mNameView = (TextView) view.findViewById(R.id.grid_view_icon_name);
      mUnicodeView = (TextView) view.findViewById(R.id.grid_view_icon_unicode);
    }
  }

}
