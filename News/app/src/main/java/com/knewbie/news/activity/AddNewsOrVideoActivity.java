package com.knewbie.news.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.knewbie.news.R;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.NewsForReadItem;
import com.knewbie.news.global.GlobalApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewsOrVideoActivity extends AppCompatActivity {
    private Button returnButton;
    private TextView saveTextView;
    private TextView releaseTextView;
    private EditText newsTitleEditView;
    private EditText newsContentEditView;
//news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content, last_edit_time, release_user_id, cover_pic_id)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news_or_video);
        returnButton = (Button) findViewById(R.id.buttonReturn);
        saveTextView = (TextView) findViewById(R.id.textViewSave);
        releaseTextView = (TextView) findViewById(R.id.textViewRelease);
        newsTitleEditView = (EditText) findViewById(R.id.editTextReleaseTitle);
        newsContentEditView = (EditText) findViewById(R.id.editTextMultiLineInput);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AddNewsOrVideoActivity.this, MainActivity.class));
                finish();
            }
        });
        saveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddNewsOrVideoActivity.this, "保存功能开发中~", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(AddNewsOrVideoActivity.this, MainActivity.class));
            }
        });
        releaseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddNewsOrVideoActivity.this, "发布功能开发中~", Toast.LENGTH_SHORT);
                addNews();
                Toast.makeText(AddNewsOrVideoActivity.this, "发布成功~", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(AddNewsOrVideoActivity.this, MainActivity.class));
            }
        });
    }

    private void addNews() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(new Date());
        //news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content, last_edit_time, release_user_id, cover_pic_id)
        //if (isNews)
        NewsForReadItem news = new NewsForReadItem();
        //news.setCategory();
        news.setTitle(newsTitleEditView.getText().toString());
        news.setIntroduction(newsContentEditView.getText().toString());
        news.setReadAmount(0);
        news.setReviewAmount(0);
        news.setLikeAmount(0);
        news.setContent(newsContentEditView.getText().toString());
        news.setLastEditTime(nowTime);
        //news.setAuthor();
        //news.setPic();
        //DatabaseOperationDao dbManager = new DatabaseOperationDao(this);
        GlobalApplication app = (GlobalApplication) this.getApplication();
        DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
        // ----------------待完善----------------
        //  dbManager.addNewsForReadItem(news);
        // --------------------------------------
        setResult(1);
        finish();
    }
}
