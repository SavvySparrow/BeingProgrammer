package pj.ess.dee.beingaprogrammer.Toolbars;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import pj.ess.dee.beingaprogrammer.Activities.MainActivity;
import pj.ess.dee.beingaprogrammer.R;

/**
 * Created by Sahil Jalan on 2/17/2015.
 */
public class InitializeStatus_ToolBarFragment extends Fragment {
    private final String TAG = "InitializeStatus_ToolBarFragment";


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        initStart();
    }

    public void initStart() {
        //Set Translucent Status Bar
        initializeStatusBar();
        //Set and Initialize Status Bar TintColor
        initializeTintColor();

    }

    private void initializeTintColor() {
        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        //set color
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {

            if (MainActivity.Set_Theme) {
                switch (MainActivity.Theme_id) {
                    case 1:
                        tintManager.setTintResource(R.color.colorTealDark);
                        break;
                    case 2:
                        tintManager.setTintResource(R.color.colorIndegoDark);
                        break;
                    case 3:
                        tintManager.setTintResource(R.color.colorOrangeDark);
                        break;
                    default: //Default Theme Case
                        tintManager.setTintResource(R.color.colorPrimaryDark);
                        break;
                }
            } else { // Default Theme
                tintManager.setTintResource(R.color.colorPrimaryDark);
            }
        }else
            tintManager.setTintResource(R.color.transparent);
    }

    private void initializeStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
