package com.mjc.sc_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MemoWriteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText edit_title, edit_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_write);

        layoutInit();
    }

    private void layoutInit() {
        toolbar = findViewById(R.id.memoWrite_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit_title = findViewById(R.id.memoWrite_title);
        edit_content = findViewById(R.id.memoWrite_content);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.memoWrite_check:
                String title = edit_title.getText().toString();
                String content = edit_content.getText().toString();

                DBHelper dbHelper = new DBHelper(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO tb_memo (title, content) VALUES (?, ?)", new String[]{title, content});
                db.close();
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        }
    }
}
