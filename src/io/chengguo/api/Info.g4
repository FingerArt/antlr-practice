grammar Info;

init
    : (NL* item)*
    ;

item
    : '-' key ':' value
    ;

key
    : Keyword
    ;

value
    : Keyword
    ;

Keyword
    : (~[ \\\b\f\t\r\n\-:] | EscapeSequence)+
    ;

NL
    : '\r'? '\n'
    ;

WS
    : [ \t] -> skip
    ;

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;

fragment HexDigits
    : HexDigit ((HexDigit | '_')* HexDigit)?
    ;

fragment HexDigit
    : [0-9a-fA-F]
    ;