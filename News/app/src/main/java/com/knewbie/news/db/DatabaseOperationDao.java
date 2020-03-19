package com.knewbie.news.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.knewbie.news.R;
import com.knewbie.news.entity.NewsBean;
import com.knewbie.news.entity.UserBean;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperationDao {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DatabaseOperationDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }


    public void updateTable(String table, String setSql, String whereSql) {
        String sql = "update "+table+ " set " +setSql+" where "+whereSql;
        db.execSQL(sql);
    }

    public int getTheLast(String table, String primaryKey) {    //找到最后的id
        Cursor cursor = db.rawQuery("select * from "+table+" order by "+primaryKey+" desc limit 0,1", null);
        return (cursor.moveToNext())?cursor.getInt(cursor.getColumnIndex(primaryKey)):-1;
    }

    /*
     * User
     * */
    public boolean findUsername(String username) {
        Cursor cursor = db.query("user_table", null, "username = ?", new String[]{username}, null, null, null);
        if (cursor.moveToNext()) {
            return true;
        } else return false;
    }

    /*public boolean findUserPhone(String phone) {
        Cursor cursor = db.query("user_table", null, "phone = ?", new String[]{phone}, null, null, null);
        if (cursor.moveToNext()) {
            return true;
        } else return false;
    }*/

    public UserBean findUser(String username, String pwd) {
        UserBean userBean;
        Cursor cursor = db.query("user_table", null, "username = ? and password = ?", new String[]{username, pwd}, null, null, null);
        if (cursor.moveToNext()) {
            userBean = new UserBean();
            userBean.setId(cursor.getInt(cursor.getColumnIndex("user_id")));
            userBean.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            userBean.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            userBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            userBean.setEmailAd(cursor.getString(cursor.getColumnIndex("email_address")));
            userBean.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
            if (userBean == null) userBean.setAvatar("drawable://"+R.drawable.ic_appbar_user);
            else userBean.setAvatar(cursor.getString(cursor.getColumnIndex("pic")));
            cursor.close();
        } else return null; //此种情况，用户名或密码不存在

        return userBean;
    }

    public UserBean findUserByPhone(String phone) {
        UserBean userBean;
        Log.d("hello", ""+phone);
        Cursor cursor = db.query("user_table", null, "phone = ?", new String[]{phone}, null, null, null);
        if (cursor.moveToNext()) {
            userBean = new UserBean();
            userBean.setId(cursor.getInt(cursor.getColumnIndex("user_id")));
            userBean.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            userBean.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            userBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            userBean.setEmailAd(cursor.getString(cursor.getColumnIndex("email_address")));
            userBean.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
            if (userBean == null) userBean.setAvatar("drawable://"+R.drawable.ic_appbar_user);
            else userBean.setAvatar(cursor.getString(cursor.getColumnIndex("pic")));
            cursor.close();
        } else return null; //此种情况，用户名或密码不存在

        return userBean;
    }

    public List<UserBean> getUser() {
        List<UserBean> result = new ArrayList<>();
        //user_table(user_id, username, password, phone, email_address, signature, is_auth, pic_id)
        Cursor cursor = db.query("user_table", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            UserBean item = new UserBean();
            item.setId(cursor.getInt(cursor.getColumnIndex("user_id")));
            item.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            item.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            item.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            item.setEmailAd(cursor.getString(cursor.getColumnIndex("email_address")));
            item.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
            //item.setAvatar(R.drawable.ic_appbar_user);
            if (item == null) item.setAvatar("drawable://"+R.drawable.ic_appbar_user);
            else item.setAvatar(cursor.getString(cursor.getColumnIndex("pic")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    public void addUser(UserBean item) {
        db.beginTransaction();
        try {
            //user_table(user_id, username, password, phone, email_address, signature, is_auth, pic)
            db.execSQL("insert into user_table values(null, ?, ?, ?, ?, ?, 0, ?)", new Object[]{item.getUsername(), item.getPassword(), item.getPhone(), item.getEmailAd(), item.getSignature(), "drawable://"+R.drawable.ic_appbar_user});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteUser(long id) {
        String sql = "delete from user_table where user_id = "+id;
        db.beginTransaction();
        try {
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    /*
    * News For Read
    * */
    public void test() {
        String sql = "select * from news_table";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.d("hello", "news----"+cursor.getString(cursor.getColumnIndex("news_id")));
        }
        cursor.close();
    }

    public List<NewsBean.ResultBean.DataBean> getNewsDataBeanList() {
        List<NewsBean.ResultBean.DataBean> result = new ArrayList<>();
        // news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url)
        /* 以前设计
        news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content, last_edit_time, release_user_id, cover_pic_id)
        目前cover_pic不是id，是图片
        */
        Cursor cursor = db.query("news_table", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            NewsBean.ResultBean.DataBean item = new NewsBean.ResultBean.DataBean();
            item.setUniquekey(cursor.getString(cursor.getColumnIndex("news_id")));
            item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //item.setIntroduction(cursor.getString(cursor.getColumnIndex("digest")));
            //item.setReadAmount(cursor.getInt(cursor.getColumnIndex("read_amount")));
            //item.setReviewAmount(cursor.getInt(cursor.getColumnIndex("review_amount")));
            //item.setLikeAmount(cursor.getInt(cursor.getColumnIndex("like_amount")));
            item.setUrl(cursor.getString(cursor.getColumnIndex("content_url")));
            item.setDate(cursor.getString(cursor.getColumnIndex("last_edit_time")));
            item.setAuthor_name(cursor.getString(cursor.getColumnIndex("release_source")));
            item.setThumbnail_pic_s(cursor.getString(cursor.getColumnIndex("cover_pic1_url")));
            item.setThumbnail_pic_s02(cursor.getString(cursor.getColumnIndex("cover_pic2_url")));
            item.setThumbnail_pic_s03(cursor.getString(cursor.getColumnIndex("cover_pic3_url")));
            //item.setPic(cursor.getInt(cursor.getColumnIndex("cover_pic_id")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    public List<NewsBean.ResultBean.DataBean> getNewsDataBeanList(int start, int len) {
        List<NewsBean.ResultBean.DataBean> result = new ArrayList<>();
        // news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url)
        Cursor cursor = db.query("news_table", null, null, null, null, null, null, ""+start+","+len+"");
        while (cursor.moveToNext()) {
            NewsBean.ResultBean.DataBean item = new NewsBean.ResultBean.DataBean();
            item.setUniquekey(cursor.getString(cursor.getColumnIndex("news_id")));
            item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //item.setIntroduction(cursor.getString(cursor.getColumnIndex("digest")));
            //item.setReadAmount(cursor.getInt(cursor.getColumnIndex("read_amount")));
            //item.setReviewAmount(cursor.getInt(cursor.getColumnIndex("review_amount")));
            //item.setLikeAmount(cursor.getInt(cursor.getColumnIndex("like_amount")));
            item.setUrl(cursor.getString(cursor.getColumnIndex("content_url")));
            item.setDate(cursor.getString(cursor.getColumnIndex("last_edit_time")));
            item.setAuthor_name(cursor.getString(cursor.getColumnIndex("release_source")));
            item.setThumbnail_pic_s(cursor.getString(cursor.getColumnIndex("cover_pic1_url")));
            item.setThumbnail_pic_s02(cursor.getString(cursor.getColumnIndex("cover_pic2_url")));
            item.setThumbnail_pic_s03(cursor.getString(cursor.getColumnIndex("cover_pic3_url")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    public boolean findNews(String id) {
        Cursor cursor = db.query("news_table", null, "news_id = ?", new String[]{id}, null, null, null);
        /*if (cursor.moveToNext()) {
            return true;
        } else return false;*/
        return cursor.moveToNext();
    }

    public void addNewsDataBean(NewsBean.ResultBean.DataBean item) {
        db.beginTransaction();
        try {
            // news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url)
            db.execSQL("insert into news_table values(?, ?, ?, ?, 0, 0, 0, ?, ?, ?, ?, ?, ?)", new Object[]{item.getUniquekey(), item.getTitle(), item.getCategory(), "摘要", item.getUrl(), item.getDate(), item.getAuthor_name(), item.getThumbnail_pic_s(), item.getThumbnail_pic_s02(), item.getThumbnail_pic_s03()});
            //Log.d("hello", "pic2"+item.getThumbnail_pic_s02()+"pic3"+item.getThumbnail_pic_s03());
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteNewsDataBean(String id) {
        String sql = "delete from news_table where news_id = '"+ id +"'";
        db.beginTransaction();
        try {
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    /*
     * Favorite News
     */
    public int findNewsFavorite(int uid, String newsId) {
        Cursor cursor = db.query("favorite_table", null, "user_id = ? and news_id = ?", new String[]{""+uid, newsId}, null, null, null);
        if (cursor.moveToNext())
            return cursor.getInt(cursor.getColumnIndex("favorite_id"));
        return -1;
    }

    public List<NewsBean.ResultBean.DataBean> getNewsFavoriteList(int uid) {
        List<NewsBean.ResultBean.DataBean> result = new ArrayList<>();
        //favorite_table (favorite_id, user_id, news_id)
        // news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url)
        String sql = "select * from news_table, favorite_table where news_table.news_id = favorite_table.news_id and user_id = ? order by favorite_id";
        //String sql = "select * from news_table where news_id in (select news_id from favorite_table where user_id = ?)";
        Cursor cursor = db.rawQuery(sql, new String[]{uid+""});
        //Cursor cursor = db.query("favorite_table", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            //Log.d("hello", "getFavoriteNews---");
            NewsBean.ResultBean.DataBean item = new NewsBean.ResultBean.DataBean();
            //item.setId(cursor.getInt(cursor.getColumnIndex("favorite_id")));
            item.setUniquekey(cursor.getString(cursor.getColumnIndex("news_id")));
            item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //item.setIntroduction(cursor.getString(cursor.getColumnIndex("digest")));
            item.setUrl(cursor.getString(cursor.getColumnIndex("content_url")));
            item.setDate(cursor.getString(cursor.getColumnIndex("last_edit_time")));
            item.setAuthor_name(cursor.getString(cursor.getColumnIndex("release_source")));
            item.setThumbnail_pic_s(cursor.getString(cursor.getColumnIndex("cover_pic1_url")));
            item.setThumbnail_pic_s02(cursor.getString(cursor.getColumnIndex("cover_pic2_url")));
            item.setThumbnail_pic_s03(cursor.getString(cursor.getColumnIndex("cover_pic3_url")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    public void getNewsFavoriteDisplayItemList() {
        //favorite_table (favorite_id, user_id, news_id)
        String sql = "select * from favorite_table";
        Cursor cursor = db.rawQuery(sql, null);
        //Cursor cursor = db.query("favorite_table", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.d("hello", "----"+cursor.getInt(cursor.getColumnIndex("favorite_id"))
                            + "--"+cursor.getString(cursor.getColumnIndex("news_id")) +
                    "---"+cursor.getInt(cursor.getColumnIndex("user_id")) );
        }
        cursor.close();
        //return result;
    }
    /*public List<NewsDisplayItem> getNewsFavoriteDisplayItemList() {
        List<NewsDisplayItem> result = new ArrayList<>();
        //favorite_table (favorite_id, user_id, news_id)
        String sql = "select * from news_table inner join favorite_table on ";
        Cursor cursor = db.rawQuery(sql, null);
        //Cursor cursor = db.query("favorite_table", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            NewsDisplayItem item = new NewsDisplayItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("favorite_id")));
            //item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //item.setIntroduction(cursor.getString(cursor.getColumnIndex("digest")));
            //item.setContent(cursor.getString(cursor.getColumnIndex("content")));
            //item.setLastEditTime(cursor.getString(cursor.getColumnIndex("last_edit_time")));
            //item.setAuthor(cursor.getInt(cursor.getColumnIndex("release_user_id")));
            //item.setPic(cursor.getInt(cursor.getColumnIndex("cover_pic_id")));
            result.add(item);
        }
        cursor.close();
        return result;
    }*/

    public void addNewsFavoriteDisplayItem(int uid, String newsId) {
        db.beginTransaction();
        try {
            //favorite_table (favorite_id, user_id, news_id)
            db.execSQL("insert into favorite_table values(null, ?, ?)", new Object[]{uid, newsId});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteNewsFavoriteDisplayItem(long id) {
        String sql = "delete from favorite_table where favorite_id = "+id;
        //Log.d("hello", "deleteNewsFavorite---");
        db.beginTransaction();
        try {
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    /*
     * History
     */
    public int findNewsHistory(int uid, String newsId) {
        Cursor cursor = db.query("history_table", null, "user_id = ? and news_id = ?", new String[]{""+uid, newsId}, null, null, null);
        if (cursor.moveToNext())
            return cursor.getInt(cursor.getColumnIndex("history_id"));
        return -1;
    }

    public List<NewsBean.ResultBean.DataBean> getNewsHistoryList(int uid) {
        //getNewsHistoryDisplayItemList();
        //test();
        List<NewsBean.ResultBean.DataBean> result = new ArrayList<>();
        //history_table (history_id, user_id, news_id)
        //Cursor cursor = db.query("history_table", null, null, null, null, null, null);
        // news_table (news_id, title, type, digest, read_amount, review_amount, like_amount, content_url, last_edit_time, release_source, cover_pic1_url, cover_pic2_url, cover_pic3_url)
        String sql = "select * from news_table,history_table where news_table.news_id = history_table.news_id and user_id = ? order by history_id";
        //select * from news_table where news_id in (select news_id from history_table where user_id = ?)这样写，按照uniqkey排序
        Cursor cursor = db.rawQuery(sql, new String[]{uid+""});
        while (cursor.moveToNext()) {
            NewsBean.ResultBean.DataBean item = new NewsBean.ResultBean.DataBean();
            //item.setId(cursor.getInt(cursor.getColumnIndex("history_id")));
            item.setUniquekey(cursor.getString(cursor.getColumnIndex("news_id")));
            //Log.d("hello", "newsId---"+cursor.getString(cursor.getColumnIndex("news_id")));
            item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //item.setIntroduction(cursor.getString(cursor.getColumnIndex("digest")));
            item.setUrl(cursor.getString(cursor.getColumnIndex("content_url")));
            item.setDate(cursor.getString(cursor.getColumnIndex("last_edit_time")));
            item.setAuthor_name(cursor.getString(cursor.getColumnIndex("release_source")));
            item.setThumbnail_pic_s(cursor.getString(cursor.getColumnIndex("cover_pic1_url")));
            item.setThumbnail_pic_s02(cursor.getString(cursor.getColumnIndex("cover_pic2_url")));
            item.setThumbnail_pic_s03(cursor.getString(cursor.getColumnIndex("cover_pic3_url")));
            result.add(item);
        }
        cursor.close();
        return result;
    }
    public void getNewsHistoryDisplayItemList() {
        String sql = "select * from history_table";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.d("hello", "history----"+cursor.getInt(cursor.getColumnIndex("history_id"))
                    + "--"+cursor.getString(cursor.getColumnIndex("news_id"))
                    + "---"+cursor.getInt(cursor.getColumnIndex("user_id")) );
        }
        cursor.close();
        //return result;
    }

    public void addNewsHistoryDisplayItem(int uid, String newsId) {
        db.beginTransaction();
        try {
            //history_table (history_id, user_id, news_id)
            db.execSQL("insert into history_table values(null, ?, ?)", new Object[]{uid, newsId});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteNewsHistoryDisplayItem(long id) {
        String sql = "delete from history_table where history_id = "+id;
        db.beginTransaction();
        try {
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    /*
     * Follow Users
     *
    public List<FollowItem> getFollowItemList() {
        List<FollowItem> result = new ArrayList<>();
        //follow_table (follow_id, follow_user_id, befollowed_user_id)
        Cursor cursor = db.query("follow_table", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            FollowItem item = new FollowItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("news_id")));
            //item.setAuthor(cursor.getInt(cursor.getColumnIndex("release_user_id")));
            //item.setPic(cursor.getInt(cursor.getColumnIndex("cover_pic_id")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    public void addFollowItem(FollowItem item) {
        db.beginTransaction();
        try {
            //follow_table (follow_id, follow_user_id, befollowed_user_id)
            db.execSQL("insert into follow_table values(null, ?, ?)", new Object[]{item.getTitle(), item.getCategory(), item.getIntroduction(), item.getContent(), item.getLastEditTime(), "Author", R.drawable.logo_news_fill_2});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteFollowItem(long id) {
        String sql = "delete from history_table where news_id = "+id;
        db.beginTransaction();
        try {
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }*/
}
