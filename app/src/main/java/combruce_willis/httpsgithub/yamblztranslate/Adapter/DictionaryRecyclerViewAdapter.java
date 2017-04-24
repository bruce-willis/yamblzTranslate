package combruce_willis.httpsgithub.yamblztranslate.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.Collections;
import java.util.List;

import combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary.Example;
import combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary.Meaning;
import combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary.Synonym;
import combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary.TranslationDictionary;
import combruce_willis.httpsgithub.yamblztranslate.R;

/**
 * Created by yury- on 4/24/2017.
 */

public class DictionaryRecyclerViewAdapter extends RecyclerView.Adapter<DictionaryRecyclerViewAdapter.ViewHolder> {

    private List<TranslationDictionary> translations;

    FlexboxLayout.LayoutParams params;

    private Activity mainActivity;

    public DictionaryRecyclerViewAdapter() {
        this.translations = Collections.emptyList();
    }

    public void setDefinitions(List<TranslationDictionary> translations) {
        this.translations = translations;
    }

    public void setMainActivity(Activity mainActivity) {
        this.mainActivity = mainActivity;
        params = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_dictionary_item, parent, false);
        return new DictionaryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TranslationDictionary item = translations.get(position);

        holder.item = item;

        holder.numberTextView.setText((position + 1) + ".");

        holder.synonymsFlexboxLayout.removeAllViews();
        holder.meaningsFlexboxLayout.removeAllViews();
        holder.samplesFlexboxLayout.removeAllViews();

        TextView textView = new TextView(mainActivity);
        setupSynonymTextView(textView);
        textView.setText(item.getText());

        holder.synonymsFlexboxLayout.addView(textView);

        if (item.getSynonym() != null &&!item.getSynonym().isEmpty()) {
            for (Synonym synonym : item.getSynonym()) {
                textView = new TextView(mainActivity);
                setupSynonymTextView(textView);
                textView.setText(", " + synonym.getText());
                holder.synonymsFlexboxLayout.addView(textView);
            }
        }

        if (item.getMeaning() != null &&!item.getMeaning().isEmpty()) {
            for (Meaning meaning : item.getMeaning()) {
                textView = new TextView(mainActivity);
                setupMeaningTextView(textView);
                textView.setText(meaning.getText());
                holder.meaningsFlexboxLayout.addView(textView);
            }
        }

        if (item.getExample() != null &&!item.getExample().isEmpty()) {
            for (Example example : item.getExample()) {
                textView = new TextView(mainActivity);
                setupSampleTextView(textView);
                textView.setText(example.getText() + " - " + example.getTr().get(0).getText());
                holder.samplesFlexboxLayout.addView(textView);
            }
        }
    }

    private void setupSynonymTextView(TextView textView) {
        textView.setLayoutParams(params);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setTextColor(Color.BLACK);
    }

    private void setupMeaningTextView(TextView textView) {
        textView.setLayoutParams(params);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setTextColor(Color.GRAY);
    }

    private void setupSampleTextView(TextView textView) {
        textView.setLayoutParams(params);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(Color.LTGRAY);
    }


    @Override
    public int getItemCount() {
        if (translations == null) return 0;
        return translations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View contentLayout;

        public TextView numberTextView;

        public FlexboxLayout synonymsFlexboxLayout;
        public FlexboxLayout meaningsFlexboxLayout;
        public FlexboxLayout samplesFlexboxLayout;


        public TranslationDictionary item;

        public ViewHolder(View view) {
            super(view);
            contentLayout = view;
            numberTextView = (TextView) view.findViewById(R.id.dictionary_item_number);
            synonymsFlexboxLayout = (FlexboxLayout) view.findViewById(R.id.dictionary_item_result);
            meaningsFlexboxLayout = (FlexboxLayout) view.findViewById(R.id.dictionary_item_meaning);
            samplesFlexboxLayout = (FlexboxLayout) view.findViewById(R.id.dictionary_item_samples);
        }
    }
}
