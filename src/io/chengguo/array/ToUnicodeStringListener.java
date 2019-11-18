package io.chengguo.array;

public class ToUnicodeStringListener extends ArrayInitBaseListener {
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.print("\"");
    }

    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
        if (ctx.init() != null) {
            return;// skip
        }
        int value = Integer.parseInt(ctx.getText());
        System.out.printf("\\u%04x", value);
    }

    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.print("\"");
    }
}
