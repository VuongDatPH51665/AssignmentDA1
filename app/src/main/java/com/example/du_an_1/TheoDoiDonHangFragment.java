package com.example.du_an_1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TheoDoiDonHangFragment extends Fragment {

    public static TheoDoiDonHangFragment newInstance() {
        return new TheoDoiDonHangFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_theo_doi_don_hang, container, false);
    }
}
