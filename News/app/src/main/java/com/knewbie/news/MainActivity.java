package com.knewbie.news;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import com.knewbie.news.activity.MyInfoActivity;
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
    private UserBean user;
    private CircleImageView appBarIcon;
    private TextView textViewUsername, textViewSignature;
    private final int REQUESTCODE_ADDNEWS = 1;
    private final int REQUESTCODE_SELFINFO = 2;

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
        appBarIcon = headerView.findViewById(R.id.imageViewAppbarIcon);
        textViewUsername = headerView.findViewById(R.id.textViewAppbarUsername);
        textViewSignature = headerView.findViewById(R.id.textViewAppbarSignature);
        user = (UserBean) getIntent().getSerializableExtra("userBean");
        //Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        textViewUsername.setText(user.getUsername());
        if (user.getSignature()!= null)textViewSignature.setText(user.getSignature());
        appBarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyInfoActivity.class);
                intent.putExtra("userBean", user);
                startActivityForResult(intent, REQUESTCODE_SELFINFO);
            }
        });
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
        if (id == R.id.toolbarItem_action_settings) {
            SearchView searchView = findViewById(R.id.toolbarItem_search);
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_LONG).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            return true;
        }else if (id == R.id.toolbarItem_action_settings) {
            return true;
        }
        else if (id == R.id.toolbarItem_addNewsItem) {
            Intent intent = new Intent(this, AddNewsOrVideoActivity.class);
            startActivityForResult(intent, REQUESTCODE_ADDNEWS);
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
        switch (requestCode) {
            case REQUESTCODE_ADDNEWS:
                if (resultCode == 1) {
                    Fragment currentFragment = viewPagerAdapter.getInstantFragment();
                    //Toast.makeText(this, "Activity,"+currentFragment+","+((currentFragment instanceof HomeFragment)?"1":"0"), Toast.LENGTH_SHORT).show();
                    if (currentFragment != null && currentFragment instanceof HomeFragment) {
                        //Toast.makeText(this, "Activity", Toast.LENGTH_SHORT).show();
                        ((HomeFragment) currentFragment).refresh();
                    }
                }
                break;
            case REQUESTCODE_SELFINFO:
                if (resultCode == 1) {
                    Intent intent = getIntent();
                    user = (UserBean) intent.getSerializableExtra("userBean");
                    textViewUsername.setText(user.getUsername());
                    if (user.getSignature()!= null)textViewSignature.setText(user.getSignature());
                }
                break;
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
