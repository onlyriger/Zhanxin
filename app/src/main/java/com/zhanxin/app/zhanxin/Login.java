package com.zhanxin.app.zhanxin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by riger on 2016/7/2.
 */
public class Login extends Activity {
    private EditText mUser;         //帐号编辑框
    private EditText mPassword;     //密码编辑框

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


    }
}
