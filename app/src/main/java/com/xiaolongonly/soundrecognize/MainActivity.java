package com.xiaolongonly.soundrecognize;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xiaolongonly.soundrecognize.Model.MsgModel;
import com.xiaolongonly.soundrecognize.adapter.RecorderAdapter;
import com.xiaolongonly.soundrecognize.view.AudioRecordButton;
import com.xiaolongonly.soundrecognize.view.AudioRecordButton.AudioFinishRecorderListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private final static String TAG = "MainActivity";
    private AudioRecordButton button;
    private ListView mlistview;
    private View viewanim;
    private List<MsgModel> msgModels = new ArrayList<MsgModel>();
    private RecorderAdapter recorderAdapter;
    private AnimationDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlistview = (ListView) findViewById(R.id.listview);

        button = (AudioRecordButton) findViewById(R.id.recordButton);
        button.setAudioFinishRecorderListener(new AudioFinishRecorderListener() {
            @Override
            public void onFinished(float seconds, String filePath) {
                MsgModel msgModel = new MsgModel();
                msgModel.setTime(seconds);
                msgModel.setFilePathString(filePath);
                msgModel.setState(1);
//				msgModels.clear();
                msgModels.add(msgModel);
                recorderAdapter.clear();
                recorderAdapter.addModel(msgModels);
                recorderAdapter.notifyDataSetChanged();
//				mlistview.setSelection(mDatas.size() - 1);
            }
        });

        recorderAdapter = new RecorderAdapter(this);
        mlistview.setAdapter(recorderAdapter);
        mlistview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 播放动画
                if (viewanim != null) {//让第二个播放的时候第一个停止播放
                    viewanim.setBackgroundResource(R.drawable.play);
                    viewanim = null;
                    drawable.stop();
                }
                viewanim = view.findViewById(R.id.id_recorder_anim);
                viewanim.setBackgroundResource(R.drawable.play);
                drawable = (AnimationDrawable) viewanim
                        .getBackground();
                drawable.start();
                // 播放音频
                MediaManager.playSound(msgModels.get(position).getFilePathString(),
                        new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                viewanim.setBackgroundResource(R.drawable.play);
                            }
                        });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }

}
