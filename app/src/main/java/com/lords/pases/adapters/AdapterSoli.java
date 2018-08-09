package com.lords.pases.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;


import com.loopwiki.qrsacnner.R;
import com.lords.pases.entidades.Solicitud;

import java.util.ArrayList;

public class   AdapterSoli extends RecyclerView.Adapter<AdapterSoli.ViewHolderCategory> {

    private ArrayList<Solicitud> list;
    private Context mCtx;
    private String xxx;

    public AdapterSoli(ArrayList list,Context c) {
        this.list = list;
        mCtx = c;
    }

    @Override
    public AdapterSoli.ViewHolderCategory onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pase_item, null, false);
        return new ViewHolderCategory(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterSoli.ViewHolderCategory holder, int position) {
        holder.setData(position);
        holder.optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(mCtx,  holder.optionButton);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_pase);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                            case R.id.menu3:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderCategory extends RecyclerView.ViewHolder {
        TextView name;
        TextView optionButton;

        public ViewHolderCategory(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_subtitle_item);
            optionButton = itemView.findViewById(R.id.textViewOptions);
        }

        public void setData(int index) {
            name.setText(list.get(index).getMotivo());
        }
    }
}
