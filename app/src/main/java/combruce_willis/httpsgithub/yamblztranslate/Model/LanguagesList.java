package combruce_willis.httpsgithub.yamblztranslate.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by yury- on 4/21/2017.
 */

public class LanguagesList {

    @SerializedName("dirs")
    @Expose
    private List<String> directions = null;

    @SerializedName("langs")
    @Expose
    private Map<String, String> languages = null;

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public List<String> getDirections() {

        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }
}
