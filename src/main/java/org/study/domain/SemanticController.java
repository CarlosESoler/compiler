package org.study.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.gals.SemanticError;
import org.study.gals.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SemanticController {
    private static final Logger logger = LogManager.getLogger(SemanticController.class);
    public static StringBuilder programText;
    private String semanticType = "";
    private final List<Token> identifiers = new ArrayList<>();
    private final Map<String, String> symbolsTable = new HashMap<>();
    private final Stack<String> typeStack = new Stack<>();
    private final Map<String, String> mapSemanticTypes = new HashMap<>() {{
        put("int", "int64");
        put("float", "float64");
        put("string", "string");
        put("char", "char");
        put("bool", "bool");
    }};

    public SemanticController() {
        programText = new StringBuilder();
    }

    // Action 101
    public void generateProgramHeader() {
        programText.append(SemanticRepository.PROGRAM_HEADER);
        logger.info("Cabeçalho do programa gerado com sucesso.");
        logProgramText();
    }

    // Action 102
    public void generateEndProgram() {
        programText.append(SemanticRepository.END_PROGRAM);
        logger.info("Fim do programa gerado com sucesso.");
        logProgramText();
    }


    // Action 103
    public void saveType(String lexeme) {
        this.semanticType = lexeme;
    }

    // Action 105
    public void addIdentifier(Token token) {
        this.identifiers.add(token);
    }

    // Action 104
    public void verifyIdentifiers() throws SemanticError {
        for (Token identifier : identifiers) {
            logToken(identifier);
            if(symbolsTable.containsKey(identifier.getLexeme())) {
                throw new SemanticError("Identificador " + identifier + " já foi declarado.", identifier.getPosition());
            }

            String identifierType = mapSemanticTypes.get(semanticType);
            symbolsTable.put(identifier.getLexeme(), identifierType);
            logger.info("Identificador {} do tipo {} adicionado à tabela de símbolos.", identifier.getLexeme(), identifierType);

            programText.append(SemanticRepository.declareIdentifier(identifier.getLexeme(), identifierType));
        }
        identifiers.clear();
        logProgramText();
    }

    // Action 106
    public void addExpression() throws SemanticError {
        String type = typeStack.pop();

        logger.info("Tipo da expressão: {}", type);
        if(type.equals(mapSemanticTypes.get("int"))) {
            programText.append(SemanticRepository.CONVERT_INT);
        }

        Token identifier = identifiers.get(0);

        if(!symbolsTable.containsKey(identifier.getLexeme())) {
            logger.error("Token {} não foi declarado.", identifier.getLexeme());
            throw new SemanticError("Identificador " + identifier.getLexeme() + " não foi declarado.");
        }

        programText.append(SemanticRepository.loadValueInIdentifier(identifier.getLexeme()));
        identifiers.clear();
        logProgramText();
    }

    // Action 107
    public void addExpressionWithType() throws SemanticError {
        Token identifier = identifiers.get(0);
        logToken(identifier);
        String tokenLexeme = identifier.getLexeme();
        String identifierType = symbolsTable.get(tokenLexeme);

        if(!symbolsTable.containsKey(tokenLexeme)) {
            logger.error("Token {} não foi declarado.", tokenLexeme);
            throw new SemanticError("Identificador " + tokenLexeme + " não foi declarado.");
        }


        List<String> invalidTypes = List.of(mapSemanticTypes.get("char"), mapSemanticTypes.get("bool"));
        if(invalidTypes.contains(identifierType)) {
            logger.error("Tipo do identificador {} é {}, mas esperado int ou float.", tokenLexeme, identifierType);
            throw new SemanticError(tokenLexeme + " inválido para comando de entrada.");
        }

        programText.append(SemanticRepository.READ_LINE);
        programText.append(SemanticRepository.in(identifierType));
        programText.append(SemanticRepository.loadValueInIdentifier(tokenLexeme));
        logProgramText();
        identifiers.clear();
    }

    // Action 108
    public void out() {
        String type = typeStack.pop();
        if(type.equals(mapSemanticTypes.get("int"))) {
            programText.append(SemanticRepository.CONVERT_INT);
        }
        if(type.equals(mapSemanticTypes.get("char"))) {
            type = "string"; // Convert char to string for output
        }

        programText.append(SemanticRepository.out(type));
        logProgramText();
    }

    // Action 119
    public void loadTrue(Token token) {
        String semanticBool = mapSemanticTypes.get("bool");
        typeStack.push(semanticBool);
        programText.append(SemanticRepository.loadTrue(token.getLexeme()));
        logProgramText();
    }

    // Action 120
    public void loadFalse(Token token) {
        String semanticBool = mapSemanticTypes.get("bool");
        typeStack.push(semanticBool);
        programText.append(SemanticRepository.loadFalse(token.getLexeme()));
        logProgramText();
    }

    // Action 121
    public void notExpression(Token token) throws SemanticError {
        logToken(token);
        String type = typeStack.pop();
        if(!type.equals(mapSemanticTypes.get("bool"))) {
            logger.error("Operador NOT só pode ser aplicado a expressões booleanas.");
            throw new SemanticError("Operador NOT só pode ser aplicado a expressões booleanas.");
        }
        programText.append(SemanticRepository.notExpression());
        logProgramText();
    }

    // Action 128
    public void addConstantInExpression(Token token) throws SemanticError {
        logToken(token);
        String tokenLexeme = token.getLexeme();
        if(!symbolsTable.containsKey(tokenLexeme)) {
            logger.error("Identificador {} não foi declarado.", tokenLexeme);
            throw new SemanticError("Identificador " + tokenLexeme + " não foi declarado.");
        }
        String tokenSemanticType = symbolsTable.get(tokenLexeme);
        typeStack.push(tokenSemanticType);
        programText.append("ldloc ").append(tokenLexeme).append(" \n");
        if(tokenSemanticType.equals("int64")) {
            programText.append(SemanticRepository.CONVERT_FLOAT);
        }
        logProgramText();
    }

    // Action 129
    public void loadInt(Token token) {
        String semanticInt = mapSemanticTypes.get("int");
        typeStack.push(semanticInt);
        programText.append(SemanticRepository.loadIntValue(token.getLexeme()));
        logProgramText();
    }

    // Action 130
    public void loadFloat(Token token) {
        String semanticFloat = mapSemanticTypes.get("float");
        typeStack.push(semanticFloat);
        programText.append(SemanticRepository.loadFloatValue(token.getLexeme()));
        logProgramText();
    }

    // Action 131
    public void loadChar(Token token) {
        String semanticChar = mapSemanticTypes.get("char");
        typeStack.push(semanticChar);
        programText.append(SemanticRepository.loadCharValue(token.getLexeme()));
        logProgramText();
    }

    // Action 132
    public void loadString(Token token) {
        String semanticString = mapSemanticTypes.get("string");
        typeStack.push(semanticString);
        programText.append(SemanticRepository.loadStringValue(token.getLexeme()));
        logProgramText();
    }

    // Action 133 - para o operador aritmético unário
    public void unaryOperator() {
    }



    public String parseSemanticError(SemanticError error, String text) {
        String errorMessage = "Erro na linha " + TextUtils.getLineNumber(text, error.getPosition()) + " - " + error.getMessage();
        logger.error(errorMessage);
        return errorMessage;
    }


    public void clearProgramText() {
        programText.setLength(0);
        logger.info("Texto do programa limpo.");
    }
    private void logProgramText() {
        logger.info("Texto do programa atualizado:\n{}", programText);
    }

    private void logToken(Token token) {
        logger.info("Token: {}", token.getLexeme());
    }
}
