package combruce_willis.httpsgithub.yamblztranslate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateFragment extends Fragment {


    public TranslateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_translate, container, false);
        EditText editText = (EditText)  view.findViewById(R.id.Translation);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        return view;
    }

}
