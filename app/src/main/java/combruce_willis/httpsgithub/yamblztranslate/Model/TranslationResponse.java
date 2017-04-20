package combruce_willis.httpsgithub.yamblztranslate.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yury- on 4/20/2017.
 */

public class TranslationResponse {

        @SerializedName("code")
        @Expose
        private Integer code;

        @SerializedName("lang")
        @Expose
        private String lang;

        @SerializedName("text")
        @Expose
        private List<String> text = null;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public List<String> getText() {
            return text;
        }

        public void setText(List<String> text) {
            this.text = text;
        }

}
