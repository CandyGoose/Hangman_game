package backend.academy;

public class WordWithHint {
    private final String word;
    private final String hint;

    public WordWithHint(String word, String hint) {
        this.word = word;
        this.hint = hint;
    }

    public String getWord() {
        return word;
    }

    public String getHint() {
        return hint;
    }
}
