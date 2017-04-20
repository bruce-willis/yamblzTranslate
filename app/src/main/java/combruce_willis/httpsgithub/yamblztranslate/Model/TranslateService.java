package combruce_willis.httpsgithub.yamblztranslate.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yury- on 4/20/2017.
 */

public interface TranslateService {

    String API_KEY = "trnsl.1.1.20170415T142217Z.2741b8874c74d8a1.5d1326cc2e04df1e865b1c80f74d749d94c61bd2";
    String BaseUrl = "https://translate.yandex.net/api/v1.5/tr.json/";

    @GET("translate?key=" + API_KEY)
    Observable<TranslationResponse> getTranslation(@Query("text") String text, @Query("lang") String language);

    class Factory{
        public static TranslateService create() {
            return new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build().create(TranslateService.class);
        }
    }
}
