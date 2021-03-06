package com.p2s.vending.activities;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.p2s.vending.R;
import com.p2s.vending.classes.VendingProduct;
import com.p2s.vending.classes.Vendor;
import com.p2s.vending.fragments.BalanceFragment;
import com.p2s.vending.fragments.ProductsFragment;
import com.p2s.vending.fragments.TransactionFinishedFragment;

public class VendorMainActivity extends Activity implements ProductsFragment.OnFragmentInteractionListener, BalanceFragment.OnFragmentInteractionListener {

    private static final String TAG = "VendorMainActivity";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    TransactionFinishedFragment transactionFinishedFragment;


    private ViewPager mViewPager;
    // private static Vendor vendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        createSampleData();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

//        mViewPager.setCurrentItem(1,true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vendor_main, menu);
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
        }

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            backAction();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setNewBalance(int _newBalance) {
        Vendor.setBalance(_newBalance);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return BalanceFragment.newInstance();
                case 1:
                    return ProductsFragment.newInstance(Vendor.products);
                case 2:
                    transactionFinishedFragment = TransactionFinishedFragment.newInstance();
                    return transactionFinishedFragment;
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {

                case 0:
                    getActionBar().setTitle("Insert Cash");

                    return "Insert Cash";
                case 1:
                    getActionBar().setTitle("Choose A Product");

                    return "Choose A Product";
                case 2:
                    getActionBar().setTitle("Thank You!");

                    return "Thank You!";
            }
            return null;
        }
    }


    public void goToProducts(View v) {
        if (Vendor.getBalance() <= 0) {
            Toast.makeText(getApplicationContext(), "Zero Balance", Toast.LENGTH_LONG).show();
            return;
        }

        ProductsFragment.setBalance(Vendor.getBalance());
        mViewPager.setCurrentItem(1, true);
    }

    public void shopMore(View v) {
        Vendor.setBalance(0);
        BalanceFragment.setBalance(Vendor.getBalance());
        ProductsFragment.setBalance(Vendor.getBalance());
        mViewPager.setCurrentItem(0, true);
    }


    @Override
    public void selectProduct(VendingProduct product) {
        Toast.makeText(getApplicationContext(), "vending: " + product.name(), Toast.LENGTH_LONG).show();


        TransactionFinishedFragment.setBalance(Vendor.getBalance());
        transactionFinishedFragment.setProduct(product);
        Vendor.sellProduct(product);
        mViewPager.setCurrentItem(2, true);

    }

    private void createSampleData() {
        Vendor.addProduct("Coca Cola", 85, 2);
        Vendor.addProduct("Lays Chips", 105, 0);
        Vendor.addProduct("Twix", 110, 16);
    }

    private void backAction() {

        if (mViewPager.getCurrentItem() == 0) {

            finish();

        } else {

            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        }
    }

    @Override
    public void onBackPressed() {
        backAction();
    }
}
