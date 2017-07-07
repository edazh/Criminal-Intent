package com.edazh.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 12392 on 2017/7/6 0006.
 * Crime列表Fragment
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private int mAdapterPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimeList = crimeLab.getCrimeList();

        if (mCrimeAdapter == null) {
            mCrimeAdapter = new CrimeAdapter(crimeList);
            mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        } else {
            //mCrimeAdapter.notifyDataSetChanged();
            mCrimeAdapter.notifyItemChanged(mAdapterPosition);
            mAdapterPosition = -1;
        }
    }

    /**
     * ViewHolder
     */
    private class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTxtTitle;
        private TextView mTxtDate;
        private ImageView mImgSolved;

        private Crime mCrime;

        public CrimeViewHolder(View itemView) {
            super(itemView);

            mTxtTitle = (TextView) itemView.findViewById(R.id.crime_title);
            mTxtDate = (TextView) itemView.findViewById(R.id.crime_date);
            mImgSolved = (ImageView) itemView.findViewById(R.id.img_solved);

            itemView.setOnClickListener(this);
        }

        void bind(Crime crime) {
            mCrime = crime;
            mTxtTitle.setText(mCrime.getTitle());
            mTxtDate.setText(mCrime.getDateString());
            mImgSolved.setVisibility(mCrime.getSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
            mAdapterPosition = getAdapterPosition();
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeViewHolder> {
        private List<Crime> mCrimeList;

        public CrimeAdapter(List<Crime> crimeList) {
            mCrimeList = crimeList;
        }

        @Override
        public CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeViewHolder holder, int position) {
            Crime crime = mCrimeList.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimeList.size();
        }
    }


}
