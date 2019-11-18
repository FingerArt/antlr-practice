grammar ExprGrammar;

import ExprLexerRules;

init
    : stat +
    ;

stat
    : expr NEWLINE          # printExpr
    | ID '=' expr NEWLINE   # assign
    | NEWLINE               # blank
    | 'clear'               # clear
    ;

expr
    : ID                        # id
    | INT                       # int
    | expr op=('*'|'/') expr    # MulDiv
    | expr op=('+'|'-') expr    # AddSub
    | '(' expr ')'              # parens
    ;