package pj.ess.dee.beingaprogrammer.ThemePageFragemnts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import pj.ess.dee.beingaprogrammer.Activities.MainActivity;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil_Jalan on 18-04-2015.
 */
public class DefaultThemePageFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    Context context;
    ImageView imageView;
    View rootView;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView =(ImageView) rootView.findViewById(R.id.theme_pager_image_id);
        //RelativeLayout relativeLayout =(RelativeLayout) rootView.findViewById(R.id.theme_pager_layout_id);
        imageView.setImageResource(R.drawable.default_theme);
        imageView.setOnClickListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.theme_fragment_slide_page, container, false);
        return rootView;
    }


    @Override
    public void onClick(View v) {


        MainActivity.Set_Theme = true;
        MainActivity.Theme_id = 22;
        Intent myhome = new Intent(getActivity(), MainActivity.class);
        myhome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myhome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myhome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myhome);
        //((MainActivity)context).Exit();

    }
}

