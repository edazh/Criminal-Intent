package com.edazh.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by 12392 on 2017/7/6 0006.
 * Crime列表控制器
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
