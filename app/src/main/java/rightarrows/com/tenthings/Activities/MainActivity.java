package rightarrows.com.tenthings.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import rightarrows.com.tenthings.Adapter.ViewPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import rightarrows.com.tenthings.Fragments.Entertainment;
import rightarrows.com.tenthings.Fragments.Foods;
import rightarrows.com.tenthings.Fragments.Places;
import rightarrows.com.tenthings.Fragments.Shopping;
import rightarrows.com.tenthings.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Daily Works");
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {

            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
             adapter.addFragment(new Foods(), "Foods");
             adapter.addFragment(new Places(), "Places");
             adapter.addFragment(new Shopping() , "ShoppingAdapter");
             adapter.addFragment(new Entertainment(),"Entertainment");
             viewPager.setAdapter(adapter);
    }
}
