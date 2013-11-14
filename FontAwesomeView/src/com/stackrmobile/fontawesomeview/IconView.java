
package com.stackrmobile.fontawesomeview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

public class IconView extends TextView {

  private static final String TAG = "IconView";

  private static Typeface sTypeface;
  private static Map<String, String> sCharMap;

  static {
    sCharMap = FontAwesome.getCharMap();
  }

  public IconView(Context context) {
    super(context);
    init();
  }

  public IconView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    loadFont(getContext());
    setTypeface(sTypeface);
  }

  private static void loadFont(Context context) {
    if (sTypeface == null) {
      try {
        // TODO: project libに置けるかどうか試す
        sTypeface = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
      } catch (Exception e) {
        Log.e(TAG, "Failed to load font: " + e.getMessage());
        sTypeface = Typeface.DEFAULT;
      }
    }
  }

}
