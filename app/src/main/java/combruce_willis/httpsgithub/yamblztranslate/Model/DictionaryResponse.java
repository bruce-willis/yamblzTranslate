
package combruce_willis.httpsgithub.yamblztranslate.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary.Definition;

public class DictionaryResponse {

    @SerializedName("def")
    @Expose
    private List<Definition> definitions = null;

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

}
