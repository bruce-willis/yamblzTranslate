package combruce_willis.httpsgithub.yamblztranslate.Model.Service;

import combruce_willis.httpsgithub.yamblztranslate.Model.DictionaryResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yury- on 4/24/2017.
 */

public interface DictionaryService {

    String API_KEY = "dict.1.1.20170415T145749Z.2f364b8ea6dfe8ff.7dc814a169985d16a745cfe659eb943e023806bb";
    String BaseUrl = "https://dictionary.yandex.net/api/v1/dicservice.json/";

    @GET("lookup?key=" + API_KEY)
    Observable<DictionaryResponse> getMeaning(@Query("text") String text, @Query("lang") String direction);

    class Factory {
        public static DictionaryService create() {
            return new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build().create(DictionaryService.class);
        }
    }

}
