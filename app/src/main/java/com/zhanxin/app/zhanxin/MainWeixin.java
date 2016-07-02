package com.zhanxin.app.zhanxin;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.ImageWriter;
import android.os.Bundle;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * Created by riger on 2016/7/2.
 */
public class MainWeixin extends Activity {
    public static MainWeixin instance = null;
    private ViewPager mTabPager;
    private ImageView mTabImg;      //动画图片
    private ImageView mTab1,mTab2,mTab3,mTab4;
    private int zero = 0;           //动画图片偏移量
    private int currIndex = 0;      //当前页卡编号
    private int one;
    private int two;
    private int three;
    private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;
    private boolean menu_display = false;
    private PopupWindow menuWindow;
    private LayoutInflater inflater;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        instance = this;
        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

        mTab1 = (ImageView)findViewById(R.id.img_weixin);
        mTab2 = (ImageView)findViewById(R.id.img_address);
        mTab3 = (ImageView)findViewById(R.id.img_friends);
        mTab4 = (ImageView)findViewById(R.id.img_settings);
        mTabImg = (ImageView)findViewById(R.id.img_tab_now);

        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));

        Display currDisplay = getWindowManager().getDefaultDisplay();   //获取屏幕当前的分辨率
        int displayWidth = currDisplay.getWidth();
        int displayHeight = currDisplay.getHeight();
        one = displayWidth / 4;     //设置水平动画平移大小
        two = one * 2;
        three = one * 3;
        //Log.i("info","获取的屏幕分辨率为“ + one + two + three + "X" + displayHeight);
        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.main_tab_weixin,null);
        View view2 = mLi.inflate(R.layout.main_tab_address,null);
        View view3 = mLi.inflate(R.layout.main_tab_friends,null);
        View view4 = mLi.inflate(R.layout.main_tab_settings,null);

        //每个页面的View数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        //填充ViewPager的数据适配器
        PagerAdapter mPageAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                //return false;
                return view == object;
            }

            @Override
            public void destroyItem(View container, int position, Object object){
                ((ViewPager)container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(View container, int position){
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };
        mTabPager.setAdapter(mPageAdapter);
    }

    /**
     * 图标单击监听
     */
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0;

        public MyOnClickListener(int i){
            index = i;
        }
        @Override
        public void onClick(View v) {
            mTabPager.setCurrentItem(index);
        }
    };

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            switch (position){
                case 0:
                    mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
                    if (currIndex == 1){
                        animation = new TranslateAnimation(one,0,0,0);
                        mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
                    } else if(currIndex == 2){
                        animation = new TranslateAnimation(two,0,0,0);
                        mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
                    } else if(currIndex == 3){
                        animation = new TranslateAnimation(three,0,0,0);
                        mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
                    }
                    break;
                case 1:
                    mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_pressed));
                    if (currIndex == 0){
                        animation = new TranslateAnimation(zero,one,0,0);
                        mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
                    } else if(currIndex == 2){
                        animation = new TranslateAnimation(two,one,0,0);
                        mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
                    } else if (currIndex == 3){
                        animation = new TranslateAnimation(three,one,0,0);
                        mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
                    }
                    break;
                case 2:
                    mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_pressed));
                    if (currIndex == 0){
                        animation = new TranslateAnimation(zero,two,0,0);
                        mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
                    } else if(currIndex == 1){
                        animation = new TranslateAnimation(one,two,0,0);
                        mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
                    } else if(currIndex == 3){
                        animation = new TranslateAnimation(three,two,0,0);
                        mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
                    }
                    break;
                case 3:
                    mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_pressed));
                    if (currIndex == 0){
                        animation = new TranslateAnimation(zero,three,0,0);
                        mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
                    } else if(currIndex == 1){
                        animation = new TranslateAnimation(one,three,0,0);
                        mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
                    } else if(currIndex == 2){
                        animation = new TranslateAnimation(two,three,0,0);
                        mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
                    }
                    break;
                default:
                    break;
            }
            currIndex = position;
            animation.setFillAfter(true);   //True:图片停在动画结束位置
            animation.setDuration(150);     //动画持续时间
            mTabImg.startAnimation(animation);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (menu_display) {
                menuWindow.dismiss();
                menu_display = false;
            } else {
                Intent intent = new Intent();
                intent.setClass(MainWeixin.this, Exit.class);
                startActivity(intent);
            }
        }
     else if(keyCode == KeyEvent.KEYCODE_MENU){//获取 Menu键
            if (!menu_display){
                //获取LayoutInflater实例
                inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
                //这里的main布局是在inflate中加入的，以前都是直接调用this.setContentView()
                //该方法返回的是一个View的对象，是布局中的根
                layout = inflater.inflate(R.layout.main_menu,null);
                menuWindow = new PopupWindow(layout, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                //menuWindow.showAsDropDown(layout);    //设置弹出效果
                //menuWindow.showAsDropDown(null,0,layout.getHeight());
                menuWindow.showAtLocation(this.findViewById(R.id.MainWeixin),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,0,0);    //设置layout在PopupWindow中显示的位置
                //获取main控件
                mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
                mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);

                //下面对每一个Layout进行单击事件的注册
                //例如，单击某个MenuItem时，其背景色改变
                //事先准备好一些背景图片或者颜色
                mCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(Main.this,"退出”，
                        //Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(MainWeixin.this,Exit.class);
                        menuWindow.dismiss();
                    }
                });
                menu_display = true;
            } else{
                //如果当前已经为显示状态，则隐藏起来
                menuWindow.dismiss();
                menu_display = false;
            }
            return false;
        }
        return false;
    }

    //设置标题栏右侧按钮的作用
    public void btnmainright(View v){
        Intent intent = new Intent(MainWeixin.this,MainTopRightDialog.class);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(),"单击了功能按钮”,
        //Toast.LENGTH_LONG.show();
    }

    public void startchat(View v){
        Intent intent = new Intent(MainWeixin.this,ChatActivity.class);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(),"登录成功",
        //Toast.LENGTH_LONG.show();
    }

    public void exit_settings(View v){
        Intent intent = new Intent(MainWeixin.this,ExitFromSettings.class);
        startActivity(intent);
    }

    public void btn_shake(View v){
        Intent intent = new Intent(MainWeixin.this,ShakeActivity.class);
        startActivity(intent);
    }
}
