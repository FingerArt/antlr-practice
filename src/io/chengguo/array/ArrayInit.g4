grammar ArrayInit;

init
    : '{' value (',' value)* '}'
    | '{' '}'
    ;

value
    :   init    # ValueInit
    |   INT     # ValueInt
    ;

INT
    : NON_ZERO_DIGIT DIGIT*
    ;

WS :    [ \t\r\n]+ -> skip ;

LCURL: '{';

RCURL: '}';

/**
 * fragments
 */

/// nonzerodigit   ::=  "1"..."9"
fragment NON_ZERO_DIGIT
 : [1-9]
 ;

// digit          ::=  "0"..."9"
fragment DIGIT
    : [0-9]
    ;