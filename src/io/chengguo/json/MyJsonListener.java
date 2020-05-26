package io.chengguo.json;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class MyJsonListener extends JsonBaseListener {

    @Override
    public void enterValueNumber(JsonParser.ValueNumberContext ctx) {
        System.out.println("enterValueNumber: " + ctx.getText());
    }

    @Override
    public void enterValueString(JsonParser.ValueStringContext ctx) {
        System.out.println("enterValueString: " + ctx.getText());
    }

    public static void main(String[] args) {
        JsonListener jsonListener = new MyJsonListener();
        JsonLexer jsonLexer = new JsonLexer(new ANTLRInputStream("[\"hello\",2432342,2323]"));
        JsonParser jsonParser = new JsonParser(new CommonTokenStream(jsonLexer));
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(jsonListener, jsonParser.json());
    }
}
