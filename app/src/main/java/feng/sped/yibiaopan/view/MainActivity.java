package feng.sped.yibiaopan.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class MainActivity extends Activity {
    SpeedBiaoPanView speedBiaoPanView;
    SeekBar mSebar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_biao_pan);
        speedBiaoPanView = (SpeedBiaoPanView) findViewById(R.id.speed_biao_pan);
        mSebar = (SeekBar) findViewById(R.id.seekbar);
        mSebar.setMax(180);
        mSebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float percount = (float) progress / 5 + 1;
                float totaoDegress = (float) 3 * progress / 2;
                Log.e("biao_pan", "count == " + percount + "....degress == " + totaoDegress + "....speed===" +
                    progress);
                speedBiaoPanView.setPercentCount((int) percount, totaoDegress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
