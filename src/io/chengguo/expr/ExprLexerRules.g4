lexer grammar ExprLexerRules;

ADD
    : '+'
    ;

SUB
    : '-'
    ;

MUL
    : '*'
    ;

DIV
    : '/'
    ;

ID
    : [a-zA-Z]+
    ;

INT
    : [0-9]+
    ;

NEWLINE
    : '\r'? '\n'
    ;

WS
    : [ \t]+ -> skip
    ;