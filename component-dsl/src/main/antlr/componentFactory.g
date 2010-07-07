grammar componentFactory;
options {
    language=Java;  
}

componentDeclaration 
	: 'component' componentDefinition '{' .* '}'	
	;

componentDefinition
        : '(' IDENTIFIER ')'
	;
	
componentParameter
	: parameterName ':' '"' parameterValue '"'	
	;

parameterName
	: IDENTIFIER
	;
	
parameterValue
	: STRING	
	;

parameterBlock 
	: '{' .* '}'
	;
	
STRING          
@init{StringBuilder lBuf = new StringBuilder();}
    :   
           '"' 
           ( escaped=ESC {lBuf.append(escaped.getText());} | 
             normal=~('"'|'\\'|'\n'|'\r')     {lBuf.appendCodePoint(normal);} )* 
           '"'     
           {setText(lBuf.toString());}
    ;

fragment
ESC
    :   '\\'
        (       'n'    {setText("\n");}
        |       'r'    {setText("\r");}
        |       't'    {setText("\t");}
        |       'b'    {setText("\b");}
        |       'f'    {setText("\f");}
        |       '"'    {setText("\"");}
        |       '\''   {setText("\'");}
        |       '/'    {setText("/");}
        |       '\\'   {setText("\\");}

        )
    ;

STATEMENT_TERMINATOR : ';';
NUMBER : '0'..'9'+ ;
IDENTIFIER :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;
WS  : (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;};
COMMENT : '/*' .* '*/' {$channel=HIDDEN;};
LINE_COMMENT : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};


