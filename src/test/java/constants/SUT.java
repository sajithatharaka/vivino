package constants;

public enum SUT {
    QA(""),
    PRD("https://www.vivino.com/");

    private String ULR;

    SUT(String URL) {
        this.ULR = URL;
    }

    public String getURL() {
        return ULR;
    }
}