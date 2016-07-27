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

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import project.hackathon.herewego.Models.Location;
import project.hackathon.herewego.Models.LocationsManager;
import project.hackathon.herewego.Models.Review;
import project.hackathon.herewego.Models.TripDetailsFromSms;
import project.hackathon.herewego.Models.Tuple;
import project.hackathon.herewego.R;
import project.hackathon.herewego.ui.activity.AddLocation;
import project.hackathon.herewego.ui.activity.EditTrip;
import project.hackathon.herewego.ui.activity.HomeActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

                    Intent innt=new Intent(context,AddLocation.class);// TargetActivity is opened when you click on the notification.
                    //PendingIntent pi= PendingIntent.getActivity(context, 0, innt, 0);

// Build notification
// Actions are just fake
                    LocationsManager location = LocationsManager.getInstance();
                    ArrayList<Location> triplocations = new ArrayList<Location>();
                    Location l1 = new Location("l1", "Golconda Fort", 17.3833, 78.4011, 4.0, "fort", true, "http://i10.dainikbhaskar.com/thumbnail/330x286/web2images/www.dailybhaskar.com/2014/04/02/9411_golconda_fort.jpg","http://hyderabadadvisor.com/wp-content/uploads/2011/05/Golconda-Fort-2.jpg","http://planetden.com/wp-content/uploads/2014/03/golconda-fort.jpg" ,1);
                    Location l2 = new Location("l2", "Salar Jung Museum", 17.3714, 78.4804, 3.5, "fort", true, "http://www.hyd.co.in/images/salarjung-thumb.jpg","http://hyderabadadvisor.com/wp-content/uploads/2011/05/Salar-Jung-Museum-1.jpg","http://www.goldenleaf.in/media/images/ToursAndTravelsGallery/salar2_3.jpeg",1);
                    Location l3 = new Location("l3", "Hussain Sagar", 17.4239, 78.4738, 4.5, "fort", true, "http://hyderabad-india-online.com/wp-content/uploads/2014/01/hussain-sagar_150x150px.png","http://www.hellohyderabad.com/Hello-Hyderabad/Tourism/Tourism-images/hussain-sagar.jpg", "http://hyderabadadvisor.com/wp-content/uploads/2011/05/Hussain-Sagar-4.jpg",1);
                    Location l4 = new Location("l4", "Charminar", 17.3616, 78.4747, 5.0, "fort", true, "http://www.helloindiatravel.com/gifs/charminar.jpg","http://cityrockz.com/wp-content/uploads/2012/02/Charminar-Rockz.jpg","https://trabol.s3.amazonaws.com/images/4476/2.jpg" ,1);
                    Location l5 = new Location("l5", "Ramoji Film City", 17.2543, 78.6808, 4.5, "fort", false, "http://www.poultryindia.co.in/media/Ramoji-Film-City.jpg","http://www.hyderabadtourism.travel/images/tourist-places/ramoji-filmcity-hyderabad/ramoji-film-city-hyderabad-india.jpg","http://upload.wikimedia.org/wikipedia/commons/d/da/Ramoji_Film_City,_Hyderabad_-_views_from_Ramoji_Film_City_(1).JPG", 1);
                    Location l6 = new Location("l6", "Birla Mandir", 17.4062, 78.4691, 5.0, "fort", false, "http://theanwar.com/wp-content/uploads/2011/01/birlamandir.jpg","http://1.bp.blogspot.com/-MWr4SeJAqSQ/T9H_4cNzinI/AAAAAAAAIhU/p_5nbeMi5H8/s1600/DSCF0309.JPG","http://www.mybengaluru.com/attachments/communities/53__cimg_0812.jpg",1);
                    // Set variables of l1
                    l1.setName("Golconda Fort");
                    l1.setCity("Hyderabad");
                    l1.setCountry("India");
                    l1.setSummary("Golkonda, also known as Golconda or Golla konda, is a citadel and fort in Southern India and was the capital of the medieval sultanate of the Qutub Shahi dynasty, is situated 11 kilometres west of Hyderabad.");
                    l1.setAddress("Ibrahim Bagh, Hyderabad, Telangana 500008, India");
                    l1.setContactNumber(" +91 40 2351 2401");
                    l1.setWebsiteUrl("telanganatourism.gov.in");


                    // Set variables of l2
                    l2.setName("Salar Jung Museum");
                    l2.setCity("Hyderabad");
                    l2.setCountry("India");
                    l2.setSummary("The Salar Jung Museum is an art museum located at Darushifa, on the southern bank of the Musi river in the city of Hyderabad, Telangana, India. It is one of the three National Museums of India.");
                    l2.setAddress(" Salar Jung Road, Darulshifa, Hyderabad, Telangana 500002, India");
                    l2.setContactNumber("+91 40 2457 6443");
                    l2.setWebsiteUrl("telanganatourism.gov.in");

                    // Set variables of l3
                    l3.setName("Hussain Sagar");
                    l3.setCity("Hyderabad");
                    l3.setCountry("India");
                    l3.setSummary("Hussain Sagar is a lake in Hyderabad built by Hazrat Hussain Shah Wali in 1562, during the rule of Ibrahim Quli Qutub Shah. It is spread across an area of 5.7 square kilometers and is fed by River Musi.");
                    l3.setAddress(" Necklace Road, Hyderabad, Telangana 500002, India");
                    l3.setContactNumber("+91 40 2457 6445");
                    l3.setWebsiteUrl("telanganatourism.gov.in");

                    // Set variables of l4
                    l4.setName("Charminar");
                    l4.setCity("Hyderabad");
                    l4.setCountry("India");
                    l4.setSummary("The Charminar, constructed in 1591 CE, is a monument and mosque located in Hyderabad, Telangana, India. The landmark has become a global icon of Hyderabad, listed among the most recognized structures of India.");
                    l4.setAddress("Hyderabad, Telangana 500002, India");
                    l4.setContactNumber(" +91 40 6674 5986");
                    l4.setWebsiteUrl("telanganatourism.gov.in");
