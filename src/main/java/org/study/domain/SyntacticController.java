package org.study.domain;

import org.study.gals.SyntaticError;
import org.study.gals.Token;

public record SyntacticController(String text) {

    public String parseSyntacticError(SyntaticError e, Token token) {
        int errorLine = TextUtils.getLineNumber(text(), e.getPosition());
        StringBuilder message = new StringBuilder("Linha " + errorLine + ": ");
        String errorMessage = e.getMessage();
        String tokenLexeme = token.getLexeme().equals("$") ? "EOF" : token.getLexeme();
        String lexicalClass = token.getId() == 6 ? "constante_string" : tokenLexeme;

        message.append("encontrado ").append(lexicalClass).append(" ").append(generateSyntasticMessage(errorMessage, errorLine));

        return message.toString();
    }

    private String generateSyntasticMessage(String errorMessage, int errorLine) {
        return "\n" + String.format("%" + (errorLine + errorMessage.length()) + "s", errorMessage);
    }
}
