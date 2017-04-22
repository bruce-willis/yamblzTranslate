package combruce_willis.httpsgithub.yamblztranslate.View;

import combruce_willis.httpsgithub.yamblztranslate.Model.LanguagesList;

/**
 * Created by yury- on 4/22/2017.
 */

public interface LanguagesMvpView extends MvpView {

    void showLanguages(final LanguagesList languages);

}
