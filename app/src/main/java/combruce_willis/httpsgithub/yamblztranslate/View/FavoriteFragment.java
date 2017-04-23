package combruce_willis.httpsgithub.yamblztranslate.View;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import combruce_willis.httpsgithub.yamblztranslate.Model.HistoryDatabase;
import combruce_willis.httpsgithub.yamblztranslate.R;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private RecyclerView favoriteRecyclerView;

    private HistoryFragment.OnHistoryFragmentInteractionListener listener;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_items, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_history_list, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.history_toolbar);
        toolbar.setTitle(R.string.title_favorites);
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        favoriteRecyclerView = (RecyclerView) view.findViewById(R.id.history_recycler_view);
        setupRecyclerView(favoriteRecyclerView);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HistoryFragment.OnHistoryFragmentInteractionListener) {
            listener = (HistoryFragment.OnHistoryFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHistoryFragmentInteractionListener");
        }

    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        Realm realm = Realm.getDefaultInstance();
        HistoryRecyclerViewAdapter adapter = new HistoryRecyclerViewAdapter(
                realm.where(HistoryDatabase.class)
                        .equalTo("favorite", true)
                        .findAllSorted("date", Sort.DESCENDING),
                listener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation()));
    }

}
