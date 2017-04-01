/**
 *
 * Kamal
 *Importing necessary packages
 */
import android.app.Notification;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * This is the second activity which is mapped to activity_main.xml
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Thread t = null;
    TextView tv1, tv2, tv3, tv4, tv5;
    Socket cs;
    DataInputStream in;
    PrintWriter pr;
    int i = 0;
    EditText et1;
    Button bt;
    String st, rt, gt = "";
    String ip;
    int port;
    String name;
    Thread at = null;
    TextView sw;
    Handler updatetexthandler;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the layout
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        et1 = (EditText) findViewById(R.id.et);
        bt = (Button) findViewById(R.id.bt1);
        b = getIntent().getExtras();
        ip = b.getString("ip");
        name = b.getString("name");
        port = Integer.parseInt(b.getString("port"));

        /**
         * New thread used to connect client to the server.
         */
        t = new Thread(new clientConnect());
        t.start();
        bt.setOnClickListener(this); //After hitting the 'send', onClick event will be triggered.
    }



    /**
     *The following function sends messages to the server using printwriter object.
     * using printwriter, we can write message into sockets output stream buffer
     *
     */
    public void onClick(View v) {

        rt = et1.getText().toString();
        tv5.setText("android:" + rt);

        try {
            pr = new PrintWriter(cs.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv5.setVisibility(View.VISIBLE);
        pr.println(rt);
        et1.setText("");
        pr.flush(); // clears the buffer
    }

    @Override

    /**
     *when the activity stops, then the client socket will be closed.
     */
    protected void onStop() {
        super.onStop();
        try {
            cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class clientConnect implements Runnable {


        public void run() {

            try {
                //here we connect via the ip and port number that user has entered
                cs = new Socket(ip, port);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //here we creating input stream object which is used to read from socket input stream buffer
                in = new DataInputStream(cs.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //reading input from the buffer
               while(true)
            {
                try {
                    gt=in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //handler is used to update text view.
                updatetexthandler.post(new Runnable() {
                    @Override
                    public void run() {
                            tv1.setText(gt);
                    }
                });
            }



        }


    }
}





