package com.example.boardteam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IntroActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_layout); //xml , java 소스 연결
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent); //다음화면으로 넘어감
                finish();
            }
        }, 3000); //3초 뒤에 Runner객체 실행하도록 함
    }
    protected void onPause(){
        super.onPause();
        finish();
    }
}
