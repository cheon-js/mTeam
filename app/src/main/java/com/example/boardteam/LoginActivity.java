package com.example.boardteam;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KakaoSdk.init(this, "1e5590c0de7a151128596eb2ebbaaa56");
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button)findViewById(R.id.button);
        Intent i = new Intent(this,MainActivity.class);
        getHashKey();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (LoginClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    LoginClient.getInstance().loginWithKakaoTalk(LoginActivity.this, new Function2<OAuthToken, Throwable, Unit>() {
                        @Override
                        public Unit invoke(OAuthToken token, Throwable error) {
                            if (error != null) {
                                Log.e("TAG", "로그인 실패", error);
                            } else {
                                Log.d("TAG", "로그인 성공");
                                startActivity(i);

                                // 사용자 정보 요청
                                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                                    @Override
                                    public Unit invoke(User user, Throwable error) {
                                        if (error != error) {
                                            Log.e("TAG", "사용자 정보 요청 실패", error);
                                        } else {
                                            Log.i("TAG", user.toString());
                                        }
                                        return null;
                                    }
                                });
                            }
                            return null;
                        }
                    });
                } else {
                    LoginClient.getInstance().loginWithKakaoAccount(LoginActivity.this,  new Function2<OAuthToken, Throwable, Unit>() {
                        @Override
                        public Unit invoke(OAuthToken token, Throwable error) {
                            if (error != null) {
                                Log.e("TAG", "로그인 실패", error);
                            } else {
                                Log.d("TAG", "로그인 성공");
                                startActivity(i);

                                // 사용자 정보 요청
                                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                                    @Override
                                    public Unit invoke(User user, Throwable error) {
                                        if (error != error) {
                                            Log.e("TAG", "사용자 정보 요청 실패", error);
                                        } else {
                                            Log.i("TAG", user.toString());
                                        }
                                        return null;
                                    }
                                });
                            }
                            return null;
                        }
                    });
                }
            }
        });
    }

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }





}