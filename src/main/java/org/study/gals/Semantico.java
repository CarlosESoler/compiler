package org.study.gals;

import org.study.domain.SemanticController;

public class Semantico implements Constants
{
    final SemanticController semanticController = new SemanticController();
    public void executeAction(int action, Token token)	throws SemanticError {

        System.out.println("A��o #"+action+", Token: "+token);

        switch (action) {
            case 101 -> semanticController.generateProgramHeader();

            case 102 -> semanticController.generateEndProgram();

            case 103 -> semanticController.saveType(token.getLexeme());

            case 104 -> semanticController.verifyIdentifiers();

            case 105 -> semanticController.addIdentifier(token);

            case 106 -> semanticController.addExpression();

            case 107 -> semanticController.addExpressionWithType();

            case 108 -> semanticController.out();

            case 109 -> semanticController.handleAction109();

            case 110 -> semanticController.handleAction110();

            case 111 -> semanticController.handleAction111();

            case 112 -> semanticController.handleAction112();

            case 113 -> semanticController.handleAction113();

            case 114 -> semanticController.handleAction114();

            case 115 -> semanticController.handleAction115();

            case 116 -> semanticController.handleAction116();

            case 117 -> semanticController.handleAction117();

            case 118 -> semanticController.handleAction118();

            case 119 -> semanticController.loadTrue();

            case 120 -> semanticController.loadFalse();

            case 121 -> semanticController.notExpression();

            case 122 -> semanticController.storeRelationalOperator(token);

            case 123 -> semanticController.handleAction123();

            case 124 -> semanticController.handleAction124();

            case 125 -> semanticController.handleAction125();

            case 126 -> semanticController.handleAction126();

            case 127 -> semanticController.handleAction127();

            case 128 -> semanticController.addConstantInExpression(token);

            case 129 -> semanticController.loadInt(token);

            case 130 -> semanticController.loadFloat(token);

            case 131 -> semanticController.loadChar(token);

            case 132 -> semanticController.loadString(token);

            default -> throw new SemanticError("Ação desconhecida: " + action, token.getPosition());
        }
    }
}
