
package com.stackrmobile.fontawesomeview.sample;

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

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    GridView gridView = (GridView) findViewById(R.id.activity_main_grid_view);
    IconAdapter adapter = new IconAdapter(this);
    gridView.setAdapter(adapter);

    // \uF000 ~ \uF196
    ArrayList<String> codeList = new ArrayList<String>();
    for (int i = 0xf000; i <= 0x0f196; i++) {
      String uninode = "\\u" + Integer.toHexString(i);
      codeList.add(uninode);
    }

    adapter.setList(codeList);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return false;
  }

  /**
   * Unicode文字列から元の文字列に変換する ("\u3042" -> "あ") <br />
   * http://qiita.com/sifue/items/039846cf8415efdc5c92
   * 
   * @param unicode
   * @return
   */
  private static String convertToOriginal(String unicode) {
    String[] codeStrs = unicode.split("\\\\u");
    int[] codePoints = new int[codeStrs.length - 1]; // 最初が空文字なのでそれを抜かす
    for (int i = 0; i < codePoints.length; i++) {
      codePoints[i] = Integer.parseInt(codeStrs[i + 1], 16);
    }
    String encodedText = new String(codePoints, 0, codePoints.length);
    return encodedText;
  }

  private class IconAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mList;

    private IconAdapter(Activity activity) {
      mContext = activity;
      mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      mList = new ArrayList<String>();
    }

    private void setList(ArrayList<String> list) {
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
    public String getItem(int position) {
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
      String item = getItem(position);
      ViewHolder holder;
      if (convertView != null) {
        holder = (ViewHolder) convertView.getTag();
      } else {
        convertView = mInflater.inflate(R.layout.grid_view_item, parent, false);
        holder = new ViewHolder(convertView);
        convertView.setTag(holder);
      }

      holder.mIconView.setText(convertToOriginal(item));
      holder.mUnicodeView.setText(item);

      return convertView;
    }

  }

  private static class ViewHolder {
    IconView mIconView;
    TextView mUnicodeView;

    private ViewHolder(View view) {
      mIconView = (IconView) view.findViewById(R.id.grid_view_icon_text);
      mUnicodeView = (TextView) view.findViewById(R.id.grid_view_icon_unicode);
    }
  }

}
