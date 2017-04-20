package combruce_willis.httpsgithub.yamblztranslate.Presenter;

/**
 * Created by yury- on 4/20/2017.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
