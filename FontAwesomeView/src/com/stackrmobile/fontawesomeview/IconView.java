
package com.stackrmobile.fontawesomeview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * 使い方は普通のTextViewと同じです <br />
 * 使用するプロジェクトのassets以下に fontawesome-webfont.ttf を置いてください
 */
public class IconView extends TextView {

  private static final String TAG = "IconView";
  private static Typeface sTypeface;

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
        sTypeface = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
      } catch (Exception e) {
        Log.e(TAG, "Failed to load font: " + e.getMessage());
        sTypeface = Typeface.DEFAULT;
      }
    }
  }

}
