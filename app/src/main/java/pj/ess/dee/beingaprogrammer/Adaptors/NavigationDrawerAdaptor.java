package pj.ess.dee.beingaprogrammer.Adaptors;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import pj.ess.dee.beingaprogrammer.Activities.AboutUs;
import pj.ess.dee.beingaprogrammer.Activities.HelpAndFeedbackActivity;
import pj.ess.dee.beingaprogrammer.Activities.MainActivity;
import pj.ess.dee.beingaprogrammer.Activities.MyTheme;
import pj.ess.dee.beingaprogrammer.Activities.TabActivity;
import pj.ess.dee.beingaprogrammer.Fragments.NavigationDrawerFragment;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil Jalan on 2/20/2015.
 */
public class NavigationDrawerAdaptor extends RecyclerView.Adapter<NavigationDrawerAdaptor.MyViewHolder> {

    private Handler mHandler = new Handler();
    private LayoutInflater inflate;
    private Context context;
    List<DrawerInformation> data = Collections.emptyList();

    public NavigationDrawerAdaptor(Context context, List<DrawerInformation> data) {
        this.context = context;
        inflate = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        DrawerInformation current = data.get(position);
        if (MainActivity.Set_Theme == true) {
            switch (MainActivity.Theme_id) {
                case 1:
                    holder.icon.setImageResource(current.iconId1);
                    break;
                case 2:
                    holder.icon.setImageResource(current.iconId2);
                    break;
                case 3:
                    holder.icon.setImageResource(current.iconId3);
                    break;
                default: //Default Theme Case
                    holder.icon.setImageResource(current.iconId_def);
                    break;
            }
        } else { // Default Theme
            holder.icon.setImageResource(current.iconId_def);
        }

        holder.title.setText(current.textId);
        //holder.Subtitle.setText(current.SubTextId);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout button;
        ImageView icon;
        TextView title, Subtitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            button = (LinearLayout) itemView.findViewById(R.id.drawerListButton);
            icon = (ImageView) itemView.findViewById(R.id.drawer_listviewImage);
            title = (TextView) itemView.findViewById(R.id.drawer_listviewText);
            //Subtitle = (TextView) itemView.findViewById(R.id.drawer_listviewSecondText);

            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (getPosition()) {
                case 0:
                    final Intent myHome = new Intent(context, MainActivity.class);
                    //final Intent myTab = new Intent(context, TabActivity.class);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if ((MainActivity.myPager.getCurrentItem() == 0 && !TabActivity.isRunning) || (MainActivity.myPager.getCurrentItem() == 1 && !TabActivity.isRunning)) {

                                MainActivity.myPager.setCurrentItem(1);
                                //Screen.lockOrientation((MainActivity) context);
                                MainActivity.toolbar.setTitle("Being Programmer");
                                int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, context.getResources().getDisplayMetrics());
                                MainActivity.toolbar.getLayoutParams().height = dp;
                                NavigationDrawerFragment.mDrawerLayout.closeDrawers();
                                NavUtils.shouldUpRecreateTask((Activity) context, myHome);
                            }

                            else {
                                //NavigationDrawerFragment.mDrawerLayout.closeDrawers();
                                //NavUtils.shouldUpRecreateTask((Activity) context, myTab);
                                myHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(myHome);
                                ((TabActivity) context).finish();
                            }
                        }
                    }, 300);
                    //NavigationDrawerFragment.mDrawerLayout.closeDrawers();
                    //((Activity) context).finish();
                    // Intent myhome = new Intent(context, MainActivity.class);
                    //context.startActivity(myhome);
                    //Toast.makeText(context, "Item Clicked At " + getPosition(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            context.startActivity(new Intent(context, MyTheme.class));
                            NavigationDrawerFragment.mDrawerLayout.closeDrawers();
                        }
                    }, 300);
                    break;
                case 2:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //context.startActivity(new Intent(context, HelpAndFeedbackActivity.class));
                            //NavigationDrawerFragment.mDrawerLayout.closeDrawers();
                            Toast.makeText(context, "In Developing Stage\n Wait For an Update", Toast.LENGTH_SHORT).show();

                        }
                    }, 300);
                    break;
                case 3:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            context.startActivity(new Intent(context, HelpAndFeedbackActivity.class));
                            NavigationDrawerFragment.mDrawerLayout.closeDrawers();
                        }
                    }, 500);
                    break;
                case 4:
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent myhome = new Intent(context, MainActivity.class);

                            Log.d("MyActivity", "Closed Drawer Layout First");
                            NavigationDrawerFragment.mDrawerLayout.closeDrawers();


                            Log.d("MyActivity", "ReCreate Task");
                            NavUtils.shouldUpRecreateTask((Activity) context, myhome);

                            AboutUs cdd = null;
                            cdd = new AboutUs((Activity) context);
                            cdd.show();
                        }
                    }, 500);
                    break;
            }
        }
    }

    /**
     * Created by sahil on 2/20/2015.
     */
    public static class DrawerInformation {
        public int iconId_def,iconId1, iconId2, iconId3;
        public String textId;//,SubTextId;

    }
}
