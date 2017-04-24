
package combruce_willis.httpsgithub.yamblztranslate.Model.Dictionary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Definition {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("pos")
    @Expose
    private String PartOfSpeech;

    @SerializedName("ts")
    @Expose
    private String transcription;

    @SerializedName("tr")
    @Expose
    private List<TranslationDictionary> translationDictionary = null;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPartOfSpeech() {
        return PartOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.PartOfSpeech = partOfSpeech;
    }

    public List<TranslationDictionary> getTranslationDictionary() {
        return translationDictionary;
    }

    public void setTranslationDictionary(List<TranslationDictionary> translationDictionary) {
        this.translationDictionary = translationDictionary;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
