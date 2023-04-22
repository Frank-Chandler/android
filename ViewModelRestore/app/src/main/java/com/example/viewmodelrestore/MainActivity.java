package com.example.viewmodelrestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewmodelrestore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyViewModel myViewModel;
    public final static String KEY_NUMBER="my_number";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO:创建SharedPreferences接口，用来保存简单数据
        String shpName = "SHP_NAME";
        SharedPreferences shp = getSharedPreferences(shpName,Context.MODE_PRIVATE);
        //写入操作
        SharedPreferences.Editor editor=shp.edit();
        editor.putInt("NUMBER",100);
        editor.apply();

        //读取操作
        int x=shp.getInt("NUMBER",0);
        String TAG="myLog";
        Log.d(TAG,"onCreate"+x);

        //添加数据判断，用新方法SavedStateHandle实现后不需要
        if (savedInstanceState!=null){
            myViewModel.getNumber().setValue(savedInstanceState.getInt(KEY_NUMBER));
        }
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        //TODO:三选一实现
        //用新方法SavedStateHandle实现需要添加new SavedStateViewModelFactory(getApplication(),this))
        // 新方法实测不可用
        myViewModel=new ViewModelProvider(this).get(MyViewModel.class);
        //myViewModel=ViewModelProviders.of(this,new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModel.class);
        //myViewModel=new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);//实现observe功能
        //TODO:将相关函数绑定至xml
        /*myViewModel.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView.setText(String.valueOf(integer));
            }
        });*/
        /*binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.add();
            }
        });*/
    }


    //TODO:保存在后台数据不被杀死
    //用新方法SavedStateHandle实现后不需要（新方法不可用）
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUMBER,myViewModel.getNumber().getValue());
    }

}