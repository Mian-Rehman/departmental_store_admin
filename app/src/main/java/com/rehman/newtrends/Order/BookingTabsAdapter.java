package com.rehman.newtrends.Order;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BookingTabsAdapter extends FragmentStateAdapter {



    public BookingTabsAdapter(@NonNull FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = new PendingBookingFrag();
                break;

            case 1:
                fragment = new CancelBookingFrag();
                break;

            case 2:
                fragment = new SuccessBookingFrag();
                break;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
