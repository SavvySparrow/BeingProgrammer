package pj.ess.dee.beingaprogrammer.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import pj.ess.dee.beingaprogrammer.Activities.MainActivity;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 16-03-2015.
 */
//@SuppressWarnings("deprecation")
public class HomePageAdaptor extends RecyclerView.Adapter<HomePageAdaptor.MyViewHolder> {

    public static RelativeLayout ImageLayout;
    public static LinearLayout ExpandedArea, LearnMore_Layout, FullViewlayout;
    public static TextView Share, LearnMore;
    public static ImageView myImage, myHelp;
    public static TextView MainTitleBg,MainTitleTx;
    public static TextView Title, Subtitle;



    private Handler mHandler = new Handler();
    private LayoutInflater inflate;
    private int expandedPosition = 0;
    public static boolean LearnMoreIsClicked;
    private boolean isFirstRun;
    String shareID=null;
    public static Integer mHomePagePosition= 0;
    public static Integer mShareIdPosition= 0;
    private Context context;
    List<HomePageListInformation> data = Collections.emptyList();

    public HomePageAdaptor(Context context, List<HomePageListInformation> data) {
        this.context = context;
        inflate = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate.inflate(R.layout.custom_home_page_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        // holder.myImage.setImageAlpha(180);
        if(MainActivity.Set_Theme){
            switch (MainActivity.Theme_id){
                case 1:
                    holder.MainTitleBg.setTextColor(context.getResources().getColor(R.color.colorTealDark));
                    holder.Title.setTextColor(context.getResources().getColor(R.color.colorTealPrimaryText));
                    holder.LearnMore.setTextColor(context.getResources().getColor(R.color.colorTealDark));
                    holder.Subtitle.setTextColor(context.getResources().getColor(R.color.colorTealSecondaryText));
                    break;
                case 2:
                    holder.MainTitleBg.setTextColor(context.getResources().getColor(R.color.colorIndegoDark));
                    holder.Title.setTextColor(context.getResources().getColor(R.color.colorIndegoPrimaryText));
                    holder.LearnMore.setTextColor(context.getResources().getColor(R.color.colorIndegoDark));
                    holder.Subtitle.setTextColor(context.getResources().getColor(R.color.colorIndegoSecondaryText));
                    break;
                case 3:
                    holder.MainTitleBg.setTextColor(context.getResources().getColor(R.color.colorOrangeDark));
                    holder.Title.setTextColor(context.getResources().getColor(R.color.colorOrangePrimaryText));
                    holder.LearnMore.setTextColor(context.getResources().getColor(R.color.colorOrangeDark));
                    holder.Subtitle.setTextColor(context.getResources().getColor(R.color.colorOrangeSecondaryText));
                    break;
                default: //Default Theme Case
                    holder.MainTitleBg.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    holder.Title.setTextColor(context.getResources().getColor(R.color.colorPrimaryText));
                    holder.LearnMore.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    holder.Subtitle.setTextColor(context.getResources().getColor(R.color.colorSecondaryText));
                    break;
            }

        }else{
            //Default Theme
            holder.MainTitleBg.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.Title.setTextColor(context.getResources().getColor(R.color.colorPrimaryText));
            holder.LearnMore.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.Subtitle.setTextColor(context.getResources().getColor(R.color.colorSecondaryText));
        }

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        HomePageListInformation current = data.get(position);

        holder.MainTitleTx.setText(current.MainTitleTextId);
       // holder.MainTitleTx.setAllCaps(true);
        holder.Title.setText(current.TitleTextId);
        holder.myImage.setImageResource(current.ImageId);
        holder.myHelp.setImageResource(current.helpId);
        holder.Subtitle.setText(current.SubtitleTextId);
        holder.Share.setText(current.ShareId);
        holder.LearnMore.setText(current.LearnMoreId);
        holder.MainTitleTx.setTextColor(Color.parseColor("#FFFFFF"));
        holder.MainTitleBg.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

        if (position == expandedPosition) {
            holder.MainTitleBg.setVisibility(View.GONE);
            holder.MainTitleTx.setVisibility(View.GONE);
            holder.myHelp.setVisibility(View.VISIBLE);
            if(isFirstRun)
            holder.ExpandedArea.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_down_long));
            holder.ExpandedArea.setVisibility(View.VISIBLE);
            holder.myImage.setClickable(false);
            holder.FullViewlayout.setAlpha(1);
            holder.myImage.setAlpha((float)1.0);

        } else {
            holder.MainTitleBg.setVisibility(View.VISIBLE);
            holder.MainTitleTx.setVisibility(View.VISIBLE);
            holder.myHelp.setVisibility(View.INVISIBLE);
            //holder.ExpandedArea.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_up_long));
            /*holder.ExpandedArea.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.ExpandedArea.setVisibility(View.GONE);
                }
            }, 00);*/
            holder.ExpandedArea.setVisibility(View.GONE);
            holder.myImage.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in));
            //holder.FullViewlayout.setAlpha((float)0.7);
            holder.myImage.setAlpha((float)0.7);
            holder.myImage.setClickable(true);
            holder.MainTitleBg.setAlpha((float)1.0);
            holder.MainTitleTx.setAlpha(1);

        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RelativeLayout ImageLayout;
        public LinearLayout ExpandedArea, LearnMore_Layout, FullViewlayout;
        public TextView Share, LearnMore;
        public ImageView myImage, myHelp;
        //public RippleView LearnMore_rippleView;
        public TextView MainTitleBg,MainTitleTx;
        public TextView Title, Subtitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            Share = (TextView) itemView.findViewById(R.id.Share_ID);
            //LearnMore_rippleView = (RippleView) itemView.findViewById(R.id.Learn_more_ripple_view);
            ImageLayout = (RelativeLayout) itemView.findViewById(R.id.ImageLayout);
            ExpandedArea = (LinearLayout) itemView.findViewById(R.id.ExpandedArea);
            LearnMore_Layout = (LinearLayout) itemView.findViewById(R.id.Learn_More_layout);
            FullViewlayout = (LinearLayout) itemView.findViewById(R.id.FullViewLayout);
            LearnMore = (TextView) itemView.findViewById(R.id.LearnMore_ID);
            myImage = (ImageView) itemView.findViewById(R.id.myImageView);
            MainTitleBg = (TextView) itemView.findViewById(R.id.myImageViewTextBg);
            MainTitleTx=(TextView) itemView.findViewById(R.id.main_title_text);
            Title = (TextView) itemView.findViewById(R.id.homePage_titleText_id);
            Subtitle = (TextView) itemView.findViewById(R.id.Home_Page_subtitleText);
            myHelp = (ImageView) itemView.findViewById(R.id.Home_Page_help_ID);



            MainTitleBg.setOnClickListener(this);
            myImage.setOnClickListener(this);
            Share.setOnClickListener(this);
            LearnMore.setOnClickListener(this);
            //myHelp.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                //case R.id.Main_Title_layout:
                case R.id.myImageView:
                case R.id.myImageViewTextBg:

                    // Check for an expanded view, collapse if you find one
                    if (expandedPosition >= 0) {
                        int prev = expandedPosition;
                        notifyItemChanged(prev);
                    }

                    // Set the current position to "expanded"
                    expandedPosition = getPosition();
                    if(getPosition()==2) {
                        Share.setVisibility(View.GONE);
                    }
                    isFirstRun=true;
                    myImage.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_out_from_last_state));
                    notifyItemChanged(expandedPosition);
                    break;

                case R.id.LearnMore_ID:
                    LearnMoreIsClicked=true;
                    //v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.button_click));
                    startHomeFragemts();
                    break;

                case R.id.Share_ID:

                    mShareIdPosition = getPosition();
                    switch(getPosition()){
                        case 0:
                            shareID = "\nProgramming Libraries\n\nWide Range of Programming Tutorials With best in class examples\nDownload Now! & Become a Developer\n";
                            break;
                        case 1:
                            shareID = "\nProgramming Quiz\n\nA Wonderful collections of MCQ\nDownload Now! & Test YourSelf , Become Expert\n";
                            break;
                    }
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try
                            { Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("text/plain");
                                String sTit= "******** Being Programmer ********\n\n";
                                i.putExtra(Intent.EXTRA_SUBJECT, "Being Programmer");
                                i.putExtra(Intent.EXTRA_TITLE, "******** Being Programmer ********\n\n");

                                String sAux = "\nLet me Recommend you this Wonderful Tutorial Application.Worlds First Well Designed Programming tutorial Android App!\n\n";
                                sAux =  sAux + sTit + shareID +"\nDownload Link : https://www.mediafire.com/?yiei58s4vhuf48y \n\n";
                                i.putExtra(Intent.EXTRA_TEXT, sAux);
                                //i.putExtra(Intent.EXTRA_TEXT,"\n\nDevelopers:\nSahil Jalan & Deppak Pandey\n");
                                context.startActivity(Intent.createChooser(i, "Choose One"));
                            }
                            catch(Exception e)
                            {
                                e.toString();
                            }
                        }
                    }, 370);



                    break;

                case R.id.Home_Page_help_ID:
                    //MainTitleBg.setVisibility(View.VISIBLE);
                    //MainTitleTx.setVisibility(View.VISIBLE);
                    //myHelp.setVisibility(View.INVISIBLE);
                    //ExpandedArea.setVisibility(View.GONE);
                    //FullViewlayout.setAlpha(1);
                    break;



            }

        }

        private void startHomeFragemts() {

            final int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,128,context.getResources().getDisplayMetrics());
            //Unlock Screen Orientation
            //Screen.unlockOrientation(((MainActivity)context));
            mHomePagePosition = getPosition();
            switch (getPosition()){
                case 1:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.toolbar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_pager));
                            MainActivity.toolbar.setTitle("Programming Quiz");
                            //MainActivity.toolbar.setBackgroundResource(R.drawable.chakboard2);
                            //MainActivity.toolbar.setScaleY((float) 1.1);
                            //MainActivity.toolbar.setScaleX((float) 1.0);
                            MainActivity.toolbar.getLayoutParams().height = dip;
                            MainActivity.myPager.setCurrentItem(0, true);
                        }
                    }, 450);

                    break;
                case 0:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.toolbar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_pager));
                            MainActivity.toolbar.setTitle("Programming Libraries");
                           // MainActivity.toolbar.setBackgroundResource(R.drawable.library);
                           // MainActivity.toolbar.setScaleY((float) 1.1);
                            //MainActivity.toolbar.setScaleX((float) 1.0);
                            //MainActivity.toolbar.setSubtitleTextColor(Color.BLACK);
                            //MainActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                    //WindowManager.LayoutParams.FLAG_FULLSCREEN);
                            //MainActivity.toolbar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in));
                            //MainActivity.toolbar.setPadding(0,0,0,8);

                            MainActivity.toolbar.getLayoutParams().height = dip;
                            MainActivity.myPager.setCurrentItem(0, true);
                        }
                    }, 450);
                    break;
                case 2:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.toolbar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_pager));
                            MainActivity.toolbar.setTitle("Programs");

                            //MainActivity.toolbar.setBackgroundResource(R.drawable.programs);
                            //MainActivity.toolbar.getLayoutParams().height = dip;
                            //MainActivity.toolbar.setScaleY((float) 1.3);
                            //MainActivity.toolbar.setScaleX((float) 1.0);
                            MainActivity.toolbar.getLayoutParams().height = dip;
                            MainActivity.myPager.setCurrentItem(0, true);
                        }
                    }, 450);
                    break;
            }
        }
    }

    /**
     * Created by Sahil_Jalan on 16-03-2015.
     */
    public static class HomePageListInformation {
        public int ImageId,helpId;
        public String MainTitleTextId,TitleTextId,SubtitleTextId,ShareId,LearnMoreId;
    }
}

