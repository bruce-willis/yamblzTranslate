package combruce_willis.httpsgithub.yamblztranslate.View.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import combruce_willis.httpsgithub.yamblztranslate.Adapter.DictionaryRecyclerViewAdapter;
import combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary.Definition;
import combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary.TranslationDictionary;
import combruce_willis.httpsgithub.yamblztranslate.Model.DictionaryResponse;
import combruce_willis.httpsgithub.yamblztranslate.Model.HistoryDatabase;
import combruce_willis.httpsgithub.yamblztranslate.Model.TranslationResponse;
import combruce_willis.httpsgithub.yamblztranslate.Presenter.TranslatePresenter;
import combruce_willis.httpsgithub.yamblztranslate.R;
import combruce_willis.httpsgithub.yamblztranslate.View.MvpView.TranslateMvpView;
import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateFragment extends Fragment implements TranslateMvpView {

    private TranslatePresenter presenter;

    private Realm realm;

    private TextView translationTextView;
    private EditText editText;
    private TextView languageSourceTextView;
    private String languageSourceFull;
    private String languageSourceCode;
    private TextView languageTargetTextView;
    private String languageTargetFull;
    private String languageTargetCode;
    private SharedPreferences sharedPreferences;
    private ImageButton swapLanguagesButton;
    private ProgressBar progressBar;

    private TextView dictionaryTranslationTextView;
    private TextView dictionaryTranscriptionTextView;
    private RecyclerView dictionaryRecyclerView;

    FragmentNavigation fragmentNavigation;

    public TranslateFragment() {
        // Required empty public constructor
    }

    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNavigation) {
            fragmentNavigation = (FragmentNavigation) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new TranslatePresenter();
        presenter.attachView(this);

        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.language_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        languageSourceTextView = (TextView) view.findViewById(R.id.language_source);
        languageSourceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentNavigation != null)
                    fragmentNavigation.pushFragment(LanguageFragment.newInstance(0));
            }
        });

        languageTargetTextView = (TextView) view.findViewById(R.id.language_target);
        languageTargetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentNavigation != null)
                    fragmentNavigation.pushFragment(LanguageFragment.newInstance(1));
            }
        });

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        updateTitle();

        swapLanguagesButton = (ImageButton) view.findViewById(R.id.swap_languages);
        swapLanguagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(getString(R.string.saved_source_language_full), languageTargetFull);
                editor.putString(getString(R.string.saved_target_language_full), languageSourceFull);

                editor.putString(getString(R.string.saved_source_language_code), languageTargetCode);
                editor.putString(getString(R.string.saved_target_language_code), languageSourceCode);

                editor.apply();

                updateTitle();
            }
        });

        translationTextView = (TextView) view.findViewById(R.id.translation);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        editText = (EditText) view.findViewById(R.id.edit_text);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    presenter.Translate(editText.getText().toString(), languageSourceCode + "-" + languageTargetCode);
                }
                return false;
            }
        });

        dictionaryRecyclerView = (RecyclerView) view.findViewById(R.id.dictionary_recyclerView);
        setupRecyclerView(dictionaryRecyclerView);

        dictionaryTranslationTextView = (TextView) view.findViewById(R.id.dictionary_translation_result);
        dictionaryTranscriptionTextView = (TextView) view.findViewById(R.id.dictionary_transcription);

        return view;
    }

    private void updateTitle() {
        languageSourceFull = sharedPreferences.getString(getString(R.string.saved_source_language_full), "English");
        languageSourceCode = sharedPreferences.getString(getString(R.string.saved_source_language_code), "en");
        languageTargetFull = sharedPreferences.getString(getString(R.string.saved_target_language_full), "Russian");
        languageTargetCode = sharedPreferences.getString(getString(R.string.saved_target_language_code), "ru");
        languageSourceTextView.setText(languageSourceFull);
        languageTargetTextView.setText(languageTargetFull);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        DictionaryRecyclerViewAdapter adapter = new DictionaryRecyclerViewAdapter();
        adapter.setMainActivity(getActivity());
        recyclerView.setAdapter(adapter);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    }

    //TranslateMvpView interface methods implementation
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
    public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
        translationTextView.setVisibility(View.GONE);
    }

    @Override
    public void showTranslation(TranslationResponse translationResponse) {
        progressBar.setVisibility(View.GONE);
        translationTextView.setText(translationResponse.getText().get(0));
        translationTextView.setVisibility(View.VISIBLE);
        WriteToDatabase();
        presenter.Meaning(editText.getText().toString(), languageSourceCode + "-" + languageTargetCode);
    }

    @Override
    public void showDictionaryMeaning(DictionaryResponse dictionaryResponse) {
        dictionaryTranslationTextView.setText(dictionaryResponse.getDefinitions().get(0).getText());
        dictionaryTranslationTextView.setVisibility(View.VISIBLE);
        dictionaryTranscriptionTextView.setText("[" + dictionaryResponse.getDefinitions().get(0).getTranscription() + "]");
        dictionaryTranscriptionTextView.setVisibility(View.VISIBLE);
        DictionaryRecyclerViewAdapter adapter = (DictionaryRecyclerViewAdapter) dictionaryRecyclerView.getAdapter();
        List<TranslationDictionary> list = new ArrayList<>();//(dictionaryResponse.getDefinitions().get(0).getTranslationDictionary());
        for (Definition definition : dictionaryResponse.getDefinitions()) {
            list.addAll(definition.getTranslationDictionary());
        }
        adapter.setDefinitions(list);
        adapter.notifyDataSetChanged();
        dictionaryRecyclerView.requestFocus();
        dictionaryRecyclerView.setVisibility(View.VISIBLE);
    }

    public void WriteToDatabase() {
        final String sourceString = editText.getText().toString().trim();
        HistoryDatabase translation = realm.where(HistoryDatabase.class)
                .equalTo("sourceString", sourceString)
                .equalTo("languageSourceCode", languageSourceCode)
                .equalTo("languageTargetCode", languageTargetCode)
                .findFirst();
        realm.beginTransaction();
        if (translation == null) {
            HistoryDatabase history = realm.createObject(HistoryDatabase.class);
            history.setSourceString(sourceString);
            history.setTranslationString(translationTextView.getText().toString());
            history.setLanguageSourceCode(languageSourceCode);
            history.setLanguageTargetCode(languageTargetCode);
            history.setDate(new Date());
        } else translation.setDate(new Date());
        realm.commitTransaction();
    }

    @Override
    public void showMessage(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);
    }
}
