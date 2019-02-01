package com.div.timepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Toast;

import com.div.timepicker.datepicker.CustomTimePicker;
import com.div.timepicker.datepicker.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimePickerActivity extends AppCompatActivity {

    private CustomTimePicker mTimerPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        initTimePicker();
    }

    /**
     * 初始化时间选择器
     */
    private void initTimePicker() {
        long beginTime = System.currentTimeMillis();
        String str = DateFormatUtils.long2Str(beginTime, true);
        str = (Integer.parseInt(str.substring(0, 4)) + 3) + str.substring(4, str.length());
        long endTime = DateFormatUtils.str2Long(str, true);
//        String s = DateFormatUtils.long2Str(beginTime, true);
//        tvTaskChoiceDate.setText(s);
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomTimePicker(this, new CustomTimePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                Date date = new Date(timestamp);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String s = dateFormat.format(date);
                Toast.makeText(TimePickerActivity.this, s , Toast.LENGTH_SHORT).show();
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 不允许循环滚动
        mTimerPicker.setScrollLoop(false);
        // 不允许滚动动画
        mTimerPicker.setCanShowAnim(false);
        // 设置点击回到今天，开始日期改为今天
        mTimerPicker.setBeginTimeToToday(true);
        mTimerPicker.show(beginTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimerPicker.onDestroy();
    }
}
