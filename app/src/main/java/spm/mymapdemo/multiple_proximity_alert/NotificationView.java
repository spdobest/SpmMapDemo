package spm.mymapdemo.multiple_proximity_alert;

/**
 * Created by webwerks on 3/2/16.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import spm.mymapdemo.R;

public class NotificationView extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        TextView tv = (TextView) findViewById(R.id.tv_notification);
        Bundle data = getIntent().getExtras();
        tv.setText(data.getString("content"));
    }
}