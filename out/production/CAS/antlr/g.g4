grammar g;

prog            : boolExpr (OR boolExpr)*;

boolExpr        : compareExpr (AND compareExpr)*;

compareExpr     : logicOpExpr (BOOLCOMPARE logicOpExpr)*;

logicOpExpr     : operatorExpr (COMPARE operatorExpr)*
                | BOOL;

operatorExpr    : timesExpr ((PLUS | MINUS) timesExpr)*;

timesExpr       : operatorTail ((TIMES | DIVIDED) operatorTail)*;

operatorTail    : NUM | ID
                | LPAR operatorExpr RPAR;


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