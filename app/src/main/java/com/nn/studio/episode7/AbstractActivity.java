package com.nn.studio.episode7;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.nn.studio.episode7.R;

/**
 * Created by jibi on 7/7/14.
 */
public class AbstractActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "ABSTRACT ACTIVITY";
    private static final int URL_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract);
        getLoaderManager().initLoader(URL_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getApplicationContext(), PGContract.Forums.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Log.w(TAG, cursor.getString(1));
            while(cursor.moveToNext()){
                Log.w(TAG, cursor.getString(1));
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }
}
