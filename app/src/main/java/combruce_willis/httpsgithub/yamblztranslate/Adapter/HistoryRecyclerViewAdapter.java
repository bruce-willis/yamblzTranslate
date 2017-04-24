package combruce_willis.httpsgithub.yamblztranslate.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import combruce_willis.httpsgithub.yamblztranslate.Model.HistoryDatabase;
import combruce_willis.httpsgithub.yamblztranslate.R;
import combruce_willis.httpsgithub.yamblztranslate.View.Fragment.HistoryFragment;
import io.realm.Realm;

/**
 * Created by yury- on 4/22/2017.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

    private final List<HistoryDatabase> history;
    private Realm realm;
    private View view;
    private final HistoryFragment.OnHistoryFragmentInteractionListener listener;

    public HistoryRecyclerViewAdapter(List<HistoryDatabase> history, HistoryFragment.OnHistoryFragmentInteractionListener listener) {
        this.history = history;
        this.listener = listener;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_history_item, parent, false);
        return new HistoryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final HistoryDatabase item = history.get(position);
        holder.item = item;
        holder.languageSourceTextView.setText(item.getSourceString());
        holder.languageTargetTextView.setText(item.getTranslationString());
        holder.directionTextView.setText(item.getLanguageSourceCode().toUpperCase() + " - " + item.getLanguageTargetCode().toUpperCase());
        if (item.isFavorite())
            holder.favoriteImageView.setImageResource(R.drawable.ic_bookmark_black_24dp);
        else holder.favoriteImageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        holder.favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryDatabase updatingItem = realm.where(HistoryDatabase.class)
                        .equalTo("sourceString", item.getSourceString())
                        .equalTo("languageSourceCode", item.getLanguageSourceCode())
                        .equalTo("languageTargetCode", item.getLanguageTargetCode())
                        .findFirst();
                realm.beginTransaction();
                updatingItem.setFavorite(!updatingItem.isFavorite());
                realm.commitTransaction();
                if (updatingItem.isFavorite()) {
                    holder.favoriteImageView.setImageResource(R.drawable.ic_bookmark_black_24dp);
                } else {
                    holder.favoriteImageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                }
            }
        });
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
        public ImageView favoriteImageView;

        public HistoryDatabase item;

        public ViewHolder(View view) {
            super(view);
            contentLayout = view;
            languageSourceTextView = (TextView) view.findViewById(R.id.history_language_source);
            languageTargetTextView = (TextView) view.findViewById(R.id.history_language_target);
            directionTextView = (TextView) view.findViewById(R.id.history_direction);
            favoriteImageView = (ImageView) view.findViewById(R.id.history_favorite);
        }
    }
}
