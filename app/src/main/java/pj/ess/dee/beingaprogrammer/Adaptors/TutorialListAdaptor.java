package pj.ess.dee.beingaprogrammer.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import pj.ess.dee.beingaprogrammer.Activities.MainActivity;
import pj.ess.dee.beingaprogrammer.Activities.TabActivity;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 16-03-2015.
 */
@SuppressWarnings("deprecation")
public class TutorialListAdaptor extends RecyclerView.Adapter<TutorialListAdaptor.MyViewHolder> {

    public static int myTutorialPosition;
    //private Handler mHandler = new Handler();
    private LayoutInflater inflate;
    private Random mrandom =new Random();
    int[] listItemBackground = new int[] { R.drawable.tutorial_list_tile_bg_dw1,R.drawable.tutorial_list_tile_bg_dw2,
            R.drawable.tutorial_list_tile_bg_dw3,R.drawable.tutorial_list_tile_bg_dw4,
            R.drawable.tutorial_list_tile_bg_dw5,R.drawable.tutorial_list_tile_bg_dw6,
            R.drawable.tutorial_list_tile_bg_dw7,R.drawable.tutorial_list_tile_bg_dw8,
            R.drawable.tutorial_list_tile_bg_dw9,R.drawable.tutorial_list_tile_bg_dw10,
            R.drawable.tutorial_list_tile_bg_dw11,R.drawable.tutorial_list_tile_bg_dw12,
            //Repeated list
            R.drawable.tutorial_list_tile_bg_dw1,R.drawable.tutorial_list_tile_bg_dw2,
            R.drawable.tutorial_list_tile_bg_dw3,R.drawable.tutorial_list_tile_bg_dw4,
            R.drawable.tutorial_list_tile_bg_dw5,R.drawable.tutorial_list_tile_bg_dw6,
            R.drawable.tutorial_list_tile_bg_dw7,R.drawable.tutorial_list_tile_bg_dw8,
            R.drawable.tutorial_list_tile_bg_dw9,R.drawable.tutorial_list_tile_bg_dw10,
            R.drawable.tutorial_list_tile_bg_dw11,R.drawable.tutorial_list_tile_bg_dw12};
    private Context context;
    List<TutorialListInformation> data = Collections.emptyList();

    public TutorialListAdaptor(Context context, List<TutorialListInformation> data) {

        this.context = context;
        inflate = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate.inflate(R.layout.custom_tutorial_list_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        TutorialListInformation current = data.get(position);

        //holder.Title.setText(current.TitleTextId);
        holder.TitleImage.setImageResource(current.ImageId);
        //holder.TileBg.setBackground(R.drawable.tutorial_list_tile_bg_dw3);
        //int listItemBackgroundPosition = position % listItemBackground.length;
        //holder.TileBg.setBackgroundResource(listItemBackground[listItemBackgroundPosition]);
        //holder.TileBg.setBackgroundColor(Color.rgb(mrandom.nextInt(65),mrandom.nextInt(65),mrandom.nextInt(65)));
        if(MainActivity.Set_Theme==true){
            switch (MainActivity.Theme_id){
                case 1:
                    holder.TileBg.setBackgroundColor(Color.rgb(mrandom.nextInt(165),mrandom.nextInt(165),mrandom.nextInt(165)));
                    break;
                case 2:
                    holder.TileBg.setBackgroundColor(Color.rgb(mrandom.nextInt(65),mrandom.nextInt(65),mrandom.nextInt(65)));
                    break;
                case 3:
                    holder.TileBg.setBackgroundColor(Color.rgb(mrandom.nextInt(189),mrandom.nextInt(183),mrandom.nextInt(107)));
                    break;
                default: //Default Theme Case
                    int listItemBackgroundPosition = position % listItemBackground.length;
                    holder.TileBg.setBackgroundResource(listItemBackground[listItemBackgroundPosition]);
                    //holder.TileBg.setBackgroundColor(Color.rgb(mrandom.nextInt(65),mrandom.nextInt(65),mrandom.nextInt(65)));
                    break;
            }

        }else{
            //Default Theme
            int listItemBackgroundPosition = position % listItemBackground.length;
            holder.TileBg.setBackgroundResource(listItemBackground[listItemBackgroundPosition]);
            //holder.TileBg.setBackgroundColor(Color.rgb(mrandom.nextInt(65),mrandom.nextInt(65),mrandom.nextInt(65)));
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

       // TextView Title;
        ImageView TitleImage;
        LinearLayout TileBg;

        public MyViewHolder(View itemView) {
            super(itemView);

           // Title = (TextView) itemView.findViewById(R.id.tutoriallist_titleText_id);
            TitleImage = (ImageView) itemView.findViewById(R.id.tutoriallist_imageview_id);
            TileBg = (LinearLayout) itemView.findViewById(R.id.tutorial_list_tile_bg_id);

        TileBg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           myTutorialPosition=getPosition();
                   context.startActivity(new Intent(context,TabActivity.class));




            //Called Tab Activity and set Current Pager According To Home Page Recycled View Clicked


            //v.setBackgroundColor(Color.rgb(mrandom.nextInt(265),mrandom.nextInt(265),mrandom.nextInt(265)));

           //TabActivity.mPager.setCurrentItem(0); //Creates NULL Pointer Exception Becoz Tab Activity Is Not Created
        }
    }

        /**
         * Created by Sahil_Jalan on 16-03-2015.
         */
        public static class TutorialListInformation {
            public int ImageId;
            //public String TitleTextId;
        }
    }

