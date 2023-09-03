public class Tabulate {
    private final int finalWidth;
    private final Object[][] data;

    private boolean showIndexes = false;

    Tabulate(Object[][] data){
        this.data = data;

        int width = data[0].length;

        finalWidth = 6 * width + 1;
    }

    Tabulate(Object[][] data, boolean indexes){
        this(data);

        showIndexes = indexes;
    }

    String getIndexes(){
        StringBuilder indexes = new StringBuilder();

        indexes.append(" ".repeat(3));

        int index = 0;

        for (int i = 0; i < finalWidth-1; i++) {
            if (i % 6 == 0)
                indexes.append(index++);
            else
                indexes.append(' ');
        }

        indexes.append('\n');

        return indexes.toString();
    }

    String getRowDelimiter(char suffix, char column, char postfix){
        StringBuilder rowDelimiter = new StringBuilder();

        rowDelimiter.append(suffix);

        for (int i = 1; i < finalWidth-1; i++) {
            if (i % 6 == 0)
                rowDelimiter.append(column);
            else
                rowDelimiter.append('─');
        }

        rowDelimiter.append(postfix)
                .append('\n');

        return rowDelimiter.toString();
    }

    String getRow(Object[] row){
        StringBuilder rowDelimiter = new StringBuilder();

        rowDelimiter.append('║');

        int index = 0;

        for (int i = 1; i < finalWidth-1; i++) {
            if (i % 6 == 0)
                rowDelimiter.append('│');
            else if (i % 3 == 0) {
                rowDelimiter.append(row[index++]);
            }
            else
                rowDelimiter.append(' ');
        }

        rowDelimiter.append('║')
                .append('\n');

        return rowDelimiter.toString();
    }

    @Override
    public String toString(){
        StringBuilder finalString = new StringBuilder();

        String topDelimiter = getRowDelimiter('╓', '┬', '╖');
        String rowDelimiter = getRowDelimiter('╟', '┼', '╢');
        String bottomDelimiter = getRowDelimiter('╠', '┴', '╣');
        String boardFeet = "╨" + " ".repeat(finalWidth-2) + "╨";

        if(showIndexes){
            finalString.append(getIndexes());
        }

        finalString.append(topDelimiter);

        for (int i = 0; i < data.length; i++) {
            finalString.append(getRow(data[i]));

            if (i == data.length-1)
                finalString.append(bottomDelimiter);
            else
                finalString.append(rowDelimiter);
        }

        finalString.append(boardFeet);

        return finalString.toString();
    }
}
