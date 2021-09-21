package com.example.iothomeremote.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.iothomeremote.R;
import com.example.iothomeremote.adapters.FurnitureListAdapter;
import com.example.iothomeremote.databinding.FragmentHomeBinding;
import com.example.iothomeremote.models.Furniture;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<Furniture> furnitureList = getListData();
        ListView listView = binding.listView;
        listView.setAdapter(new FurnitureListAdapter(root.getContext(), furnitureList));

        return root;
    }

    private List<Furniture> getListData(){
        List<Furniture> list = new ArrayList<Furniture>();

        list.add(new Furniture("Light", false));
        list.add(new Furniture("Television", true));

        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}