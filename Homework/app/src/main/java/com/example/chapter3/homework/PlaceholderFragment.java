package com.example.chapter3.homework;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.Random;

class user{
    public String name="";
    public String msg="";
}

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView animationView;
    private View view;
    private ObjectAnimator a1,a2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_placeholder, container, false);


        animationView = view.findViewById(R.id.animation_view);
        animationView.setRepeatMode(LottieDrawable.RESTART);
        animationView.setRepeatCount(LottieDrawable.INFINITE);
        ListView listView = view.findViewById(R.id.listview);
        listView.setAdapter(new ListViewAdapter(this.getContext()));
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return view;
    }

    public static class ListViewAdapter extends BaseAdapter {
        private String basename = "X-man-";
        private String[] words = {"Hello!","Whats going on?","Sleeping?","How r u?","2333333","Go out Tomorrow?"};
        private static int num = 0;
        private user[] U = new user[100];

        private Context mContext;

        public ListViewAdapter(Context context) {
            mContext = context;
            for(int i=0;i<100;i++){
                Random r = new Random();
                U[i] = new user();
                U[i].name=basename+num;
                U[i].msg=words[r.nextInt(6)];
                num++;
            }
        }

        @Override public int getCount() {
            return 100;
        }

        @Override public Object getItem(int position) {
            return null;
        }

        @Override public long getItemId(int position) {
            return 0;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.list, null);
                ((TextView)(view.findViewById(R.id.textView))).setText(U[position].name);
                ((TextView)(view.findViewById(R.id.textView2))).setText(U[position].msg);
                Random r = new Random();
                int head = r.nextInt(3);
                if(head == 0) ((ImageView)(view.findViewById(R.id.imageView5))).setImageResource(R.drawable.session_robot);
                else if(head == 1) ((ImageView)(view.findViewById(R.id.imageView5))).setImageResource(R.drawable.session_stranger);
                else ((ImageView)(view.findViewById(R.id.imageView5))).setImageResource(R.drawable.session_system_notice);

            } else {
                //!=null
                view = convertView;
                ((TextView)(view.findViewById(R.id.textView))).setText(U[position].name);
                ((TextView)(view.findViewById(R.id.textView2))).setText(U[position].msg);
                Random r = new Random();
                int head = r.nextInt(3);
                if(head == 0) ((ImageView)(view.findViewById(R.id.imageView5))).setImageResource(R.drawable.session_robot);
                else if(head == 1) ((ImageView)(view.findViewById(R.id.imageView5))).setImageResource(R.drawable.session_stranger);
                else ((ImageView)(view.findViewById(R.id.imageView5))).setImageResource(R.drawable.session_system_notice);
            }

            return view;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view.findViewById(R.id.listview).setVisibility(View.GONE);
        a1 = ObjectAnimator.ofFloat(animationView,"alpha", 1f, 0f);
        a1.setDuration(500);
        a2 = ObjectAnimator.ofFloat(view.findViewById(R.id.listview),"alpha", 0f, 1f);
        a2.setDuration(500);

        animationView.playAnimation();
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                a1.start();
                animationView.setVisibility(View.GONE);
                view.findViewById(R.id.listview).setVisibility(View.VISIBLE);
                a2.start();
            }
        }, 5000);
    }
}