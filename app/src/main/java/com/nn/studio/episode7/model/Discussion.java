package com.nn.studio.episode7.model;

import android.content.ContentValues;

import com.nn.studio.episode7.provider.PGContract;

/**
 * Created by jibi on 10/7/14.
 *
 */
public class Discussion {
    public Long _id;
    public String title;
    public String url;
    public Long created;
    public Long modified;
    public Long forum_id;

    public Discussion(Long _id, String title, String url, Long created, Long modified, Long forum_id) {
        this._id = _id;
        this.title = title;
        this.url = url;
        this.created = created;
        this.modified = modified;
        this.forum_id = forum_id;
    }

    public Discussion(String title, String url, Long created, Long modified, Long forum_id) {
        this.title = title;
        this.url = url;
        this.created = created;
        this.modified = modified;
        this.forum_id = forum_id;
    }

    public Discussion(Long id, String url, String title, Long forum_id) {
        this._id = id;
        this.title = title;
        this.url = url;
        this.forum_id = forum_id;
    }

    public ContentValues toContentValues(){
        ContentValues cv = new ContentValues();
        Long now = System.currentTimeMillis();
        if(created == null){
            created = now;
        }
        if(modified == null){
            modified = now;
        }
        if(_id != null){
            cv.put(PGContract.Discussions._ID, _id);
        }
        cv.put(PGContract.Discussions.COLUMN_NAME_TITLE, title);
        cv.put(PGContract.Discussions.COLUMN_NAME_URL, url);
        cv.put(PGContract.Discussions.COLUMN_NAME_CREATE_DATE, created);
        cv.put(PGContract.Discussions.COLUMN_NAME_MODIFICATION_DATE, modified);
        cv.put(PGContract.Discussions.COLUMN_NAME_FORUM_ID, forum_id);
        return cv;
    }
}
