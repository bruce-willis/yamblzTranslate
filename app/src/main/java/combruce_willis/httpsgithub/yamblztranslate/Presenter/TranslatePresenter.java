package combruce_willis.httpsgithub.yamblztranslate.Presenter;

import combruce_willis.httpsgithub.yamblztranslate.Model.LanguagesList;
import combruce_willis.httpsgithub.yamblztranslate.Model.TranslateService;
import combruce_willis.httpsgithub.yamblztranslate.Model.TranslationResponse;
import combruce_willis.httpsgithub.yamblztranslate.View.TranslateMvpView;
import combruce_willis.httpsgithub.yamblztranslate.YamblzTranslate;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by yury- on 4/20/2017.
 */

public class TranslatePresenter implements Presenter<TranslateMvpView> {

    private TranslateMvpView translateMvpView;
    private Subscription subscription;
    private TranslationResponse translationResponse;
    private LanguagesList languagesList;

    @Override
    public void attachView(TranslateMvpView view) {
        this.translateMvpView = view;
    }

    @Override
    public void detachView() {
        this.translateMvpView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void Translate(String textEntered, String language) {
        String text = textEntered.trim();
        if (text.isEmpty()) return;

        translateMvpView.showProgressIndicator();

        YamblzTranslate application = YamblzTranslate.get(translateMvpView.getContext());
        TranslateService translateService = application.getTranslateService();
        subscription = translateService.getTranslation(text, language)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<TranslationResponse>() {
                    @Override
                    public void onCompleted() {
                        if (translationResponse != null) translateMvpView.showTranslation(translationResponse);
                        else translateMvpView.showMessage("Something go wrong");
                    }

                    @Override
                    public void onError(Throwable e) {
                        translateMvpView.showMessage("Ooops " + e.getMessage());
                    }

                    @Override
                    public void onNext(TranslationResponse translationResponse) {
                        TranslatePresenter.this.translationResponse = translationResponse;
                    }
                });

    }

    public void Languages(String user_interface)
    {
        translateMvpView.showProgressIndicator();

        YamblzTranslate application = YamblzTranslate.get(translateMvpView.getContext());
        TranslateService translateService = application.getTranslateService();
        subscription = translateService.getLanguages(user_interface)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<LanguagesList>() {
                    @Override
                    public void onCompleted() {
                        if (languagesList != null) translateMvpView.showLanguages(languagesList);
                        else translateMvpView.showMessage("Something go wrong");
                    }

                    @Override
                    public void onError(Throwable e) {
                        translateMvpView.showMessage("Ooops " + e.getMessage());
                    }

                    @Override
                    public void onNext(LanguagesList languagesList) {
                        TranslatePresenter.this.languagesList = languagesList;
                    }
                });
    }
}
