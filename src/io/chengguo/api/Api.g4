grammar Api;

api
    : info http
    ;

info
    : TITLE DESCRIPTION?
    ;

http
    : method uri
    ;

variable
    : '$' '{' ID? '}'
    ;

method:
	'GET'
	| 'HEAD'
	| 'POST'
	| 'PUT'
	| 'DELETE'
	| 'CONNECT'
	| 'OPTIONS'
	| 'TRACE';

uri
    : scheme '://' host (':' port)? ('/' path)? query? LF?
    ;

scheme
    : 'http'
    | 'https'
    | string
    ;

host
    : '/'? (hostnumber | hostname)
    ;

hostnumber
   : DIGITS '.' DIGITS '.' DIGITS '.' DIGITS
   ;

hostname
   : string ('.' string)*
   ;

port
    : DIGITS
    ;

path
    : string ('/' string)*
    ;

query
    : '?' search
    ;

search
    : searchparameter ('&' searchparameter)*
    ;

searchparameter
    : string ('=' (string|DIGITS|HEX))?
    ;

string
    : STRING
    ;

TITLE
    : '#' ~[#\r\n]+ '\r'? '\n'
    ;

DESCRIPTION
    : 'DESCRIPTION'
    ;

DIGITS
   : [0-9]+
   ;

STRING
    : ([a-zA-Z~0-9] | HEX) ([a-zA-Z0-9.-] | HEX)*
    ;

HEX
    : ('%' [0-9a-fA-F] [0-9a-fA-F])+
    ;

ID
    : [a-zA-Z_] [a-zA-Z0-9_]*
    ;

LF
    : [\r\n]+
    ;

//WS
//    : [ \t\r\n]+ -> skip
//    ;