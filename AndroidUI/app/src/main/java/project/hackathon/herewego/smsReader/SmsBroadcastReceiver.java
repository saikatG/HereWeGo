package project.hackathon.herewego.smsReader;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import project.hackathon.herewego.models.TripDetailsFromSms;
import project.hackathon.herewego.R;
import project.hackathon.herewego.ui.activity.HomeActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();
    String[] result = new String[2];

    public void onReceive(Context context, Intent intent) {

        //   Intent service = new Intent(context, BackgroundService.class);
        //  context.startService(service);

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                    Thread.sleep(4000);
                    //         ToDoActivity ob = new ToDoActivity();
                    // String[] result;
                    //message = message.toLowerCase();
                    result = readMessage(message);

                    System.out.println(message);
                    //   post(result);
                    //  Log.i("Smm", "senderNum: " + senderNum + "; message: " + "Posted");*/
                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();

                    NotificationManager notificationManager1= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                    Intent innt=new Intent(context,HomeActivity.class);// TargetActivity is opened when you click on the notification.
                    //PendingIntent pi= PendingIntent.getActivity(context, 0, innt, 0);

// Build notification
// Actions are just fake
                    if (processMessageAndPopulateDetails(message, innt)) {
                        PendingIntent pi= PendingIntent.getActivity(context, 0, innt, PendingIntent.FLAG_CANCEL_CURRENT);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                        Notification myNotification = builder.setContentIntent(pi)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher))
                                .setAutoCancel(true)
                                .setContentTitle("NewTrip Suggestion")
                                .setContentText("Trip suggestion you may like..").build();
//Show notification
                        notificationManager1.notify(1, myNotification);
                    }
                } // end for loop


            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }


    public String[] readMessage(String smsReceived){

        String body = smsReceived;
        String msg ="";
        String cat ="";
        String[] sms = new String[2];

        return sms;
    }

    public boolean processMessageAndPopulateDetails(String receivedSms, Intent intent){
        String[] parts = receivedSms.split(",");
        if (parts.length != 6){
            return false;
        }

        if (!parts[0].startsWith("From") ||
                !parts[1].startsWith("To") ||
                !parts[2].startsWith("StartDate") ||
                !parts[3].startsWith("StartTime") ||
                !parts[4].startsWith("ReturnDate") ||
                !parts[5].startsWith("ReturnTime")){
            return false;
        }

        //This means the sms is of required format
        // From:HYD,To:GOA,StartDate:30-07-2016,StartTime:12-30,ReturnDate:4-8-2016,ReturnTime:16-50
        String[] temp = parts[0].split("-");
        String srcName = temp[1];
        temp = parts[1].split("-");
        String destName =  temp[1];
        temp = parts[2].split("-");
        String startDate =  temp[1];
        temp = parts[3].split("-");
        String startTime =  temp[1];
        temp = parts[4].split("-");
        String returnDate =  temp[1];
        temp = parts[5].split("-");
        String returnTime =  temp[1];

        String startDateTimeFormatter = startDate + " " +  startTime;
        String returnDateTimeFormatter = returnDate + " " +  returnTime;
        Date stDate, rtDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try
        {
            stDate = simpleDateFormat.parse(startDateTimeFormatter);
            rtDate = simpleDateFormat.parse(returnDateTimeFormatter);

            //System.out.println("date : "+simpleDateFormat.format(date));

            TripDetailsFromSms tripDetails = new TripDetailsFromSms(srcName, destName,stDate, rtDate);
            intent.putExtra("tripDetails",tripDetails);

        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }

        return true;
    }


}
