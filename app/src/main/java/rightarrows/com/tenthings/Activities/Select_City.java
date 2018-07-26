package rightarrows.com.tenthings.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rightarrows.com.tenthings.Model.Config;
import rightarrows.com.tenthings.R;

public class Select_City extends AppCompatActivity implements View.OnClickListener {
    ImageView jaipur,bikaner,udaipur,agra,ajmer,alwar,jaisalmer,jodhpur;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__city);
        SharedPreferences sharedPreferences = Select_City.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean(Config.SELECT_CITY,false))
        {
            Intent intent = new Intent(Select_City.this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            init();
            jaipur.setOnClickListener(this);
            bikaner.setOnClickListener(this);
            ajmer.setOnClickListener(this);
            alwar.setOnClickListener(this);
            agra.setOnClickListener(this);
            jodhpur.setOnClickListener(this);
            jaisalmer.setOnClickListener(this);
            udaipur.setOnClickListener(this);

            JSONObject jsonObject= new JSONObject();
            try {
                JSONArray jsonArray= jsonObject.getJSONArray("places");
                for(int i=0;i<jsonArray.length();i++)
                {
                    jsonArray.getString(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    private void init() {
        jaipur= findViewById(R.id.img_jaipur);
        jaisalmer= findViewById(R.id.img_jaisalmer);
        bikaner= findViewById(R.id.img_bikaner);
        udaipur= findViewById(R.id.img_udaipur);
        agra= findViewById(R.id.img_agra);
        ajmer= findViewById(R.id.img_ajmer);
        alwar= findViewById(R.id.img_alwar);
        jodhpur= findViewById(R.id.img_jodhpur);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.img_jaipur)
        {
            city=Config.CITY_JAIPUR;
            sendCity();
        }

        else if(view.getId()==R.id.img_agra)
        {
            city=Config.CITY_AGRA;
            sendCity();
        }

        else if(view.getId()==R.id.img_bikaner)
        {
            city=Config.CITY_BIKANER;
            sendCity();
        }

        else if(view.getId()==R.id.img_udaipur)
        {
            city=Config.CITY_UDAIPUR;
            sendCity();
        }

        else if(view.getId()==R.id.img_ajmer)
        {
            city=Config.CITY_AJMER;
            sendCity();
        }

        else if(view.getId()== R.id.img_alwar)
        {
            city=Config.CITY_ALWAR;
            sendCity();
        }

        else if(view.getId()==R.id.img_jodhpur)
        {
            city=Config.CITY_JODHPUR;
            sendCity();
        }

        else
        {
            city=Config.CITY_JAISALMER;
            sendCity();
        }

    }

    public void sendCity()
    {
        SharedPreferences sharedPreferences= Select_City.this.getSharedPreferences(Config.SHARED_PREF_NAME_VALUE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Config.SELECT_CITY,true);
        editor.putString(Config.CITY,city);
        Intent intent = new Intent(Select_City.this,MainActivity.class);
        startActivity(intent);
    }
}