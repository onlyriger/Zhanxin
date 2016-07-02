package com.zhanxin.app.zhanxin;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by riger on 2016/7/2.
 */
public class Exit extends Activity {
    //private MyDialog dialog
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //dialog = new MyDialog(this);
        setContentView(R.layout.exit_dialog);
        layout = (LinearLayout)findViewById(R.id.exit_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }

    public void exitbutton1(View v){
        this.finish();
    }

    public void exitbutton0(View v){
        this.finish();
        MainWeixin.instance.finish();   //关闭Main这个Activity
    }
}