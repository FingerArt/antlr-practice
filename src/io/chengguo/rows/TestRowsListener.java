package io.chengguo.rows;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class TestRowsListener extends RowsBaseListener {
    public static void main(String[] args) {
        RowsLexer rowsLexer = new RowsLexer(new ANTLRInputStream("asdas asdfasf\r\nabc 哈哈😄\r\n"));
        RowsParser rowsParser = new RowsParser(new CommonTokenStream(rowsLexer), 2);
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(new TestRowsListener(), rowsParser.file());
    }
}