// Set variables of l5
                    l5.setName("Ramoji Film City");
                    l5.setCity("Hyderabad");
                    l5.setCountry("India");
                    l5.setSummary("The Ramoji Film City in India is located in Hyderabad. At 2000 acres, it is the largest integrated film city in the world.");
                    l5.setAddress("Anaspur Village, Hayathnagar Mandal, Hyderabad, Telangana 501512, India");
                    l5.setContactNumber(" +91 1800 4250 9999");
                    l5.setWebsiteUrl("telanganatourism.gov.in");
// Set variables of l6
                    l6.setName("Birla Mandir");
                    l6.setCity("Hyderabad");
                    l6.setCountry("India");
                    l6.setSummary("Birla Mandir is a Hindu temple, built on a 280 feet high hillock called Naubath Pahad on a 13 acres plot. The construction took 10 years and was opened in 1976 by Swami Ranganathananda of Ramakrishna Mission.");
                    l6.setAddress("Hill Fort Rd, Hyderabad, Telangana 500004, India");
                    l6.setContactNumber(" +91 40 2345 0165");
                    l6.setWebsiteUrl("telanganatourism.gov.in");

                    Review R1 = new Review("http://3.bp.blogspot.com/-coH7JStVgw8/TmvdyNQBWhI/AAAAAAAAGQw/V63AT0PZF9o/s1600/Funny+cartoon+faces+pictures2.jpg","Great Place",5,"Parijat1509", new DateTime(DateTime.now()));
                    Review R2 = new Review("http://2.bp.blogspot.com/-DyWKseCweTM/TmvdwjR3U7I/AAAAAAAAGQo/MDr1dutzn7Y/s1600/Funny+cartoon+faces+pictures.jpg","Awesome Place",4,"Saikat1992",new DateTime(DateTime.now()));

                    String input = "09.00.00 AM";
                    String pattern = "hh.mm.ss aa";

                    String input1 = "05.00.00 PM";
                    String pattern1 = "hh.mm.ss aa";

                    LocalDateTime localDateTime = LocalDateTime.parse(input, DateTimeFormat.forPattern(pattern));
                    LocalDateTime localDateTime1 = LocalDateTime.parse(input1, DateTimeFormat.forPattern(pattern));
                    HashMap<String, List<Tuple<DateTime, DateTime>>> openSchedule = new HashMap<>();
                    List<Tuple<DateTime,DateTime> > dates = new ArrayList<Tuple<DateTime, DateTime>>() ;
                    Tuple<DateTime,DateTime> tuple = new Tuple<DateTime, DateTime>(localDateTime.toDateTime(),localDateTime1.toDateTime());
                    dates.add(tuple);

                    openSchedule.put("Monday", dates);
                    openSchedule.put("Tuesday", dates);
                    openSchedule.put("Wednesday", dates);
                    openSchedule.put("Thursday", dates);
                    openSchedule.put("Friday", dates);
                    openSchedule.put("Saturday", dates);
                    openSchedule.put("Sunday", dates);

                    l1.setOpenSchedule(openSchedule);
                    l2.setOpenSchedule(openSchedule);
                    l3.setOpenSchedule(openSchedule);
                    l4.setOpenSchedule(openSchedule);
                    l5.setOpenSchedule(openSchedule);
                    l6.setOpenSchedule(openSchedule);

                    ArrayList<Review> reviews= new ArrayList<Review>();
                    reviews.add(R1);
                    reviews.add(R2);
                    l1.setReviews(reviews);
                    l2.setReviews(reviews);
                    l3.setReviews(reviews);
                    l4.setReviews(reviews);
                    l5.setReviews(reviews);
                    l6.setReviews(reviews);


                    LocationsManager locationsManager = LocationsManager.getInstance();
                    locationsManager.addLocation(l1, true);
                    locationsManager.addLocation(l2, true);
                    locationsManager.addLocation(l3, true);

                    locationsManager.addLocation(l4, false);
                    locationsManager.addLocation(l5, false);
                    locationsManager.addLocation(l6, false);
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
