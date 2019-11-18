package io.chengguo.expr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

class Test {

    static String input = "a = 10\n" +
            "clear\n" +
            "b = 10 + 25\n" +
            "c = a * b\n" +
            "d = a + b\n";

    public static void main(String[] args) throws IOException {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(input);
        ExprGrammarLexer lexer = new ExprGrammarLexer(antlrInputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ExprGrammarParser parser = new ExprGrammarParser(tokenStream);
        ExprGrammarParser.InitContext tree = parser.init();

        new ExprVisitor().visit(tree);

        //        System.out.println(tree.toStringTree(parser));
    }
}