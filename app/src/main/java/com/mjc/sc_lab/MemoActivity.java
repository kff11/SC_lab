package com.mjc.sc_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MemoActivity extends AppCompatActivity {
    private Intent intent;
    private ListView listView;
    private TextView textView_Count;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        layoutInit();


    }

    private void layoutInit() {
        toolbar = findViewById(R.id.memo_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.memo_listView);
        textView_Count = findViewById(R.id.memo_count);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.memo_write:
                intent = new Intent(this, MemoWriteActivity.class);
                break;

        }
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    @Override
    public void onResume() {
        super.onResume();

        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cursor = db.rawQuery("SELECT * FROM tb_memo", null);

        CursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor,
                new String[]{"title", "content"},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);

        textView_Count.setText(cursor.getCount() + "개의 메모가 있습니다.");
    }
}
