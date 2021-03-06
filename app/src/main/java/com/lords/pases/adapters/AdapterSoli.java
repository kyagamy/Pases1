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
import com.lords.pases.fragments.FragmentListSolicitudes;

import java.util.ArrayList;

public class AdapterSoli extends RecyclerView.Adapter<AdapterSoli.ViewHolderCategory> {

    private ArrayList<Solicitud> list;
    private Context mCtx;
    FragmentListSolicitudes fLS;


    public AdapterSoli(ArrayList list, Context c, FragmentListSolicitudes FLS) {
        this.list = list;
        mCtx = c;
        this.fLS = FLS;
    }

    @Override
    public AdapterSoli.ViewHolderCategory onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pase_item, null, false);
        return new ViewHolderCategory(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterSoli.ViewHolderCategory holder, final int position) {
        holder.setData(position);
        holder.optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(mCtx, holder.optionButton);
                //inflating menu from xml resource

                //adding click listener
                if (list.get(position).getEstado().equals("Pendiente")) {
                    popup.inflate(R.menu.menu_pase);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_editar:
                                    fLS.showEditFragment(list.get(position).getDias_solicitado().toString(), list.get(position).getHorapedidaSalida().toString(), list.get(position).getHoraPedidaRegreso().toString(), list.get(position).getId(), list.get(position).getMotivo());
                                    break;
                                case R.id.menu_eliminar:
                                    //handle menu2 click
                                    fLS.delete(list.get(position).getId());
                                    break;
                                case R.id.menu_ver_detalles:
                                    fLS.showPopup(list.get(position).fechaCreada,
                                            list.get(position).getDias_solicitado().toString(),
                                            list.get(position).getHorapedidaSalida().toString() + "-" + list.get(position).getHoraPedidaRegreso().toString(),
                                            list.get(position).getSalida() + "-" + list.get(position).getRegreso().toString(), list.get(position).getMotivo(), list.get(position).getRespuesta(), list.get(position).getEstado()
                                            , list.get(position).getId());
                                    break;
                            }
                            return false;
                        }
                    });
                } else {
                    popup.inflate(R.menu.menu_pase_inactivo);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            fLS.showPopup(list.get(position).fechaCreada,
                                    list.get(position).getDias_solicitado().toString(),
                                    list.get(position).getHorapedidaSalida().toString() + "-" + list.get(position).getHoraPedidaRegreso().toString(),
                                    list.get(position).getSalida() + "-" + list.get(position).getRegreso().toString(), list.get(position).getMotivo(), list.get(position).getRespuesta(), list.get(position).getEstado()
                                    , list.get(position).getId());
                            return false;
                        }
                    });
                }
                popup.show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderCategory extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;

        TextView optionButton;

        public ViewHolderCategory(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title_item);
            subtitle = itemView.findViewById(R.id.tv_subtitle_item);
            optionButton = itemView.findViewById(R.id.textViewOptions);
        }

        public void setData(int index) {
            title.setText("Solicitud de: " + list.get(index).getFechaCreada().substring(0, 11));
            subtitle.setText("Estatus: " + list.get(index).getEstado());

        }
    }


}
