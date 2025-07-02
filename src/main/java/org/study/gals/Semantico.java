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

            case 119 -> semanticController.loadTrue(token);

            case 120 -> semanticController.loadFalse(token);

            case 121 -> semanticController.notExpression(token);

            case 128 -> semanticController.addConstantInExpression(token);

            case 129 -> semanticController.loadInt(token);

            case 130 -> semanticController.loadFloat(token);

            case 131 -> semanticController.loadChar(token);

            case 132 -> semanticController.loadString(token);

            default -> throw new SemanticError("Ação desconhecida: " + action, token.getPosition());
        }
    }	
}
