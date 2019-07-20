// Register Receiver dynamically / programmatically
//other way is statically in Manifest file
/*When you register your receiver in this way, it lives for as long as the component lives and Android sends events to this receiver until the creating component itself gets destroyed.

It's your task to handle the lifecycle correctly. Thus when you add a receiver dynamically, take care to unregister the same receiver in the onPause() method of your Activity!

I suggest to register the receiver in the onResume() method of your Activity and to unregister it in your onPause() method:
package bg.easy.demo1.systembroadcastreceiver;
*/
// https://www.grokkingandroid.com/android-tutorial-broadcastreceiver/
//choose dynamically when receiver is needed only when app is ative
//choose static when receiver is needed anytime


package bg.easy.demo1.systembroadcastreceiver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    IntentFilter filter = new IntentFilter();
    //    filter.addAction(getPackageName() + "android.net.conn.CONNECTIVITY_CHANGE");
    //    registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onPause() {
        if (myReceiver != null) {
            this.unregisterReceiver(myReceiver);
            myReceiver = null;
        }
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (myReceiver != null) {
            this.unregisterReceiver(myReceiver);
            myReceiver = null;
        }
    }

    @Override
    protected void onResume() {
        this.myReceiver = new MyReceiver();
        registerReceiver(
                this.myReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        super.onResume();
    }
}
