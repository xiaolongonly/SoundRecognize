package com.xiaolongonly.soundrecognize.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.u1city.module.base.U1CityAdapter;
import com.u1city.module.util.ViewHolder;
import com.xiaolongonly.soundrecognize.Model.MsgModel;
import com.xiaolongonly.soundrecognize.R;

public class RecorderAdapter extends U1CityAdapter<MsgModel> {


    private int mMinItemWith;// 设置对话框的最大宽度和最小宽度
    private int mMaxItemWith;

    public RecorderAdapter(Context context) {
        super(context);
        // 获取系统宽度
        WindowManager wManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wManager.getDefaultDisplay().getMetrics(outMetrics);
        mMaxItemWith = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWith = (int) (outMetrics.widthPixels * 0.15f);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MsgModel  msgModel = getModels().get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_layout,null);
        }
        if (msgModel == null) {
            return convertView;
        }
        if (1 == msgModel.getState()) {
            convertView = inflater.inflate(R.layout.item_layout, null);
        } else {
            convertView = inflater.inflate(R.layout.chatting_item_msg_text_left, null);
        }
        TextView content = ViewHolder.get(convertView, R.id.tv_chatcontent);
        TextView seconds= ViewHolder.get(convertView, R.id.recorder_time);
        FrameLayout length=ViewHolder.get(convertView, R.id.recorder_length);
        seconds.setText(Math.round(msgModel.getTime()) + "\"");
        ViewGroup.LayoutParams lParams = length.getLayoutParams();
        lParams.width = (int) (mMinItemWith + mMaxItemWith / 60f * msgModel.getTime());
        length.setLayoutParams(lParams);
        return convertView;
    }


}
