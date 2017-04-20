package combruce_willis.httpsgithub.yamblztranslate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import combruce_willis.httpsgithub.yamblztranslate.Model.TranslateService;
import combruce_willis.httpsgithub.yamblztranslate.Model.TranslationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateFragment extends Fragment implements TextView.OnEditorActionListener {


    public TranslateFragment() {
        // Required empty public constructor
    }
    private TextView textView;
    private EditText editText;

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
            Translate(editText.getText().toString());
        }
        return false;
    }

    private void Translate(String text) {
        Gson gson = new GsonBuilder()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TranslateService.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        TranslateService translateService = retrofit.create(TranslateService.class);

        try {
            Call<TranslationResponse> call = translateService.getTranslation(TranslateService.API_KEY, text, "ru");
            call.enqueue(new Callback<TranslationResponse>() {
                @Override
                public void onResponse(Call<TranslationResponse> call1, Response<TranslationResponse> response) {
                    if (response.isSuccessful()) {
                        TranslationResponse callback = response.body();
                        if (callback.getCode() == 200) textView.setText(callback.getText().get(0));
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Error code " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<TranslationResponse> call1, Throwable t) {
                    Toast.makeText(getActivity(), "Did not work " +  t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            Log.d("Translate", ":" + e.getMessage());
        }
    }

}
