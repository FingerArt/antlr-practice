package io.chengguo.expr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.HashMap;

public class ExprVisitor extends ExprGrammarBaseVisitor<Integer> {
    HashMap<String, Integer> memory = new HashMap<>();

    @Override
    public Integer visitAssign(ExprGrammarParser.AssignContext ctx) {
        String name = ctx.ID().getText();
        Integer value = visit(ctx.expr());
        System.out.printf(" = %s(%s)\r\n", value, name);
        memory.put(name, value);
        return value;
    }

    @Override
    public Integer visitMulDiv(ExprGrammarParser.MulDivContext ctx) {
        Integer first = visit(ctx.expr(0));
        Integer second = visit(ctx.expr(1));
        if (ctx.op.getType() == ExprGrammarLexer.MUL) {
            System.out.printf("%s * %s ", first, second);
            return first * second;
        }
        System.out.printf("%s / %s ", first, second);
        return first / second;
    }

    @Override
    public Integer visitAddSub(ExprGrammarParser.AddSubContext ctx) {
        Integer first = visit(ctx.expr(0));
        Integer second = visit(ctx.expr(1));
        if (ctx.op.getType() == ExprGrammarLexer.ADD) {
            System.out.printf("%s + %s ", first, second);
            return first + second;
        }
        System.out.printf("%s - %s ", first, second);
        return first - second;
    }

    @Override
    public Integer visitInt(ExprGrammarParser.IntContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitId(ExprGrammarParser.IdContext ctx) {
        String name = ctx.ID().getText();
        if (memory.containsKey(name)) {
            return memory.get(name);
        }
        return 0;
    }

    @Override
    public Integer visitClear(ExprGrammarParser.ClearContext ctx) {
        memory.clear();
        System.out.println("clear");
        return 0;
    }

    @Override
    public Integer visitParens(ExprGrammarParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitPrintExpr(ExprGrammarParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println("visitPrintExpr: " + value);
        return value;
    }

    static String input = "a = 10\n" +
            "clear\n" +
            "b = 10 + 25 * 2\n" +
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
