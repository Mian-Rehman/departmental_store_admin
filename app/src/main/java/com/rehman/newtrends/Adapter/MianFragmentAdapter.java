package com.rehman.newtrends.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.rehman.newtrends.Main.OrderFrag;
import com.rehman.newtrends.Main.ProfileFrag;

public class MianFragmentAdapter extends FragmentStateAdapter
{
    String id = "";



    public MianFragmentAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MianFragmentAdapter(FragmentActivity fragmentActivity,String id) {
        super(fragmentActivity);
        this.id = id;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment = null;

        switch (position)
        {
            case 0:
                fragment = new ProfileFrag(id);
                break;
            case 1:
                fragment = new OrderFrag(id);
                break;
        }


        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
