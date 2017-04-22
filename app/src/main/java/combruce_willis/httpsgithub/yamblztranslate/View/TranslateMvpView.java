package combruce_willis.httpsgithub.yamblztranslate.View;

import combruce_willis.httpsgithub.yamblztranslate.Model.LanguagesList;
import combruce_willis.httpsgithub.yamblztranslate.Model.TranslationResponse;

/**
 * Created by yury- on 4/20/2017.
 */

public interface TranslateMvpView extends MvpView {

    void showTranslation(TranslationResponse translationResponse);

    void showProgressIndicator();

}
