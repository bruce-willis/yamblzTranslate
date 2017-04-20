package combruce_willis.httpsgithub.yamblztranslate.View;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import combruce_willis.httpsgithub.yamblztranslate.Model.TranslationResponse;
import combruce_willis.httpsgithub.yamblztranslate.Presenter.TranslatePresenter;
import combruce_willis.httpsgithub.yamblztranslate.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateFragment extends Fragment implements TranslateMvpView, TextView.OnEditorActionListener {

    private TranslatePresenter presenter;

    private TextView textView;
    private EditText editText;

    public TranslateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TranslatePresenter();
        presenter.attachView(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_translate, container, false);
        textView = (TextView) view.findViewById(R.id.editText);
        editText = (EditText) view.findViewById(R.id.Translation);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setOnEditorActionListener(this);
        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE)
        {
            presenter.Translate(editText.getText().toString(), "ru");
        }
        return false;
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

    }

    @Override
    public void showTranslation(TranslationResponse translationResponse) {
        textView.setText(translationResponse.getText().get(0));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
