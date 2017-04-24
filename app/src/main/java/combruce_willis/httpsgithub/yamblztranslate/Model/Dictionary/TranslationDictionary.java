
package combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslationDictionary {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("PartOfSpeech")
    @Expose
    private String partOfSpeech;

    @SerializedName("gen")
    @Expose
    private String gen;

    @SerializedName("syn")
    @Expose
    private List<Synonym> synonym = null;

    @SerializedName("mean")
    @Expose
    private List<Meaning> meaning = null;

    @SerializedName("ex")
    @Expose
    private List<Example> example = null;

    @SerializedName("asp")
    @Expose
    private String asp;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<Synonym> getSynonym() {
        return synonym;
    }

    public void setSynonym(List<Synonym> synonym) {
        this.synonym = synonym;
    }

    public List<Meaning> getMeaning() {
        return meaning;
    }

    public void setMeaning(List<Meaning> meaning) {
        this.meaning = meaning;
    }

    public List<Example> getExample() {
        return example;
    }

    public void setExample(List<Example> example) {
        this.example = example;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getAsp() {
        return asp;
    }

    public void setAsp(String asp) {
        this.asp = asp;
    }
}
