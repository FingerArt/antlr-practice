grammar Cymbol;

/*

// 这是注释

int ok = 10;

int fact(int x) {
    if x==0 then return 1;
    return x * fact(x-1);
}

*/

cymbol
    : varDecl*
    | functionDecl*
    ;

varDecl
    : type ID ('=' expr)? ';'
    ;

functionDecl
    : type ID '(' formalParamters? ')' block
    ;

stat
    : 'if' expr 'then' stat ('else' stat)?
    | 'return' expr ';'
    | expr ';'
    | varDecl
    ;

block
    : '{' stat* '}'
    ;

formalParamters
    : formalParamter (',' formalParamter)*
    ;

formalParamter
    : type ID
    ;

type
    : 'int'
    | 'float'
    | 'void'
    | 'string'
    ;

expr
    : ID '(' exprList? ')'
    | '-' expr
    | '!' expr
    | expr ('*'|'/') expr
    | expr ('+'|'-') expr
    | '(' expr ')'
    | ID
    | INT
    | expr '==' expr
    ;

exprList
    : expr (',' expr)*
    ;

ID
    : [a-zA-Z_$] [0-9a-zA-Z_$]*
    ;

INT
    : '-'? ('0' | [1-9] [0-9]*)
    ;

WS
    : [ \r\n\t] -> skip
    ;