package org.study.domain;

import org.apache.logging.log4j.util.StringBuilders;

public class SemanticRepository {
    public static final String PROGRAM_HEADER = """
                // cabecalho
                .assembly extern mscorlib {}
                .assembly _programa{}
                .module _programa.exe
                .class public _programa{
                    .method static public void _principal(){
                     .entrypoint
                """;

    public static final String END_PROGRAM = """
            // fim de programa
            ret
            }
            }
            """;

    public static final String BREAK_LINE = "\n";

    public static String declareIdentifier(String identifier, String type) {
        return ".locals (" + type + " " + identifier + ") " + BREAK_LINE;
    }

    public static final String CONVERT_INT = "conv.i8 \n";

    public static final String CONVERT_FLOAT = "conv.r8 \n";

    public static final String LOAD_STRING = "ldstr ";

    public static final String READ_LINE = "call string [mscorlib]System.Console::ReadLine() " + BREAK_LINE;

    public static String in(String type) {
        return "call " + type + " [mscorlib]System.Int64::Parse(string) " + BREAK_LINE;
    }


    public static String out(String type) {
            return "call void [mscorlib]System.Console::Write(" + type + ") " + BREAK_LINE;
    }

    public static String loadIntValue(String lexeme) {
        return ("ldc.i8 " + lexeme + CONVERT_FLOAT + BREAK_LINE);
    }

    public static String loadTrue(String lexeme) {
        return "ldc.i4.1 " + lexeme + BREAK_LINE;
    }

    public static String loadFalse(String lexeme) {
        return "ldc.i4.0 " + lexeme + BREAK_LINE;
    }

    public static String loadFloatValue(String lexeme) {
        return ("ldc.r8 " + lexeme + BREAK_LINE);
    }

    public static String loadCharValue(String lexeme) {
        return (LOAD_STRING + "\"" + lexeme + "\"" + BREAK_LINE);
    }

    public static String loadStringValue(String lexeme) {
        return (LOAD_STRING + lexeme + BREAK_LINE);
    }

    public static String loadValueInIdentifier(String lexeme) {
        return "stloc " + lexeme + BREAK_LINE;
    }

    public static String notExpression() {
        return "ldc.i4.1 " + BREAK_LINE +
               "xor " + BREAK_LINE;
    }
}
