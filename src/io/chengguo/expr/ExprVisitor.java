package io.chengguo.expr;

import java.util.HashMap;

public class ExprVisitor extends ExprGrammarBaseVisitor<Integer> {
    HashMap<String, Integer> memory = new HashMap<>();

    @Override
    public Integer visitAssign(ExprGrammarParser.AssignContext ctx) {
        String name = ctx.ID().getText();
        Integer value = visit(ctx.expr());
        System.out.println(value);
        memory.put(name, value);
        return value;
    }

    @Override
    public Integer visitInt(ExprGrammarParser.IntContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitMulDiv(ExprGrammarParser.MulDivContext ctx) {
        Integer first = visit(ctx.expr(0));
        Integer second = visit(ctx.expr(1));
        if (ctx.op.getType() == ExprGrammarLexer.MUL) {
            return first * second;
        }
        return first / second;
    }

    @Override
    public Integer visitAddSub(ExprGrammarParser.AddSubContext ctx) {
        Integer first = visit(ctx.expr(0));
        Integer second = visit(ctx.expr(1));
        if (ctx.op.getType() == ExprGrammarLexer.ADD) {
            return first + second;
        }
        return first - second;
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
        return 0;
    }

    @Override
    public Integer visitParens(ExprGrammarParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitPrintExpr(ExprGrammarParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }
}
