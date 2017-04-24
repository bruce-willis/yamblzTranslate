package combruce_willis.httpsgithub.yamblztranslate.View.MvpView;

import combruce_willis.httpsgithub.yamblztranslate.Model.TranslationResponse;
import combruce_willis.httpsgithub.yamblztranslate.View.MvpView.MvpView;

/**
 * Created by yury- on 4/20/2017.
 */

public interface TranslateMvpView extends MvpView {

    void showTranslation(TranslationResponse translationResponse);

    void showProgressIndicator();

}
