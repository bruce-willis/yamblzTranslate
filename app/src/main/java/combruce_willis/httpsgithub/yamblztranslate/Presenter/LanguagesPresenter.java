package combruce_willis.httpsgithub.yamblztranslate.Presenter;

import combruce_willis.httpsgithub.yamblztranslate.Model.LanguagesList;
import combruce_willis.httpsgithub.yamblztranslate.Model.TranslateService;
import combruce_willis.httpsgithub.yamblztranslate.View.LanguagesMvpView;
import combruce_willis.httpsgithub.yamblztranslate.View.TranslateMvpView;
import combruce_willis.httpsgithub.yamblztranslate.YamblzTranslate;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by yury- on 4/22/2017.
 */

public class LanguagesPresenter implements Presenter<LanguagesMvpView> {

    private LanguagesMvpView languagesMvpView;
    private Subscription subscription;
    private LanguagesList languagesList;

    @Override
    public void attachView(LanguagesMvpView view) {
        this.languagesMvpView = view;
    }

    @Override
    public void detachView() {
        this.languagesMvpView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void loadLanguages(String user_interface)
    {
        if (subscription != null) subscription.unsubscribe();

        YamblzTranslate application = YamblzTranslate.get(languagesMvpView.getContext());
        TranslateService translateService = application.getTranslateService();
        subscription = translateService.getLanguages(user_interface)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<LanguagesList>() {
                    @Override
                    public void onCompleted() {
                        if (languagesList != null) languagesMvpView.showLanguages(languagesList);
                        else languagesMvpView.showMessage("Something go wrong");
                    }

                    @Override
                    public void onError(Throwable e) {
                        languagesMvpView.showMessage("Ooops " + e.getMessage());
                    }

                    @Override
                    public void onNext(LanguagesList languagesList) {
                        LanguagesPresenter.this.languagesList = languagesList;
                    }
                });
    }
}
