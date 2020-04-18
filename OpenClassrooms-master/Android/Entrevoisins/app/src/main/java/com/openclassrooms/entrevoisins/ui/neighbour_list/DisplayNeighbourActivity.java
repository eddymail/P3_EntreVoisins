package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayNeighbourActivity extends AppCompatActivity {

    private Neighbour neighbour = null;

    public void switchFavoriteStatus() {
        neighbour.setFavorite(!neighbour.isFavorite());
        mFavoriteButton.setSelected(neighbour.isFavorite());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_neighbour);
        ButterKnife.bind(this);

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFavoriteStatus();
            }
        });

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayNeighbourActivity.this.finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            int id = extras.getInt("id", -1);
            neighbour = DI.getNeighbourApiService().getNeighbourById(id);
        }

        if(neighbour != null){
            mNeighbourName.setText(neighbour.getName());
            Glide.with(mNeighbourAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .into(mNeighbourAvatar);
            mFavoriteButton.setSelected(neighbour.isFavorite());

        }

    }

    @BindView(R.id.iv_avatar)
    public ImageView mNeighbourAvatar;
    @BindView(R.id.ib_return)
    public ImageButton mReturnButton;
    @BindView(R.id.fab_favorite)
    public ImageButton mFavoriteButton;
    @BindView(R.id.tv_name)
    public TextView mNeighbourName;
    @BindView(R.id.cv_contact)
    public CardView mContactInformation;
    @BindView(R.id.cv_about)
    public CardView mAboutText;


}
