package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessageSender extends Thread{

    private final String[] messages;
    private final Context myContext;

    Boolean isRunning;

    public MessageSender(Context aContext, String aConcatenatedString){
        myContext = aContext;

        // create array list of strings from the concatenated string separated by ","
        messages = aConcatenatedString.split(",");
    }

    @Override
    public void run() {
        isRunning = true;

        while(isRunning){
            try {
                sendMessage();
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopThread(){
        isRunning = false;
    }

    private void sendMessage(){

        for (String message : messages){

            Log.d("MessageSender", "Sending message: " + message);

            Intent intent = new Intent();
            intent.setAction("ro.pub.cs.systems.eim.practicaltest01var05");
            intent.putExtra("message", message);

            myContext.sendBroadcast(intent);
        }

    }
}
