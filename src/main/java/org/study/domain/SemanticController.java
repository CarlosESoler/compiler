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

    private Stack<String> labelsStack = new Stack<>();
    private int labelCounter = 0;
    private String currentRelationalOperator;

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
            throw new SemanticError("Identificador " + identifier.getLexeme() + " não foi declarado.", identifier.getPosition());
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
            throw new SemanticError("Identificador " + tokenLexeme + " não foi declarado.", identifier.getPosition());
        }


        List<String> invalidTypes = List.of(mapSemanticTypes.get("char"), mapSemanticTypes.get("bool"));
        if(invalidTypes.contains(identifierType)) {
            logger.error("Tipo do identificador {} é {}, mas esperado int ou float.", tokenLexeme, identifierType);
            throw new SemanticError(tokenLexeme + " inválido para comando de entrada.", identifier.getPosition());
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

    // Action 117
    public void handleAction117() {
        String type2 = typeStack.pop();
        String type1 = typeStack.pop();

        typeStack.push("bool");

        programText.append("and").append("\n");
    }

    // Action 118
    public void handleAction118() {
        String type2 = typeStack.pop();
        String type1 = typeStack.pop();

        typeStack.push("bool");

        programText.append("or").append("\n");
    }

    // Action 119
    public void loadTrue() {
        String semanticBool = mapSemanticTypes.get("bool");
        typeStack.push(semanticBool);
        programText.append(SemanticRepository.loadTrue());
        logProgramText();
    }

    // Action 120
    public void loadFalse() {
        String semanticBool = mapSemanticTypes.get("bool");
        typeStack.push(semanticBool);
        programText.append(SemanticRepository.loadFalse());
        logProgramText();
    }

    // Action 121
    public void notExpression() {
        String type = typeStack.pop();
        typeStack.push("bool");
        programText.append("ldc.i4.1\n");
        programText.append("xor\n");
    }

    // Action 128
    public void addConstantInExpression(Token token) throws SemanticError {
        logToken(token);
        String tokenLexeme = token.getLexeme();
        if(!symbolsTable.containsKey(tokenLexeme)) {
            logger.error("Identificador {} não foi declarado.", tokenLexeme);
            throw new SemanticError(tokenLexeme + " não foi declarado.", token.getPosition());
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

    private String generateNewLabel() {
        return "L" + (labelCounter++);
    }

    public void handleAction109() {
        String endSwitchLabel = generateNewLabel();
        labelsStack.push(endSwitchLabel);
    }

    public void handleAction110() {
        programText.append("pop\n");
        String endSwitchLabel = labelsStack.pop();
        programText.append(endSwitchLabel).append(":\n");
    }

    public void handleAction111() {
        programText.append("dup\n");
    }

    public void handleAction112() {
        programText.append("ceq\n");
        String nextCaseOrEndLabel = generateNewLabel();
        labelsStack.push(nextCaseOrEndLabel);
        programText.append("brfalse ").append(nextCaseOrEndLabel).append("\n");
    }

    public void handleAction113() {
        programText.append("pop\n");
        String nextCaseOrEndLabel = labelsStack.pop();
        String endSwitchLabel = labelsStack.pop();
        programText.append("br ").append(endSwitchLabel).append("\n");
        programText.append(nextCaseOrEndLabel).append(":\n");
        labelsStack.push(endSwitchLabel);
    }

    public void handleAction114() {
        String loopStartLabel = generateNewLabel();
        programText.append(loopStartLabel).append(":\n");
        labelsStack.push(loopStartLabel);
    }

    public void handleAction115() {
        String loopStartLabel = labelsStack.pop();
        programText.append("brtrue ").append(loopStartLabel).append("\n");
    }

    public void handleAction116() {
        String loopStartLabel = labelsStack.pop();
        programText.append("brfalse ").append(loopStartLabel).append("\n");
    }

    public void unaryOperator() {
        programText.append("neg \n");
        logProgramText();
    }

    public void handleAction124() {
        processBinaryArithmetic("add");
    }

    public void handleAction125() {
        processBinaryArithmetic("sub");
    }

    public void handleAction126() {
        processBinaryArithmetic("mul");
    }

    public void handleAction127() {
        processBinaryArithmetic("div");
    }

    public void processBinaryArithmetic(String operator) {
        String type2 = typeStack.pop();
        String type1 = typeStack.pop();

        String resultType = getResultingArithmeticType(type1, type2, operator);
        typeStack.push(resultType);
        programText.append(operator).append("\n");
    }

    private String getResultingArithmeticType(String type1, String type2, String operator) {
        // If any operand is float64, the result is float64 (type promotion).
        if (type1.equals("float64") || type2.equals("float64")) {
            return "float64";
        }
        if (operator.equals("div")) {
            return "float64";
        }
        return "int64";
    }

    public void storeRelationalOperator(Token token) {
        this.currentRelationalOperator = token.getLexeme();
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

    public void handleAction123() {
        String type2 = typeStack.pop();
        String type1 = typeStack.pop();

        typeStack.push("bool");

        switch (currentRelationalOperator) {
            case "=":
                programText.append("ceq\n");
                break;
            case "!":
                programText.append("ceq\n");
                programText.append("ldc.i4.1\n");
                programText.append("xor\n");
                break;
            case "<":
                programText.append("clt\n");
                break;
            case ">":
                programText.append("cgt\n");
                break;
        }
        currentRelationalOperator = "";
    }
}
