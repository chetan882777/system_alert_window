package in.jvapps.system_alert_window;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import in.jvapps.system_alert_window.utils.Commons;
import in.jvapps.system_alert_window.views.BodyView;
import in.jvapps.system_alert_window.views.FooterView;
import in.jvapps.system_alert_window.views.HeaderView;

import static in.jvapps.system_alert_window.utils.Constants.INTENT_EXTRA_PARAMS_MAP;
import static in.jvapps.system_alert_window.utils.Constants.KEY_BODY;
import static in.jvapps.system_alert_window.utils.Constants.KEY_FOOTER;
import static in.jvapps.system_alert_window.utils.Constants.KEY_HEADER;

public class BubbleActivity extends AppCompatActivity {

    private static final String TAG = "BubbleActivity";

    private  LinearLayout bubbleLayout;
    private HashMap<String, Object> paramsMap;

    private Context mContext;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        mContext = this;
        bubbleLayout = findViewById(R.id.bubbleLayout);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            paramsMap = (HashMap<String, Object>) intent.getSerializableExtra(INTENT_EXTRA_PARAMS_MAP);
            configureUI();
        }
    }

    /**
     *
     *  rows=[
     *      {
     *          padding=null,
     *          margin=null,
     *          decoration=null,
     *          columns=[
     *              {
     *                  padding={
     *                      right=8,
     *                      top=6,
     *                      left=8,
     *                      bottom=6
     *                      },
     *                  margin={
     *                      right=0,
     *                      top=4,
     *                      left=0,
     *                      bottom=0
     *                      },
     *                  text={
     *                      padding=null,
     *                      fontSize=12.0,
     *                      text=fcm message,
     *                      textColor=3707764736,
     *                      fontWeight=bold
     *                      },
     *                  decoration={
     *                      borderColor=4294967295,
     *                      borderRadius=25.0,
     *                      endColor=null,
     *                      borderWidth=0,
     *                      startColor=520093696
     *                      }
     *               }
     *        ],
     *        gravity=center
     *    },
     *    {
     *          padding=null,
     *          margin=null,
     *          decoration=null,
     *          columns=[
     *              {
     *                  padding=
     *                      {
     *                          right=8,
     *                          top=6,
     *                          left=8,
     *                          bottom=6
     *                      },
     *                 margin=
     *                      {
     *                          right=0,
     *                          top=4,
     *                          left=0,
     *                          bottom=0
     *                      },
     *                 text={
     *                      padding=null,
     *                      fontSize=12.0,
     *                      text=#order id,
     *                      textColor=3707764736,
     *                      fontWeight=bold
     *                      },
     *                 decoration={
     *                      borderColor=4294967295,
     *                      borderRadius=25.0,
     *                      endColor=null,
     *                      borderWidth=0,
     *                      startColor=520093696
     *                      }
     *              }
     *       ],
     *       gravity=center
     *   }
     *  ],
     *  padding={right=16, top=12, left=16, bottom=12}, decoration=null}
     *
     */

    void configureUI(){
        Map<String, Object> headersMap = Commons.getMapFromObject(paramsMap, KEY_HEADER);
        Map<String, Object> bodyMap = Commons.getMapFromObject(paramsMap, KEY_BODY);
        Log.d(TAG, "configureUI: ====================== body map: " + bodyMap);
        Map<String, Object> footerMap = Commons.getMapFromObject(paramsMap, KEY_FOOTER);
        LinearLayout headerView = new HeaderView(mContext, headersMap).getView();
        LinearLayout bodyView = new BodyView(mContext, bodyMap).getView();
        LinearLayout footerView = new FooterView(mContext, footerMap, bodyMap).getView();

        bubbleLayout.setBackgroundColor(Color.WHITE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        bubbleLayout.setLayoutParams(params);
        bubbleLayout.addView(headerView);
        bubbleLayout.addView(bodyView);
        bubbleLayout.addView(footerView);
    }
}
