grammar javaCore;
options {
    language=Java;  
}

packageDeclaration 
	: 'package' (PACKAGE_NAME '.'?)+  ';' 
	;
	
importDelcaration
        : 'import' ? (PACKAGE_NAME '.')+ ('*'|IDENTIFIER) ';'
        ;	

STATEMENT_TERMINATOR : ';';
NUMBER : '0'..'9'+ ;
PACKAGE_NAME : ('a'..'z'|'_') ('a'..'z'|'0'..'9'|'_')* ;
IDENTIFIER :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;
WS  : (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;};
COMMENT : '/*' .* '*/' {$channel=HIDDEN;};
LINE_COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};