grammar g;

prog            : boolExpr (OR boolExpr)*;

operatorExpr    : timesExpr ((PLUS | MINUS) timesExpr)*;

timesExpr       : operatorTail ((TIMES | DIVIDED) operatorTail)*;

operatorTail    : NUM | ID
                | LPAR operatorExpr RPAR;

boolExpr        : compareExpr (AND compareExpr)*;

compareExpr     : logicOpExpr (BOOLCOMPARE logicOpExpr)*;

logicOpExpr     : operatorExpr (COMPARE operatorExpr)*
                | BOOL;

OR              : '||';
AND             : '&&';
BOOLCOMPARE     : '='
                | '!=';
COMPARE         : '>'
                | '>='
                | '<'
                | '<=';
BOOL            : 'true' | 'false';
WHITESPACE      : [\t ]+ -> skip ;
LPAR            : '(';
RPAR            : ')';
ID              : [a-zA-Z] [a-zA-Z0-9]*;
NUM             : [-]?[0-9]+('.'[0-9]+)?;
PLUS            : '+';
MINUS           : '-';
TIMES           : '*';
DIVIDED         : '/';