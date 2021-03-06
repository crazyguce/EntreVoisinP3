package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RefreshNeighbourgsEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class NeighbourFragment extends Fragment {

    private static final String IS_FAVORIT_EXTRA = "IS_FAVORIT_EXTRA";
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private boolean isFavorit;
    private MyNeighbourRecyclerViewAdapter mAdapter;


    /**
     * Create and return a new instance
     *
     * @param isFavorit
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(boolean isFavorit) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FAVORIT_EXTRA, isFavorit);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mNeighbours = new ArrayList<>();
        mApiService = DI.getNeighbourApiService();
        if (getArguments() != null && getArguments().containsKey(IS_FAVORIT_EXTRA)) {
            isFavorit = getArguments().getBoolean(IS_FAVORIT_EXTRA);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours.clear();
        if (isFavorit) {
            mNeighbours.addAll(mApiService.getFavorites());
        } else {
            mNeighbours.addAll(mApiService.getNeighbours());
        }
        if (mAdapter == null) {
            mAdapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        if (isFavorit) {
            mApiService.deleteFavorite(event.neighbour);
        } else {
            mApiService.deleteNeighbour(event.neighbour);
        }
        initList();
    }

    @Subscribe
    public void onRefreshNeighbourgsEvent(RefreshNeighbourgsEvent event) {
        initList();
    }

}
