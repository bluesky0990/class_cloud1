package com.nellem.cloud1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // 데이터베이스 구성
    FirebaseDatabase db = FirebaseDatabase.getInstance ();
    DatabaseReference myRef = db.getReference ("logDHT");

    // 커스텀리스트뷰 구성
    ListView list;
    LvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        setTitle ("addChildEvent"); // 타이틀바 메세지 변경

        // 커스텀리스트뷰 생성
        list = (ListView)findViewById(R.id.LvItem);
        adapter = new LvAdapter();
        list.setAdapter(adapter);

        // 데이터베이스의 값 변경 시 이벤트 발생
        myRef.addChildEventListener(new ChildEventListener() {

            //  값이 추가 될 때마다 실행
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //  스냅샷으로 부터 생성 된 값을 String에 대입, 이 후 커스텀리스트뷰 어댑터에 추가
                String data1 = snapshot.child("temperature").getValue().toString();
                String data2 = snapshot.child("humidity").getValue().toString();
                String data3 = snapshot.child("date").getValue().toString();
                adapter.addItem(data3, data1 + "℃", data2 + "%");
                adapter.notifyDataSetChanged();

//                Map<String, Object> dataDict = new HashMap<>();
//                dataDict = (Map<String, Object>) snapshot.getValue();
//                String hu = dataDict.get("humidity").toString();
//                String te = dataDict.get("temperature").toString();
//                String ti = dataDict.get("date").toString();
//
//                adapter.addItem(ti, te + "℃", hu + "%");
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}