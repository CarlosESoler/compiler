package org.study.domain;

import org.study.gals.SyntaticError;
import org.study.gals.Token;

public class SyntaticController {
    private final String text;
    private Token token;

    public SyntaticController(String text) {
        this.text = text;
    }

    public String parseSyntaticError(SyntaticError e) {
        int errorLine = TextUtils.getLineNumber(getText(), e.getPosition());
        StringBuilder message = new StringBuilder("Linha " + errorLine + ": ");
        String errorMessage = e.getMessage();

        message.append("encontrado ").append(token.getLexeme()).append(" ").append(generateSyntaticMessage(errorMessage, errorLine));

        return message.toString();
    }

    private String generateSyntaticMessage(String errorMessage, int errorLine) {
        return "\n" + String.format("%" + (errorLine + errorMessage.length()) + "s", errorMessage);
    }

    public String getText() {
        return text;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
