grammar javaCore;
options {
    language=Java;  
}

sourceFile 
	: packageDeclaration importDelcaration*	classDeclaration*
	;

packageDeclaration 
	: 'package' (IDENTIFIER '.'?)+  ';' 
	;
	
importDelcaration
        : 'import' ? (IDENTIFIER '.')+ ('*'|IDENTIFIER) ';'
        ;
        
classDeclaration 
	: modifier* 'class' IDENTIFIER '{' (modifier | parameterBlock | codeBlock | .)* '}'	
	;

modifier
	: ('public' | 'private' | 'protected' | 'static' | 'final')  
	;

codeBlock 
	: '{' .* '}'
	;
parameterBlock 
	: '(' .* ')'	
	;

STATEMENT_TERMINATOR : ';';
NUMBER : '0'..'9'+ ;
IDENTIFIER :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;
WS  : (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;};
COMMENT : '/*' .* '*/' {$channel=HIDDEN;};
LINE_COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};