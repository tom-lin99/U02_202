package com.tqc.gdd02;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GDD02 extends Activity implements View.OnClickListener
{

  Button btnSubmit;
  EditText edInputNum;
  TextView tvRespond;
  //答案
  String luckyNum;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    btnSubmit = (Button) findViewById(R.id.submit);
    edInputNum = (EditText) findViewById(R.id.input_number);
    tvRespond = (TextView) findViewById(R.id.respond);

    btnSubmit.setOnClickListener(this);
    luckyNum = generateLuckyNum();
  }

  private String generateLuckyNum(){
    int num=0;
    String strNum = "";
    do{
      num = (int) (Math.random()*1000);//12 5
      strNum = String.format("%03d",num);//012 005
    }while(isLegal(strNum)==false);

    Log.i("ANS",strNum);
    return strNum;
  }

  //判斷輸入數字符合規則
  private boolean isLegal(String num){
    //TODO 記得修改
    //檢查長度
    if(num==null||num.length()!=3){
      return false;
    }
    //123 1
    if(num.charAt(0)==num.charAt(1)
      ||num.charAt(0)==num.charAt(2) ||num.charAt(1)==num.charAt(2)
    ){
      return false;
    }
    return true;
  }
// TO DO
  private int getANum(String num,String luckNum){
    int aNum =0;
    for (int i = 0; i <luckyNum.length() ; i++) {
      if(num.charAt(i)==luckyNum.charAt(i)){
        aNum++;
      }
    }
    return aNum;
  }
  private int getBNum(String num,String luckNum){
    int bNum =0;
    for (int i = 0; i <luckyNum.length() ; i++) {
      char x = luckyNum.charAt(i);
      if(num.charAt(i)==luckyNum.charAt(i)){

      }else{
        int pos = num.indexOf(x);
        if(pos>=0)bNum++;
      }
    }
    return bNum;
  }


  //按鈕事件
  @Override
  public void onClick(View v) {
    String userNum = edInputNum.getText().toString();
    // TO DO
    if(!isLegal(userNum)){
      edInputNum.setText("");
      String msg = "輸入錯誤"+getText(R.string.title);
      tvRespond.setText(msg);
    }else{
      int numA = getANum(userNum,luckyNum);
      int numB = getBNum(userNum,luckyNum);
      //
      if(numA==luckyNum.length()){
        String msg = String.format(
                "INPUT的數字: %s 恭喜你答對了!答案 : %s",userNum,luckyNum);
        tvRespond.setText(msg);
      }else{
        edInputNum.setText("");
        String msg = String.format(
                "INPUT的數字: %s %dA%dB",userNum,numA,numB);
        tvRespond.setText(msg);
      }
    }

    }
  }

