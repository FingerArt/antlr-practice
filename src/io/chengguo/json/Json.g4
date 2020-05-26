grammar Json;

json
    : object | array
    ;

object
    : '{' pair (',' pair)* '}'
    | '{' '}'
    ;

array
    : '[' value (',' value)* ']'
    | '[' ']'
    ;

pair
    : STRING ':' value
    ;

value
    : object    # ValueObject
    | array     # ValueArray
    | BOOLEAN   # ValueBoolean
    | STRING    # ValueString
    | NUMBER    # ValueNumber
    | DOUBLE    # ValueDouble
    | 'null'    # ValueNull
    ;

BOOLEAN
    : 'true'
    | 'false'
    ;

STRING
    : '"' (ESC|~["\r\n])* '"'
    ;

NUMBER
    : '-'? INTEGER [Ee] [+\-]? INTEGER
    | '-'? INTEGER
    | '-'? DOUBLE
    ;

INTEGER
    : '0' | [1-9] DIGIT*
    ;

DOUBLE
    : INTEGER '.' DIGIT*
    ;

ESC
    : '\\' (["bfnrt\\/] | UNICODE)
    ;

UNICODE
    : 'u' HEX HEX HEX HEX
    ;

HEX
    : [0-9a-fA-F]
    ;

DIGIT
    : [0-9]
    ;

WS
    : [ \t\r\n] -> skip
    ;