package com.nellem.cloud1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance ();
    DatabaseReference myRef = db.getReference ("logDHT");
//    DatabaseReference myRef = db.getReference ();

    TextView textView, textView2, textView3;
    EditText editText;
    Button btnPut, btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        setTitle ("addChildEvent");

        textView = (TextView) findViewById (R.id.textView);
        textView2 = (TextView) findViewById (R.id.textView2);
        textView3 = (TextView) findViewById (R.id.textView3);
        editText = (EditText)findViewById(R.id.inputText);
        btnPut = (Button)findViewById(R.id.btnPut);
        btnGet = (Button)findViewById(R.id.btnGet);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String data = snapshot.getValue().toString();
                //textView.append(data.toString() + "\n");
                data = data.replaceAll("\\{", "");
                data = data.replaceAll("\\}", "");
                data = data.replaceAll("time=", "");
                data = data.replaceAll("temperature=", "");
                data = data.replaceAll("humidity=", "");
                String[] sResult = data.split(",");

                textView.setText("데이터 갱신 시각 : " + sResult[2]);
                textView2.setText(sResult[0] + "℃");
                textView3.setText(sResult[1] + "%");
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

        btnPut.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String inData = editText.getText().toString ();

                // Firebase에 key에 대한 value 저장
                //myRef.setValue(inData);
                myRef.push().child("test").setValue(inData);
                editText.setText ("");
            }
        });
        btnGet.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

            }
        });
    }
}