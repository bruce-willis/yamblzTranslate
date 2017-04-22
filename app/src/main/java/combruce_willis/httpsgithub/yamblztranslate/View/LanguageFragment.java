package combruce_willis.httpsgithub.yamblztranslate.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import combruce_willis.httpsgithub.yamblztranslate.Model.LanguagesList;
import combruce_willis.httpsgithub.yamblztranslate.Presenter.LanguagesPresenter;
import combruce_willis.httpsgithub.yamblztranslate.R;

public class LanguageFragment extends Fragment implements LanguagesMvpView {


    private LanguagesPresenter presenter;

    private RecyclerView languagesRecyclerView;

    private static final String ARG_LANGUAGE = "languageType";
    /**
     * 0 - source languageType
     * 1 - target languageType
     */
    private int languageType = 0;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LanguageFragment() {
    }

    public static LanguageFragment newInstance(int language) {
        LanguageFragment fragment = new LanguageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LANGUAGE, language);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LanguagesPresenter();
        presenter.attachView(this);
        if (getArguments() != null) {
            languageType = getArguments().getInt(ARG_LANGUAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language_list, container, false);

        presenter.loadLanguages("en");

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.history_toolbar);
        if (languageType == 0) toolbar.setTitle(R.string.source_language);
        else toolbar.setTitle(R.string.target_language);
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        languagesRecyclerView = (RecyclerView) view.findViewById(R.id.languages_recycler_view);
        setupRecyclerView(languagesRecyclerView);

        return view;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        // Set the adapter
        LanguageRecyclerViewAdapter adapter = new LanguageRecyclerViewAdapter();
        adapter.setCallback(new LanguageRecyclerViewAdapter.Callback() {
            @Override
            public void onItemClick(String language, String languageCode, int languageType) {
                SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
                if (languageType == 0) {
                    editor.putString(getString(R.string.saved_source_language_code),languageCode);
                    editor.putString(getString(R.string.saved_source_language_full), language);
                }
                else {
                    editor.putString(getString(R.string.saved_target_language_code), languageCode);
                    editor.putString(getString(R.string.saved_target_language_full), language);
                }
                editor.apply();
                getActivity().onBackPressed();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    //LanguagesMvpView interface methods implementation
    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLanguages(LanguagesList languages) {
        LanguageRecyclerViewAdapter adapter = (LanguageRecyclerViewAdapter) languagesRecyclerView.getAdapter();
        adapter.setLanguages(new ArrayList<>(languages.getLanguages().values()));
        adapter.setLanguageType(this.languageType);
        adapter.setLanguagesWithCode(languages.getLanguages()); //TODO simplify this!!!
        adapter.notifyDataSetChanged();
        languagesRecyclerView.requestFocus();
        languagesRecyclerView.setVisibility(View.VISIBLE);
    }
}
