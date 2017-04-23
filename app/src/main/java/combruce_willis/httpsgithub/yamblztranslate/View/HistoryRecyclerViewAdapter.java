package combruce_willis.httpsgithub.yamblztranslate.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import combruce_willis.httpsgithub.yamblztranslate.Model.HistoryDatabase;
import combruce_willis.httpsgithub.yamblztranslate.R;

/**
 * Created by yury- on 4/22/2017.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

    private final List<HistoryDatabase> history;
    private View view;
    private final HistoryFragment.OnHistoryFragmentInteractionListener listener;

    public HistoryRecyclerViewAdapter(List<HistoryDatabase> history, HistoryFragment.OnHistoryFragmentInteractionListener listener) {
        this.history = history;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_history_item, parent, false);
        return new HistoryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HistoryDatabase item = history.get(position);
        holder.item = item;
        holder.languageSourceTextView.setText(item.getSourceString());
        holder.languageTargetTextView.setText(item.getTranslationString());
        holder.directionTextView.setText(item.getLanguageSourceCode().toUpperCase() + " - " + item.getLanguageTargetCode().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View contentLayout;
        public TextView languageSourceTextView;
        public TextView languageTargetTextView;
        public TextView directionTextView;

        public HistoryDatabase item;

        public ViewHolder(View view) {
            super(view);
            contentLayout = view;
            languageSourceTextView = (TextView) view.findViewById(R.id.history_language_source);
            languageTargetTextView = (TextView) view.findViewById(R.id.history_language_target);
            directionTextView = (TextView) view.findViewById(R.id.history_direction);
        }
    }
}
