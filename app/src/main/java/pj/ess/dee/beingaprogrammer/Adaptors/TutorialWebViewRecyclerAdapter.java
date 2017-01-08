package pj.ess.dee.beingaprogrammer.Adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.List;

import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 24-04-2015.
 */
@SuppressWarnings("deprecation")
public class TutorialWebViewRecyclerAdapter extends RecyclerView.Adapter<TutorialWebViewRecyclerAdapter.MyViewHolder> {

    private LayoutInflater inflate;

    Context context;
    List<TutorialWebViewInformation> data = Collections.emptyList();



    public TutorialWebViewRecyclerAdapter(Context context, List<TutorialWebViewInformation> data) {

        this.context = context;
        inflate = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflate.inflate(R.layout.custom_tutorial_view_row, parent, false);
        MyViewHolder holder = new MyViewHolder(rootView);
        //Log.d("Create View","Running");

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TutorialWebViewInformation current = data.get(position);
        //holder.layout.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_down_very_long));
        holder.webView.loadUrl(current.LoadUrl);
        //Log.d("Bind VIew","Running");


    }

    @Override
    public int getItemCount() {
        //old value 5
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        WebView webView;
        LinearLayout layout;



        public MyViewHolder(View itemView) {
            super(itemView);
            webView = (WebView) itemView.findViewById(R.id.WebView_id);
            layout = (LinearLayout) itemView.findViewById(R.id.FullViewLayout);
            webView.getSettings().setUseWideViewPort(true);
//TODO Jelly Error Webview scroll Problem

            //webView.setInitialScale(20);
            //webView.getContentHeight();

        }
    }
    /**
     * Created by Sahil_Jalan on 25-04-2015.
     */
    public static class TutorialWebViewInformation {
        public String LoadUrl;
    }

}

