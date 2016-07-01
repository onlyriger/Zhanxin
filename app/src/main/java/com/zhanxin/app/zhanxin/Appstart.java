package com.zhanxin.app.zhanxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

/**
 * Created by riger on 2016/7/1.
 */
public class Appstart extends Activity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appstart);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);        //去掉标题栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //WindowManager.LayoutParams.FLAG_FULLSCREEN);        //全屏显示
        //Toast.makeText(getApplicationContext(),"shabi",Toast.LENGTH_LONG).show();
        //overridePendingTransition(R.anim.hyperspace_in,R.anim.hyperspace_out);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Appstart.this,Welcome.class);
                startActivity(intent);
                Appstart.this.finish();
            }
        },1000);
    }
}
