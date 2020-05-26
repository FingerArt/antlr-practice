grammar Rows;

@parser::members {
	/**
	 * 行号
	 */
	int col;

	public RowsParser(TokenStream input, int col) {
		this(input);
		this.col = col;
	}
}

file: (row NL)+;

row
locals [int ii=0]
    : (
        STUFF {
            $ii++;
            if($ii == col) {
                System.out.println($ii+" - "+$STUFF.text);
            }
        }
      )+
    ;

TAB
    : [ \t] -> skip
    ;

NL
    : '\r'? '\n'
    ;

STUFF
    : ~[ \r\n\t]+
    ;