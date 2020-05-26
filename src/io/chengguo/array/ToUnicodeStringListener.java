package io.chengguo.array;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ToUnicodeStringListener extends ArrayInitBaseListener {
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.print("[");
    }

    @Override
    public void enterValueInt(ArrayInitParser.ValueIntContext ctx) {
        int value = Integer.parseInt(ctx.getText());
        System.out.printf("\\u%04x", value);
    }

    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.print("]");
    }

    public static void main(String[] args) {
        String input = "{99, 2, 3, {4, 5}}";
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(input);
        ArrayInitLexer lexer = new ArrayInitLexer(antlrInputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ArrayInitParser parser = new ArrayInitParser(tokenStream);
        ArrayInitParser.InitContext init = parser.init();
        ParseTreeWalker treeWalker = new ParseTreeWalker();
        treeWalker.walk(new ToUnicodeStringListener(), init);
//        System.out.println(init.toStringTree(parser));
    }
}
