grammar CSV;

csv
    : hdr row*
    ;

hdr
    : row
    ;

row
    : field (',' field)* '\r'? '\n'
    ;

field
    : TEXT
    | STRING
    | NUMBER
    |
    ;

STRING
    : '"' ('""'|~'"')* '"'
    ;

NUMBER
    : INT
    | FLOAT
    ;

TEXT
    : ~[,\r\n"]+
    ;

INT
    : ('0' | [1-9] DIGIT*)
    ;

FLOAT
    : INT* '.' DIGIT*
    ;

DIGIT
    : [0-9]
    ;