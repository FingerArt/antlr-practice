package io.chengguo.array;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        String input = "{1, 2, 3, {4, 5}}";
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