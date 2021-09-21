package com.example.iothomeremote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.iothomeremote.R;
import com.example.iothomeremote.models.Furniture;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.List;

public class FurnitureListAdapter extends BaseAdapter {
    private List<Furniture> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public FurnitureListAdapter(Context aContext,  List<Furniture> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) view.findViewById(R.id.furnitureName);
            holder.toggleGroup = (MaterialButtonToggleGroup) view.findViewById(R.id.furnitureState);
            holder.toggleButton = (Button) view.findViewById(R.id.furnitureStateButton);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Furniture furniture = this.listData.get(i);
        holder.nameView.setText(furniture.getName());
        holder.toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                Button button = group.findViewById(checkedId);
                if (isChecked)
                    button.setText(R.string.on);
                else
                    button.setText(R.string.off);
            }
        });
        if (furniture.getIsEnabled())
            holder.toggleGroup.check(holder.toggleButton.getId());

        return view;
    }

    static class ViewHolder {
        Button toggleButton;
        TextView nameView;
        MaterialButtonToggleGroup toggleGroup;
    }
}
