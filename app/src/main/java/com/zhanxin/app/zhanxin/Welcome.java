package com.zhanxin.app.zhanxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by riger on 2016/7/2.
 */
public class Welcome extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void welcome_login(View v){
        Intent intent = new Intent(Welcome.this,Login.class);
        startActivity(intent);
        //this.finish();
    }

    public void welcom_register(View v){
        Intent intent = new Intent(Welcome.this,MainWeixin.class);
        startActivity(intent);
        //this.finish();
    }
}
