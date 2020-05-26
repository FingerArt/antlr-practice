package io.chengguo.rows;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class TestDataListener extends DataBaseListener {

    public final TokenStreamRewriter streamRewriter;

    public TestDataListener(TokenStream tokenStream) {
        streamRewriter = new TokenStreamRewriter(tokenStream);
    }

    @Override
    public void enterGroup(DataParser.GroupContext ctx) {
        System.out.println(ctx.getText());
    }

    @Override
    public void enterSequence(DataParser.SequenceContext ctx) {
        streamRewriter.insertBefore(ctx.start, ":");
    }

    public static void main(String[] args) {
        DataLexer dataLexer = new DataLexer(new ANTLRInputStream("1 2 2 3 4 3"));
        CommonTokenStream tokenStream = new CommonTokenStream(dataLexer);
        DataParser dataParser = new DataParser(tokenStream);
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        TestDataListener listener = new TestDataListener(tokenStream);
        parseTreeWalker.walk(listener, dataParser.init());

        System.out.println(listener.streamRewriter.getText());
    }
}
