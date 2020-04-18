package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {
    //Pour data
    private final List<Neighbour> mNeighbours;
    //Constructor
    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }
    //Appel de onCreateViewHolder
    //Crée un viewHolder à partir du layout représentant chaque ligne de la RecyclerView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);

    }
    //Appel de onBindViewHolder pour chacune des lignes visibles affichées du RecyclerView
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);


        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent displayNeighbourActivityIntent = new Intent(view.getContext(),DisplayNeighbourActivity.class);
                //on récupère l'id du neighbour cliqué
                displayNeighbourActivityIntent.putExtra("id", neighbour.getId());

                view.getContext().startActivity(displayNeighbourActivityIntent);
            }
        });
    }
    //Retourne la taille de la liste d'objet et indique à l'adapter le nbr de lignes que peut contenir la RecyclerView
    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }



    //Création du ViewHolder
    //Modelise en objet java la vue xml
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
