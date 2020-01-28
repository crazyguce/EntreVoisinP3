package com.openclassrooms.entrevoisins.ui.detailsNeighbour;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import static com.openclassrooms.entrevoisins.model.Neighbour.NEIGHBOUR_EXTRA;


public class NeighbourDetailActivity extends AppCompatActivity {

    private TextView mUsername;
    private ImageView mAvatar;
    private ImageView mStar;
    private TextView mAdresse;
    private TextView mTel;
    private TextView mLink;
    private TextView mDescr;
    private TextView mBigUsername;


    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        mApiService = DI.getNeighbourApiService();
        mNeighbour = getIntent().getParcelableExtra(NEIGHBOUR_EXTRA);

        findView();
        initView();
        initToolBar();

    }

    private void initView() {
        mBigUsername.setText(mNeighbour.getName());
        mUsername.setText(mNeighbour.getName());
        Glide.with(this)
                .asBitmap()
                .load(mNeighbour.getAvatarUrl())
                .centerCrop()
                .into(mAvatar);
        mAdresse.setText(mNeighbour.getAdress());
        mTel.setText(mNeighbour.getTel());
        mLink.setText(mNeighbour.getUrl());
        mDescr.setText(mNeighbour.getDescription());
        initFavoriteView(isFavorite(mNeighbour));
    }

    private void initToolBar() {
        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true); // Enable the Up button
        ab.setDisplayShowTitleEnabled(false); // Disable the title
    }

    private void findView() {
        mUsername = findViewById(R.id.usernameText);
        mAvatar = findViewById(R.id.avatarPic);
        mStar = findViewById(R.id.favButton);
        mAdresse = findViewById(R.id.adressText);
        mTel = findViewById(R.id.telText);
        mLink = findViewById(R.id.urlText);
        mDescr = findViewById(R.id.descText);
        mBigUsername = findViewById(R.id.bigUsernameText);

    }


    /**
     * Ajout du voisin dans la liste des favoris
     * @param view
     */
    public void editFavorite(View view) {
        boolean isFavorite = isFavorite(mNeighbour);
        if(isFavorite){
            mApiService.deleteFavorite(mNeighbour);
        }
        else {
            mApiService.getFavorites().add(mNeighbour);
        }
        initFavoriteView(!isFavorite);


    }

   private boolean isFavorite(Neighbour neighbour){

        for (Neighbour favorite : mApiService.getFavorites()) {
            if (neighbour.getId().equals(favorite.getId())) {
                return true;
            }
        }
        return false;

    }
    private void initFavoriteView(boolean IsFAvorite){
        if (IsFAvorite){
            mStar.setImageResource(R.drawable.ic_star_yellow);

        }
        else {
            mStar.setImageResource(R.drawable.ic_star_white_24dp);
        }
    }





}
