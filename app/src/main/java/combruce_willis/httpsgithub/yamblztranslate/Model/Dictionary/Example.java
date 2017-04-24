
package combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("tr")
    @Expose
    private List<ExampleText> tr = null;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ExampleText> getTr() {
        return tr;
    }

    public void setTr(List<ExampleText> tr) {
        this.tr = tr;
    }

}


