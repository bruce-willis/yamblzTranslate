package combruce_willis.httpsgithub.yamblztranslate;

import android.app.Application;
import android.content.Context;

import combruce_willis.httpsgithub.yamblztranslate.Model.Service.DictionaryService;
import combruce_willis.httpsgithub.yamblztranslate.Model.Service.TranslateService;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by yury- on 4/20/2017.
 */

public class YamblzTranslate extends Application{

    private TranslateService translateService;
    private DictionaryService dictionaryService;
    private Scheduler defaultSubscribeScheduler;

    public static YamblzTranslate get(Context context) {
        return (YamblzTranslate) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    public TranslateService getTranslateService() {
        if (translateService == null) {
            translateService = TranslateService.Factory.create();
        }
        return translateService;
    }

    public void setTranslateService(TranslateService translateService) {
        this.translateService = translateService;
    }

    public DictionaryService getDictionaryService() {
        if (dictionaryService == null) {
            dictionaryService = DictionaryService.Factory.create();
        }
        return dictionaryService;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return  defaultSubscribeScheduler;
    }

    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }
}
