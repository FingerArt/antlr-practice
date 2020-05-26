package io.chengguo.java;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;

public class ExtractInterfaceListener extends JavaParserBaseListener {

    private final JavaParser javaParser;

    public ExtractInterfaceListener(JavaParser javaParser) {
        this.javaParser = javaParser;
    }

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx /* 上下文 */) {
        System.out.println("interface I" + ctx.IDENTIFIER() + " {");
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("}");
    }

    public String hello(/**
                         * 你好
                         *
                         * @return
                         */) {
        return "haha";
    }

    /**
     * 进入方法
     *
     * @param ctx
     */
    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        TokenStream tokenStream = javaParser.getTokenStream();
        String type = ctx.typeTypeOrVoid().getText();
        TerminalNode identifier = ctx.IDENTIFIER();
        String params = tokenStream.getText(ctx.formalParameters());
        String throwses = "";
        if (ctx.THROWS() != null) {
            throwses = " " + ctx.THROWS().getText() + " " + ctx.qualifiedNameList().getText();
        }
        System.out.println(String.format("\t%s\t%s%s%s;", type, identifier, params, throwses));
    }

    /**
     * 测试入口
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        JavaLexer javaLexer = new JavaLexer(new ANTLRFileStream("src/io/chengguo/java/ExtractInterfaceListener.java"));
        JavaParser javaParser = new JavaParser(new CommonTokenStream(javaLexer));
        ExtractInterfaceListener extractInterfaceListener = new ExtractInterfaceListener(javaParser);
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(extractInterfaceListener, javaParser.compilationUnit());
    }
}
