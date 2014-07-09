package com.nn.studio.episode7;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

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
        //getLoaderManager().initLoader(URL_LOADER, null, this);

        Account dummyAccount = new Account("jibi.pg", "com.pagalguy");
        AccountManager accountManager = (AccountManager) this.getSystemService(ACCOUNT_SERVICE);
        accountManager.addAccountExplicitly(dummyAccount, "pwd", Bundle.EMPTY);

        ContentResolver.setIsSyncable(dummyAccount, PGContract.AUTHORITY, 1);
        ContentResolver.requestSync(dummyAccount, PGContract.AUTHORITY, Bundle.EMPTY);

        /*
        Well inline images were a must
        So this is an attempt to integrate all the caching, fetching blah blah ..
        I use the volley library for this
        Resources at
            1. https://android.googlesource.com/platform/frameworks/volley/
            2. https://developers.google.com/events/io/sessions/325304728
            3. http://developer.android.com/training/volley/index.html
        * */
        String htmlContent = "<p>Well this is an image thingy here&nbsp;-&nbsp;<img src=\"http://sm.ge.pgstatic.net/paagal/static/emoji/soccer.png\"/></p>";
        Document doc = Jsoup.parseBodyFragment(htmlContent);
        TextView tv = (TextView) findViewById(R.id.html);
        HtmlImageParser imgur =  new HtmlImageParser(getApplicationContext(), tv);
        tv.setText(Html.fromHtml(doc.outerHtml(), imgur, null));
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
