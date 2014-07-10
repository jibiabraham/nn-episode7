package com.nn.studio.episode7;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nn.studio.episode7.model.Discussion;
import com.nn.studio.episode7.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by jibi on 9/7/14.
 */
public final class RequestProxy {
    private static String BASEURL = "http://smaug.pagalguy.com";
    private static String TAG = "RequestProxy";

    private RequestProxy(){}

    public static void getPostsInDiscussion(final Context context, Uri uri, final Long discussionId, final Long forumId){
        JsonObjectRequest postsJsonRequest = new JsonObjectRequest(Request.Method.GET, BASEURL + uri + "?json=1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject result) {
                        try{
                            JSONArray allPosts = result.getJSONObject("payload").getJSONArray("posts");
                            for (int i = 0; i < allPosts.length(); i++) {
                                JSONObject postJson = allPosts.getJSONObject(i);
                                Post post = Post.fromJson(postJson);
                                if(post != null){
                                    post.discussion_id = discussionId;
                                    post.forum_id = forumId;
                                    Uri pUri = context.getContentResolver().insert(
                                            PGContract.Posts.CONTENT_ID_URI_BASE,
                                            post.toContentValues()
                                    );
                                    Log.w(TAG, pUri.toString());
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
        NetworkRequests.getInstance(context).addToRequestQueue(postsJsonRequest);
    }
}
