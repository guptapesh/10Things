package rightarrows.com.tenthings.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import rightarrows.com.tenthings.Adapter.EntertainAdapter;
import rightarrows.com.tenthings.Adapter.FoodAdapter;
import rightarrows.com.tenthings.Adapter.ShoppingAdapter;
import rightarrows.com.tenthings.Model.EnterPojo;
import rightarrows.com.tenthings.Model.FoodPojo;
import rightarrows.com.tenthings.R;

public class Entertainment extends Fragment {
    private ListView listView;
    private ArrayList<EnterPojo> enteral = new ArrayList<>();
    private EntertainAdapter entertainAdapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.food, container, false);

        listView= rootView.findViewById(R.id.lv_food);

        entertainAdapter = new EntertainAdapter(getContext(),R.layout.item_food,enteral);
        listView.setAdapter(entertainAdapter);

        fetchdata();

        return rootView;

    }

    private void fetchdata() {

        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading....");
        dialog.setCancelable(false);
        dialog.show();

        final StringRequest request = new StringRequest(Request.Method.GET,"http://hoax.rightarrows.com/api.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        entertainAdapter.clear();
                        dialog.cancel();

                        try {
                            handleresponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.cancel();
                Toast.makeText(getActivity(), "Server On Maintenance", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getActivity()).add(request);


    }

    private void handleresponse(String response) throws JSONException {

        JSONArray jsonArray = new JSONArray(response);
        for(int i=2; i < 3;i++)
        {
            JSONObject jsonObject =  jsonArray.getJSONObject(i);
            for(int p=0;p<jsonObject.length();p++)
            {

                EnterPojo pojo = new EnterPojo();
                Log.d("heyraju",jsonObject.getString(p+""));
                pojo.setCategory(jsonObject.getString(p+""));
                enteral.add(pojo);
            }

            entertainAdapter.notifyDataSetChanged();
        }


    }

}
