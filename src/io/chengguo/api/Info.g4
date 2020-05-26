grammar Info;

init
    : title description? attribute*
    ;

title
    : '---' Keyword
    ;

description
    : '"""' Text '"""'
    ;

attribute
    : '-' key ':' value
    ;

key
    : Keyword
    ;

value
    : Keyword
    ;

Keyword
    : (~[ \\\b\f\t\r\n\-:"] | EscapeSequence)+
    ;

Text
    : (~[\\\b\f\t"] | EscapeSequence)+
    ;

WS
    : [ \t\r\n] -> channel(HIDDEN)
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