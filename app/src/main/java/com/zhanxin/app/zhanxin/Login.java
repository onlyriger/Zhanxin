package com.zhanxin.app.zhanxin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by riger on 2016/7/2.
 */
public class Login extends Activity {
    private EditText mUser;         //帐号编辑框
    private EditText mPassword;     //密码编辑框

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mUser = (EditText) findViewById(R.id.login_user_edit);
        mPassword = (EditText) findViewById(R.id.login_passwd_edit);
    }

    public void login_mainweixin(View v) {
        String user_str = mUser.getText().toString();
        String password_str = mPassword.getText().toString();
        if ("weixin".equals(user_str) && "123".equals(password_str)) {
            Intent intent = new Intent();
            intent.setClass(Login.this, LoadingActivity.class);
            startActivity(intent);
        } else if ("".equals(user_str) || "".equals(password_str)) {
            new AlertDialog.Builder(Login.this)
                    .setIcon(getResources().getDrawable(R.drawable.login_error_icon))
            .setTitle("登录错误")
            .setMessage("微信帐号或者密码不能为空,\n 请输入后再登录！")
            .create().show();
        }

        Intent intent = new Intent();
        intent.setClass(Login.this,Whatsnew.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void login_back(View v){
        this.finish();
    }

    public void login_pw(View v){
        Uri uri = Uri.parse("http://3g.qq.com");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
        //Intent intent = new Intent();
        //intent.setClass(Login.this,Whatsnew.class);
        //startActivity(intent);
    }
}
