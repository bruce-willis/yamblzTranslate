package combruce_willis.httpsgithub.yamblztranslate.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import combruce_willis.httpsgithub.yamblztranslate.R;

public class LanguageRecyclerViewAdapter extends RecyclerView.Adapter<LanguageRecyclerViewAdapter.ViewHolder> {

    private List<String> languages;
    private Callback callback;

    public LanguageRecyclerViewAdapter() {
        this.languages = Collections.emptyList();
    }

    public LanguageRecyclerViewAdapter(List<String> languages) {
        this.languages = languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_language, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(viewHolder.language);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String language = languages.get(position);
        Context context = holder.languageTextView.getContext();
        holder.language = language;
        holder.languageTextView.setText(language);
    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View contentLayout;
        public TextView languageTextView;
        public String language;

        public ViewHolder(View view) {
            super(view);
            contentLayout = view;
            languageTextView = (TextView) view.findViewById(R.id.text_language);
        }
    }

    public interface Callback {
        void onItemClick(String language);
    }
}
