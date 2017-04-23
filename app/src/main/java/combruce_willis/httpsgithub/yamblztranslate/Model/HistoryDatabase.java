package combruce_willis.httpsgithub.yamblztranslate.Model;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by yury- on 4/22/2017.
 */

public class HistoryDatabase extends RealmObject {

    @Required
    private String sourceString;

    @Required
    private String translationString;

    @Required
    private String languageSourceCode;

    @Required
    private String languageTargetCode;

    @Required
    private Date date;
    
    private boolean favorite = false;

    public String getSourceString() {
        return sourceString;
    }

    public void setSourceString(String sourceString) {
        this.sourceString = sourceString;
    }

    public String getTranslationString() {
        return translationString;
    }

    public void setTranslationString(String translationString) {
        this.translationString = translationString;
    }

    public String getLanguageSourceCode() {
        return languageSourceCode;
    }

    public void setLanguageSourceCode(String languageSourceCode) {
        this.languageSourceCode = languageSourceCode;
    }

    public String getLanguageTargetCode() {
        return languageTargetCode;
    }

    public void setLanguageTargetCode(String languageTargetCode) {
        this.languageTargetCode = languageTargetCode;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
