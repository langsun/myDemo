package com.example.sun.mydemo.home;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sun.mydemo.R;
import com.example.sun.mydemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sun on 16/8/9.
 */
public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    @Bind(R.id.tv_title)
    TextView mTitle;
    @Bind(R.id.rl_tab1)
    RelativeLayout mTab1;
    @Bind(R.id.rl_tab2)
    RelativeLayout mTab2;
    @Bind(R.id.rl_tab3)
    RelativeLayout mTab3;
    @Bind(R.id.rl_tab4)
    RelativeLayout mTab4;
    @Bind(R.id.home_vp)
    ViewPager mViewPager;
    @Bind(R.id.iv_image1)
    ImageView mIvHome;
    @Bind(R.id.iv_image2)
    ImageView mIvMessage;
    @Bind(R.id.iv_image3)
    ImageView mIvFind;
    @Bind(R.id.iv_image4)
    ImageView mIvMine;
    @Bind(R.id.tv_text1)
    TextView mTvHome;
    @Bind(R.id.tv_text2)
    TextView mTvMessage;
    @Bind(R.id.tv_text3)
    TextView mTvFind;
    @Bind(R.id.tv_text4)
    TextView mTvMine;

    private List<Fragment> mFragmentList;
    private HomeVpAdapter mHomeVpAdapter;

    private static final int HOME = 0, MESSAGE = 1, FIND = 2, MINE = 3;
    private static final String selectedColor = "#00acff";
    private static final String unSelectedColor = "#82858b";


    private Animation mViewScaleAnimation;
    private Animation mViewShakeAnimation;
    private AnimationSet mViewAnimationSet;

    @Override
    public void setContent() {
        setContentView(R.layout.home_activity);
    }

    @Override
    public void setupView() {

        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragement());
        mFragmentList.add(new MessageFragement());
        mFragmentList.add(new FindFragement());
        mFragmentList.add(new MineFragement());

        mHomeVpAdapter = new HomeVpAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mHomeVpAdapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
        mViewPager.setOnPageChangeListener(this);

        mViewScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.view_scale);
        mViewShakeAnimation = AnimationUtils.loadAnimation(this, R.anim.view_shake);
        mViewAnimationSet = new AnimationSet(false);
        mViewAnimationSet.addAnimation(mViewScaleAnimation);
        mViewAnimationSet.addAnimation(mViewShakeAnimation);

        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
        setTabSelected(HOME);

    }

    @Override
    public void setModel() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTabSelected(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTabSelected(int position) {
        clearSelectedState();
        switch (position) {
            case HOME:
                mIvHome.setImageResource(R.drawable.ip_btn_selected);
                mTvHome.setTextColor(Color.parseColor(selectedColor));
                mTitle.setText(mTvHome.getText());
                setAnimation(mIvHome);
                break;
            case MESSAGE:
                mIvMessage.setImageResource(R.drawable.message_btn_selected);
                mTvMessage.setTextColor(Color.parseColor(selectedColor));
                mTitle.setText(mTvMessage.getText());
                setAnimation(mIvMessage);
                break;
            case FIND:
                mIvFind.setImageResource(R.drawable.discovery_btn_selected);
                mTvFind.setTextColor(Color.parseColor(selectedColor));
                mTitle.setText(mTvFind.getText());
                setAnimation(mIvFind);
                break;
            case MINE:
                mIvMine.setImageResource(R.drawable.mine_btn_selected);
                mTvMine.setTextColor(Color.parseColor(selectedColor));
                mTitle.setText(mTvMine.getText());
                setAnimation(mIvMine);
                break;
        }
    }

    private void clearSelectedState() {
        mIvHome.setImageResource(R.drawable.ip_btn_unselected);
        mTvHome.setTextColor(Color.parseColor(unSelectedColor));
        mIvMessage.setImageResource(R.drawable.message_btn_unselected);
        mTvMessage.setTextColor(Color.parseColor(unSelectedColor));
        mIvFind.setImageResource(R.drawable.discovery_btn_unselected);
        mTvFind.setTextColor(Color.parseColor(unSelectedColor));
        mIvMine.setImageResource(R.drawable.mine_btn_unselected);
        mTvMine.setTextColor(Color.parseColor(unSelectedColor));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_tab1:
                setTabSelected(HOME);
                mViewPager.setCurrentItem(HOME);
                break;
            case R.id.rl_tab2:
                setTabSelected(MESSAGE);
                mViewPager.setCurrentItem(MESSAGE);
                break;
            case R.id.rl_tab3:
                setTabSelected(FIND);
                mViewPager.setCurrentItem(FIND);
                break;
            case R.id.rl_tab4:
                setTabSelected(MINE);
                mViewPager.setCurrentItem(MINE);
                break;
        }
    }

    private void setAnimation(View view) {
        view.setAnimation(mViewAnimationSet);
        view.startAnimation(mViewAnimationSet);
    }
}
