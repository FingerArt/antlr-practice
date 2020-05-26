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
locals [
    /**
     * 在RowContext类中创建成员变量
     */
    int i=0,
    /**
     * 第二个变量
     */
    int tt = 2
]
    : (
        STUFF {
            // 得到 STUFF token 的结果
            $i++;
            if($i == col) {
                System.out.println($i+" - "+$STUFF.text);
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