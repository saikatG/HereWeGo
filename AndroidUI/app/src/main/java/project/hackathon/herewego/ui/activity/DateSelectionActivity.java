package project.hackathon.herewego.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import project.hackathon.herewego.Models.Location;
import project.hackathon.herewego.Models.LocationsManager;
import project.hackathon.herewego.Models.Review;
import project.hackathon.herewego.Models.Tuple;
import project.hackathon.herewego.R;
import project.hackathon.herewego.Models.HWGSharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DateSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);
        HWGSharedPreferences.init(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calendar calendar = Calendar.getInstance();
        StringBuilder fromDate = new StringBuilder(calendar.get(Calendar.DATE) + "/" +
                calendar.get(Calendar.MONTH) + "/" +
                calendar.get(Calendar.YEAR));
        TextView fromDateText = (TextView)findViewById(R.id.fromDate_text);
        fromDateText.setText(fromDate);
        TextView toDateText = (TextView)findViewById(R.id.toDate_text);
        toDateText.setText(fromDate);
        TextView fromTimeText = (TextView)findViewById(R.id.fromTime_text);
        fromTimeText.setText("12:00");
        TextView toTimeText = (TextView)findViewById(R.id.toTime_text);
        toTimeText.setText("12:00");
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_dateSelection);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent intent = new Intent(getApplicationContext(),AddLocation.class);
                DateSelectionActivity.this.startActivity(intent);
            }
        });
    }


    public void selectFromDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int maxYear = calendar.get(Calendar.YEAR);
        int maxMonth = calendar.get(Calendar.MONTH);
        int maxDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog =
                new DatePickerDialog(view.getContext(), setFromDate, maxYear , maxMonth , maxDay);

        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener setFromDate = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            StringBuilder fromDate = new StringBuilder(selectedDay + "/" +
                    selectedMonth + "/" +
                    selectedYear);
            TextView fromDateText = (TextView) findViewById(R.id.fromDate_text);
            fromDateText.setText(fromDate);
        }
    };

    public void selectFromTime(View view) {
        TimePickerDialog fromTimePicker = new TimePickerDialog(view.getContext(), setFromTime, 12, 0, true);
        fromTimePicker.show();
    }

    private TimePickerDialog.OnTimeSetListener setFromTime = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            StringBuilder fromTime = new StringBuilder(hourOfDay + ":" + minute);
            TextView fromTimeText = (TextView) findViewById(R.id.fromTime_text);
            fromTimeText.setText(fromTime);
        }
    };

    public void selectToDate(View view) {
        Calendar cal = Calendar.getInstance();
        int maxYear = cal.get(Calendar.YEAR);
        int maxMonth = cal.get(Calendar.MONTH);
        int maxDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog =
                new DatePickerDialog(view.getContext(), setToDate, maxYear , maxMonth , maxDay);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener setToDate = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            StringBuilder toDate = new StringBuilder(selectedDay + "/" +
                    selectedMonth + "/" +
                    selectedYear);
            TextView toDateText = (TextView) findViewById(R.id.toDate_text);
            toDateText.setText(toDate);
        }
    };

    public void selectToTime(View view) {
        TimePickerDialog toTimePicker = new TimePickerDialog(view.getContext(), setToTime, 12, 0, true);
        toTimePicker.show();
    }

    private TimePickerDialog.OnTimeSetListener setToTime = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            StringBuilder toTime = new StringBuilder(hourOfDay + ":" + minute);
            TextView toTimeText = (TextView) findViewById(R.id.toTime_text);
            toTimeText.setText(toTime);
        }
    };
}
