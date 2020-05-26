grammar Data;

init
    : group+
    ;

group
    : INT sequence[$INT.int]
    ;

sequence[
    /**
     * 在 SequenceContext 中创建成员变量 n，并新建一个包含该成员变量的构造函数
     */
    int n
]
locals[
    /**
     * 在 SequenceContext 中创建成员变量 i
     */
    int i
]
    : ({$i<$n}? INT {$i++;})* // 语义判定
    ;

INT
    : [0-9]+
    ;

WS
    : [ \t] -> channel(HIDDEN)
    ;