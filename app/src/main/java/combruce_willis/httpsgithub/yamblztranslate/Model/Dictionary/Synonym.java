
package combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Synonym {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("pos")
    @Expose
    private String partOfSpeech;

    @SerializedName("gen")
    @Expose
    private String gen;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String pos) {
        this.partOfSpeech = pos;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

}
