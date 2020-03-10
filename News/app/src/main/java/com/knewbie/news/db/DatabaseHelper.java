package com.knewbie.news.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.knewbie.news.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "account.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        initTable(db);
        //初始化数据
        initData(db);
    }

    private void initTable(SQLiteDatabase db) {
        //String sql = "create table accountincometype(id integer primary key autoincrement, category text, icon integer)";
        String sql = "create table user_table(user_id integer primary key autoincrement, username text, password text, email_address text, signature text, is_auth boolean, pic_id varchar(120))";
        db.execSQL(sql);
        sql = "create table news_table(news_id text primary key, title text, type text, digest text, read_amount integer, review_amount integer, like_amount integer, content_url text, last_edit_time text, release_source text, cover_pic1_url text, cover_pic2_url text, cover_pic3_url text)";
        db.execSQL(sql);
        sql = "create table pic_table(pic_id integer primary key autoincrement, news_id integer, pic_src integer, pic_address text)";
        db.execSQL(sql);
        /* 评论记录，回复记录，反馈记录
        sql = "create table review_table(review_id integer primary key autoincrement, review_user_id integer, news_id integer, review_time text, review_content text)";
        db.execSQL(sql);
        sql = "create table reply_table (reply_id integer primary key autoincrement, sender_user_id integer, review_id integer, review_time text, review_content text)";
        db.execSQL(sql);
        sql = "create table feedback_table (feedback_id integer primary key autoincrement, feedback_content, feedback_time, feedback_user_id) ";
        db.execSQL(sql);
        */
        sql = "create table favorite_table (favorite_id integer primary key autoincrement, user_id, news_id)";
        db.execSQL(sql);
        /* 分享记录
        sql = "create table share_table (share_id integer primary key autoincrement, user_id, news_id)";
        db.execSQL(sql);
        */
        sql = "create table follow_table (follow_id integer primary key autoincrement, follow_user_id, befollowed_user_id)";
        db.execSQL(sql);
        sql = "create table history_table (history_id integer primary key autoincrement, user_id, news_id)";
        db.execSQL(sql);
        /* 打赏记录*
        sql = "create table tip_table (tip_id integer primary key autoincrement, user_id, news_id)";
        db.execSQL(sql);
        */
        //sql = "create table ";
        //sql = "create table pic_table(pic_id integer primary key autoincrement, news_id integer, pic_address text)";
        //db.execSQL(sql);
    }

    private void initData(SQLiteDatabase db) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String sql = String.format("insert into accountincometype(category, icon) values('工资', %d)", R.drawable.);

        //SQLite序号从1开始
        //user_table (user_id, username, password, email_address, signature, is_auth, pic_id)
        String sql = "insert into user_table(username, password, email_address, signature, is_auth, pic_id) values('admin', 'admin', null, null, 0, 0)";
        db.execSQL(sql);
        sql = "insert into user_table(username, password, email_address, signature, is_auth) values('0', '0', null, '一段个性签名', 0)";
        db.execSQL(sql);
        sql = "insert into user_table(username, password, email_address, signature, is_auth) values('1', '1', null, '一段个性签名', 0)";
        db.execSQL(sql);
        sql = "insert into user_table(username, password, email_address, signature, is_auth) values('News作者1', '1', null, null, 0)";
        db.execSQL(sql);
        sql = "insert into user_table(username, password, email_address, signature, is_auth) values('用户1', '1', null, null, 0)";
        db.execSQL(sql);
        sql = "insert into user_table(username, password, email_address, signature, is_auth) values('用户2', '1', null, null, 0)";
        db.execSQL(sql);


        // news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url)
        //      以前设计： news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content, last_edit_time, release_user_id, cover_pic_id)
        String nowTime = simpleDateFormat.format(new Date());
        sql = "insert into news_table(news_id, title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url) values('1', '标题', '热搜', '一段摘要，一段摘要。', 45, 8, 10, '一段内容，一段内容，一段内容', '" + nowTime + "', '来源1', 'R.drawable.logo_news_fill_2', 'R.drawable.logo_news_fill_2', null)";
        db.execSQL(sql);
        /*sql = "insert into news_table(title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url) values('标题', '热搜', '一段摘要，一段摘要。', 45, 8, 10, '一段内容，一段内容，一段内容', '" + nowTime + "', '来源1', null, null, null)";
        db.execSQL(sql);
        nowTime = simpleDateFormat.format(new Date());
        sql = String.format("insert into news_table(title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url) values('标题', '热搜', '一段摘要，一段摘要。', 163, 1, 30, '一段内容，一段内容，一段内容', '%s', '%s', %d, %d, %d)",nowTime, "来源1", R.drawable.logo_news_fill_2, null, null);
        db.execSQL(sql);
        nowTime = simpleDateFormat.format(new Date());
        sql = String.format("insert into news_table(title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url) values('标题', 'test', '一段摘要，一段摘要。', 10, 1, 0, '一段内容，一段内容，一段内容', '%s', '%s', %d, %d, %d)",nowTime, "来源1", R.drawable.logo_news_fill_2, null, null);
        db.execSQL(sql);*/

        //pic_table (pic_id, news_id,pic_src, pic_address)
        sql = String.format("insert into pic_table(news_id, pic_src, pic_address) values(0, %d, null)", R.drawable.logo_news_fill_2);
        db.execSQL(sql);

        //review_table (review_id, review_user_id, news_id, review_time, review_content)
        //reply_table (reply_id, sender_user_id, review_id, review_time, review_content)
        //feedback_table (feedback_id, feedback_content, feedback_time, feedback_user_id)

        //favorite_table (favorite_id, user_id, news_id)
        sql = "insert into favorite_table(user_id, news_id) values(2, 1)";
        db.execSQL(sql);
        sql = "insert into favorite_table(user_id, news_id) values(2, 3)";
        db.execSQL(sql);
        sql = "insert into favorite_table(user_id, news_id) values(2, 4)";
        db.execSQL(sql);

        //share_table (share_id, user_id, news_id)

        //follow_table (follow_id, follow_user_id, befollowed_user_id)
        sql = "insert into follow_table(follow_user_id, befollowed_user_id) values(1, 0)";
        db.execSQL(sql);
        sql = "insert into follow_table(follow_user_id, befollowed_user_id) values(1, 2)";
        db.execSQL(sql);
        sql = "insert into follow_table(follow_user_id, befollowed_user_id) values(1, 3)";
        db.execSQL(sql);
        sql = "insert into follow_table(follow_user_id, befollowed_user_id) values(1, 4)";
        db.execSQL(sql);

        //history_table (history_id, user_id, news_id)
        sql = "insert into history_table(user_id, news_id) values(1, 0)";
        db.execSQL(sql);
        sql = "insert into history_table(user_id, news_id) values(1, 1)";
        db.execSQL(sql);
        sql = "insert into history_table(user_id, news_id) values(1, 2)";
        db.execSQL(sql);

        //tip_table (tip_id, user_id, news_id)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
