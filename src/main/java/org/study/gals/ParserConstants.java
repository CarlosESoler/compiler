package org.study.gals;

public interface ParserConstants
{
    int START_SYMBOL = 40;

    int FIRST_NON_TERMINAL    = 40;
    int FIRST_SEMANTIC_ACTION = 71;

    int[][] PARSER_TABLE =
    {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  1, -1, -1, -1, -1, -1, -1, -1,  1,  1, -1, -1, -1, -1, -1, -1,  1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  3, -1, -1, -1, -1, -1,  2, -1,  3,  3,  2, -1, -1, -1, -1, -1,  3, -1,  3, -1,  2,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  4, -1, -1, -1, -1, -1, -1, -1,  6,  8, -1, -1, -1, -1, -1, -1,  5, -1,  7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 10, -1, -1, -1, -1, -1, -1, -1, 10, 10, -1, -1, -1, -1,  9, -1, 10, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 11, -1, 11, -1, -1, -1, -1, 11, 11, -1, -1, -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 12, -1, -1, -1, -1, 13, -1, 13, 12, 12, -1, -1, 13, 13, -1, -1, 12, 13, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, 15, -1, -1, 15 },
        { -1, -1, -1, -1, -1, -1, 17, -1, 20, -1, -1, -1, -1, 19, 18, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 25, 25, 25, 25, 25, -1, -1, -1, -1, -1, -1, 25, -1, -1, -1, -1, -1, -1, -1, 25, -1, -1, -1, -1, 25, -1, -1, -1, -1, 25, 25, -1, -1, -1, -1, -1, 25, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 27, -1, -1, -1, 26 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 35, 35, 35, 35, 35, -1, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, 35, -1, -1, -1, -1, 35, 35, -1, -1, -1, -1, -1, 35, -1 },
        { -1, 36, -1, -1, -1, -1, -1, 36, -1, 36, 36, -1, -1, -1, -1, -1, -1, 36, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, 36, -1, -1, 36 },
        { -1, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, -1, -1, 42, -1, -1, -1, -1, 39, 39, -1, -1, -1, -1, -1, 39, -1 },
        { -1, 43, 43, 43, 43, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, 43, -1, -1, -1, -1, -1, 43, -1 },
        { -1, 44, -1, -1, -1, -1, -1, 44, -1, 44, 44, -1, -1, -1, -1, -1, -1, 44, -1, 44, -1, -1, -1, 44, 44, -1, 45, 45, 45, 45, -1, -1, -1, -1, 44, 44, -1, -1, 44 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 46, 47, 48, 49, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 50, 50, 50, 50, 50, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, 50, -1, -1, -1, -1, -1, 50, -1 },
        { -1, 51, -1, -1, -1, -1, -1, 51, -1, 51, 51, -1, -1, -1, -1, -1, -1, 51, -1, 51, -1, -1, -1, 51, 51, -1, 51, 51, 51, 51, 52, 53, -1, -1, 51, 51, -1, -1, 51 },
        { -1, 54, 54, 54, 54, 54, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 54, 54, -1, -1, -1, -1, -1, 54, -1 },
        { -1, 55, -1, -1, -1, -1, -1, 55, -1, 55, 55, -1, -1, -1, -1, -1, -1, 55, -1, 55, -1, -1, -1, 55, 55, -1, 55, 55, 55, 55, 55, 55, 56, 57, 55, 55, -1, -1, 55 },
        { -1, 58, 59, 60, 61, 62, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, 65, -1, -1, -1, -1, -1, 63, -1 }
    };

    int[][] PRODUCTIONS =
        {
                {  17, 172,  44,  41,  12, 173 },
                {  43,  36,  42 },
                {   0 },
                {  41 },
                {  50 },
                {  51 },
                {  52 },
                {  55 },
                {  58 },
                {  16,  45 },
                {   0 },
                {  49, 174,  47, 175,  36,  46 },
                {   0 },
                {  45 },
                {   2, 176,  48 },
                {   0 },
                {  35,  47 },
                {   7 },
                {  15 },
                {  14 },
                {   9 },
                {  19 },
                {   2, 176,  37,  60, 177 },
                {  18,  38,  47, 178,  39 },
                {  10,  38,  53,  39 },
                {  60, 179,  54 },
                {   0 },
                {  35,  53 },
                { 180,  20,  60,  56,  12, 181 },
                {   8, 182,  60, 183,  41, 184,  57 },
                {   0 },
                {  56 },
                {  11, 185,  41,  59 },
                {  23,  60, 186 },
                {  22,  60, 187 },
                {  62,  61 },
                {   0 },
                {  24,  62, 188,  61 },
                {  25,  62, 189,  61 },
                {  63 },
                {  21, 190 },
                {  13, 191 },
                {  26,  62, 192 },
                {  66,  64 },
                {   0 },
                {  65, 193,  66, 194 },
                {  27 },
                {  28 },
                {  29 },
                {  30 },
                {  68,  67 },
                {   0 },
                {  31,  68, 195,  67 },
                {  32,  68, 196,  67 },
                {  70,  69 },
                {   0 },
                {  33,  70, 197,  69 },
                {  34,  70, 198,  69 },
                {   2, 199 },
                {   3, 200 },
                {   4, 201 },
                {   5, 202 },
                {   6, 203 },
                {  38,  60,  39 },
                {  31,  70 },
                {  32,  70, 204 }
        };

    String[] PARSER_ERROR =
        {
            "",
            "esperado EOF", // "Era esperado fim de programa"
            "esperado identificador", // "Era esperado id",
            "esperado constante_int", // "Era esperado cte_int",
            "esperado constante_float", //"Era esperado cte_float",
            "esperado constante_char", // "Era esperado cte_char"
            "esperado constante_string", // "Era esperado cte_string",
            "esperado bool", // "Era esperado pr_bool",
            "esperado case", // "Era esperado pr_case",
            "esperado char", // "Era esperado pr_char",
            "esperado echo", // "Era esperado pr_echo",
            "esperado do", // "Era esperado pr_do",
            "esperado end", // "Era esperado pr_end",
            "esperado false", // "Era esperado pr_false",
            "esperado float", // "Era esperado pr_float",
            "esperado int", // "Era esperado pr_int",
            "esperado local", // "Era esperado pr_local",
            "esperado module", // "Era esperado pr_module",
            "esperado request", // "Era esperado pr_request",
            "esperado string", // "Era esperado pr_string",
            "esperado switch", // "Era esperado pr_switch",
            "esperado true", // "Era esperado pr_true",
            "esperado untirl", // "Era esperado pr_until",
            "esperado while", // "Era esperado pr_while",
            "esperado &", // "Era esperado \"&\"",
            "esperado |", // "Era esperado \"|\"",
            "esperado !", // "Era esperado \"!\"",
            "esperado ==", // "Era esperado \"==\"",
            "esperado !=", // "Era esperado \"!=\"",
            "esperado <", // "Era esperado \"<\"",
            "esperado >", // "Era esperado \">\"",
            "esperado +", // "Era esperado \"+\"",
            "esperado -", // "Era esperado \"-\"",
            "esperado *", // "Era esperado \"*\"",
            "esperado /", // "Era esperado \"/\"",
            "esperado ,", // "Era esperado \",\"",
            "esperado ;", // "Era esperado \";\"",
            "esperado =", // "Era esperado \"=\"",
            "esperado (", // "Era esperado \"(\"",
            "esperado )", // "Era esperado \")\"",
            "esperado module", // "<prog> inv�lido",
            "esperado identificador echo do request switch ", // "<lista_comandos> inv�lido",
            "esperado identificador case echo do end request switch", // "<lista_comandos1> inv�lido",
            "esperado identificador echo do request switch", // "<comando> inv�lido",
            "esperado identificador echo do local request switch", // "<dec_variaveis> inv�lido",
            "esperado bool char float int string ", // "<lista_dec> inv�lido",
            "esperado identificador bool char echo do float int request string switch", // "<lista_dec1> inv�lido",
            "esperado identificador", // "<lista_id> inv�lido",
            "esperado , ; ) ", // "<lista_id1> inv�lido",
            "esperado bool char float int string", // "<tipo> inv�lido",
            "esperado identificador", // "<atribuicao> inv�lido",
            "esperado request", // "<input> inv�lido",
            "esperado echo", // "<output> inv�lido",
            "esperado expressao", // "<lista_expressao> inv�lido",
            "esperado , )", // "<lista_expressao1> inv�lido",
            "esperado switch", // "<selecao> inv�lido",
            "esperado case", // "<case> inv�lido",
            "esperado case end", // "<case1> inv�lido",
            "esperado do", // "<repeticao> inv�lido",
            "esperado until while", // "<repeticao1> inv�lido",
            "esperado expressao", // "<expressao> inv�lido", (id int float char string false true ! + - =)
            "esperado expressao", // "<expressao1> inv�lido",
            "esperado identificador int float char", // "<elemento> inv�lido",
            "esperado expressao", // "<relacional> inv�lido",
            "esperado expressao", // "<relacional1> inv�lido",
            "esperado == != < >", // "<operador_relacional> inv�lido",
            "esperado expressao", // "<aritmetica> inv�lido",
            "esperado expressao", // "<aritmetica1> inv�lido",
            "esperado expressao", // "<termo> inv�lido",
            "esperado expressao", // "<termo1> inv�lido",
            "esperado expressao", // "<fator> inv�lido"
        };
}
