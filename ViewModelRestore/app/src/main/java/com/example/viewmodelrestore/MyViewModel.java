package com.example.viewmodelrestore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
   private MutableLiveData<Integer> number;
   private SavedStateHandle handle;

   //TODO:利用新方法写保存后台被杀死的数据
    public MyViewModel(SavedStateHandle handle){
        this.handle=handle;
    }
    //修改getNumber函数（新方法实测不可用）
    /*public MutableLiveData<Integer>getNumber(){
        if (handle.contains(MainActivity.KEY_NUMBER)){
            handle.set(MainActivity.KEY_NUMBER,0);
        }
        return handle.getLiveData(MainActivity.KEY_NUMBER);
    }*/
    public MutableLiveData<Integer> getNumber() {
        if (number==null){
            number=new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }
    //修改功能函数（新方法实测不可用）
    /*public void add(){
        getNumber().setValue(getNumber().getValue()+1);
    }*/
    public void add(){
        number.setValue(number.getValue()+1);
    }
}
