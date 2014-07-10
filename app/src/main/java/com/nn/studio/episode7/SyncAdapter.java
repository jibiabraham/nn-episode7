package com.nn.studio.episode7;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nn.studio.episode7.model.Discussion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jibi on 8/7/14.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private final String TAG = "SyncAdapter";

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.w(TAG, "onPerformSync");

        /*final ContentResolver cr = getContext().getContentResolver();
        Cursor forums = cr.query(PGContract.Forums.CONTENT_URI, PGContract.Forums.DEFAULT_PROJECTION, null, null, null);
        final HashMap<String, Long> forumIdMap = new HashMap<String, Long>();
        if (forums.getCount() > 0){
            int indexId = forums.getColumnIndex(PGContract.Forums._ID);
            int indexUrlStarsWith = forums.getColumnIndex(PGContract.Forums.COLUMN_NAME_URL_STARTSWITH);
            for (int i = 0; i < forums.getCount(); i++) {
                forums.moveToPosition(i);
                Long forumId = forums.getLong(indexId);
                String urlStartsWith = forums.getString(indexUrlStarsWith);
                forumIdMap.put(urlStartsWith, forumId);
            }
            Log.w(TAG, forumIdMap.toString());
        }

        String BASEURL = "http://smaug.pagalguy.com";
        String[] FORUMURLS = {
                "/cat",
                "/xat-snap-cmat-others",
                "/bank-po",
                "/gmat",
                "/gre-gate-other-exams",
                "/jobs-careers",
                "/lounge"
        };
        for (final String forumUrl: FORUMURLS){
            Log.w(TAG, forumUrl);
            JsonObjectRequest discussionsJsonRequest = new JsonObjectRequest(Request.Method.GET, BASEURL + forumUrl + "?json=1", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject result) {
                            try{
                                JSONArray allDiscussions = result.getJSONObject("payload").getJSONArray("all_threads");
                                for (int i = 0; i < allDiscussions.length(); i++) {
                                    JSONObject discussion = allDiscussions.getJSONArray(i).getJSONObject(0);
                                    Long id = discussion.getLong("id");
                                    String title = discussion.getString("content");
                                    Uri url =  Uri.parse(discussion.getString("url"));
                                    List<String> pathSegments = url.getPathSegments();
                                    String urlStartsWith = pathSegments.get(0);
                                    if(forumIdMap.containsKey(urlStartsWith)){
                                        Uri dUri = cr.insert(
                                                PGContract.Discussions.CONTENT_ID_URI_BASE,
                                                new Discussion(id, title, url.toString(), forumIdMap.get(urlStartsWith)).toContentValues()
                                        );
                                        //Log.w(TAG, dUri.toString());
                                        RequestProxy.getPostsInDiscussion(getContext(), url, id, forumIdMap.get(urlStartsWith));
                                    }
                                }
                            } catch (JSONException ex){
                                ex.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            volleyError.printStackTrace();
                        }
                    }
            );
            NetworkRequests.getInstance(getContext()).addToRequestQueue(discussionsJsonRequest);
        }*/
    }
}
