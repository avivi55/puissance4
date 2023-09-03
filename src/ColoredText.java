public class ColoredText {
    private String ansiColor;
    private final String clear = "\u001B[0m";
    private String content;

    ColoredText(String content, int red, int green, int blue){
        ansiColor = "\u001B[38;2;" + red + ";" + green + ";" + blue + "m";
        this.content = content;
    }

    ColoredText(String content, int colorCode){
        ansiColor = "\u001B[38;5;" + colorCode + "m";
        this.content = content;
    }

    @Override
    public String toString(){
        return ansiColor + content + clear;
    }
}
