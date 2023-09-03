public class ColoredText {
    private final String ansiColor;
    private final String content;

    ColoredText(String content, int colorCode){
        ansiColor = "\u001B[38;5;" + colorCode + "m";
        this.content = content;
    }

    @Override
    public String toString(){
        String clear = "\u001B[0m";
        return ansiColor + content + clear;
    }
}
