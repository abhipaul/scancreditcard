package scan.kony.com.scancreditdemo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;

/**
 * Created by KIT912 on 1/12/2018.
 */

public class ScanFFI extends Activity implements PassCreditCardDataListener{

    private static Function cb;

    public static void CallCreditCardFFI(Function callback)
    {
        cb=callback;
        ScanFFI objScan = new ScanFFI();
        objScan.goToMyActivity();
    }

    public void goToMyActivity()
    {
        MainActivity.initializeDataListener(this);
        Intent in = new Intent(KonyMain.getActivityContext(), MainActivity.class);
        KonyMain.getActContext().startActivity(in);
    }

    @Override
    public void passCreditData(String data) {
        try
        {
            Log.d("StandardLib", "data binding " + data);
            cb.executeAsync(new Object[] { data });
            System.out.println(">>>>>>>>>>>>>>>>>>SCAN_RESULT is " + data);
            finish();
        }
        catch (Exception e)
        {
            System.out.println(">>>>>>>>>>>>>>>>>>JSCallback invocation failed");
            e.printStackTrace();
        }

    }
}
