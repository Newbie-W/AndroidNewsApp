package com.knewbie.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.knewbie.news.activity.AddNewsOrVideoActivity;
import com.knewbie.news.activity.FavoriteActivity;
import com.knewbie.news.activity.FollowActivity;
import com.knewbie.news.activity.HistoryActivity;
import com.knewbie.news.activity.InformationActivity;
import com.knewbie.news.adapter.ViewPagerAdapter;
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.fragment.HomeFragment;
import com.knewbie.news.fragment.MyFragment;
import com.knewbie.news.fragment.VideoFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //viewPager.setVisibility(View.VISIBLE);
                switch (menuItem.getItemId()) {
                    case R.id.bottomNav_video:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.bottomNav_home:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.bottomNav_my:
                        viewPager.setCurrentItem(2);
                        return true;
                        default: break;
                }
                return false;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {  //滑动页面后的效果
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
		
		//SharedPreferences sharedPreferences = getSharedPreferences("newsDatas", MODE_PRIVATE);
        //String username = sharedPreferences.getString("username", null);

        View headerView = navigationView.getHeaderView(0);
        /*
        Or use: headerView = navigationView.;inflateHeaderView(R.layout.nav_header_main)
        但这条，会使headerView变成两个（原来那条不会被替换）
        */
        CircleImageView appBarIcon = headerView.findViewById(R.id.imageViewAppbarIcon);
        TextView textViewUsername = headerView.findViewById(R.id.textViewAppbarUsername);
        TextView textViewSignature = headerView.findViewById(R.id.textViewAppbarSignature);
        UserBean user = (UserBean) getIntent().getSerializableExtra("userBean");
        //Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        textViewUsername.setText(user.getUsername());
        if (user.getSignature()!= null)textViewSignature.setText(user.getSignature());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.addNewsItem) {
            Intent intent = new Intent(this, AddNewsOrVideoActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_subscribe) {
            // Handle the subscribe action
            Intent intent = new Intent();
            intent.setClass(this, FollowActivity.class);
            startActivity(intent);
            /*//viewPager.setVisibility(View.INVISIBLE);
            HistoryFragment fragment = new HistoryFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.layoutContent, fragment).commit();*/
        } else if (id == R.id.nav_favorite) {
            Intent intent = new Intent();
            intent.setClass(this, FavoriteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent();
            intent.setClass(this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_information) {
            Intent intent = new Intent();
            intent.setClass(this, InformationActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent("showSetting");
            startActivity(intent);
        } else if (id == R.id.nav_help) {
            Intent intent = new Intent("showHelp");
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d("helloworld", "activityOnActivityResult");
        Fragment currentFragment = viewPagerAdapter.getInstantFragment();
        //Toast.makeText(this, "Activity,"+currentFragment+","+((currentFragment instanceof HomeFragment)?"1":"0"), Toast.LENGTH_SHORT).show();
        if (currentFragment != null && currentFragment instanceof HomeFragment) {
            //Toast.makeText(this, "Activity", Toast.LENGTH_SHORT).show();
            ((HomeFragment) currentFragment).refresh();
        }
    }

    public void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        VideoFragment videoFragment = new VideoFragment();
        HomeFragment homeFragment = new HomeFragment();
        MyFragment myFragment = new MyFragment();
        viewPagerAdapter.addFragment(videoFragment);
        viewPagerAdapter.addFragment(homeFragment);
        viewPagerAdapter.addFragment(myFragment);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
    }
}
