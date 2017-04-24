package combruce_willis.httpsgithub.yamblztranslate.Presenter;

import combruce_willis.httpsgithub.yamblztranslate.Model.DictionaryResponse;
import combruce_willis.httpsgithub.yamblztranslate.Model.Service.DictionaryService;
import combruce_willis.httpsgithub.yamblztranslate.Model.Service.TranslateService;
import combruce_willis.httpsgithub.yamblztranslate.Model.TranslationResponse;
import combruce_willis.httpsgithub.yamblztranslate.View.MvpView.TranslateMvpView;
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
    private DictionaryResponse dictionaryResponse;

    @Override
    public void attachView(TranslateMvpView view) {
        this.translateMvpView = view;
    }

    @Override
    public void detachView() {
        this.translateMvpView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void Translate(String textEntered, String direction) {
        String text = textEntered.trim();
        if (text.isEmpty()) return;

        translateMvpView.showProgressIndicator();

        if (subscription != null) subscription.unsubscribe();

        YamblzTranslate application = YamblzTranslate.get(translateMvpView.getContext());
        TranslateService translateService = application.getTranslateService();
        subscription = translateService.getTranslation(text, direction)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<TranslationResponse>() {
                    @Override
                    public void onCompleted() {
                        if (translationResponse != null)
                            translateMvpView.showTranslation(translationResponse);
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

    public void Meaning(String text, String direction) {
        if (subscription != null) subscription.unsubscribe();

        YamblzTranslate application = YamblzTranslate.get(translateMvpView.getContext());
        final DictionaryService dictionaryService = application.getDictionaryService();
        subscription = dictionaryService.getMeaning(text, direction)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<DictionaryResponse>() {
                    @Override
                    public void onCompleted() {
                        if (!dictionaryResponse.getDefinitions().isEmpty())
                            translateMvpView.showDictionaryMeaning(dictionaryResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(DictionaryResponse dictionaryResponse) {
                        TranslatePresenter.this.dictionaryResponse = dictionaryResponse;
                    }
                });
    }

}
