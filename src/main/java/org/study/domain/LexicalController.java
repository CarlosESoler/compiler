package org.study.domain;

import org.study.gals.LexicalError;
import org.study.gals.Lexico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record LexicalController(String text) {

    public void generateLexicalAnalysis() throws LexicalError {
        Lexico lexico = new Lexico(text());
        while ((lexico.nextToken()) != null) {
        }
    }

    public String parseLexemeError(LexicalError e) {
        StringBuilder message = new StringBuilder("Linha " + TextUtils.getLineNumber(text(), e.getPosition()) + ": ");
        String getLexemeError = getLexemeError(e.getPosition());
        String errorMessage = e.getMessage();
        if (needLexeme(e.getMessage())) {
            message.append(getLexemeError).append(" ").append(errorMessage);
        } else {
            message.append(errorMessage);
        }

        return message.toString();
    }

    private boolean needLexeme(String errorMessage) {
        return !(errorMessage.contains("constante_string") || errorMessage.contains("bloco") || errorMessage.contains("constate_char"));
    }

    private String getLexemeError(int position) {
        String lexemeError = text().substring(position);
        Pattern lineEnd = Pattern.compile("\r\n|\n|\r| ");
        Matcher searchLineEnd = lineEnd.matcher(lexemeError);

        if (searchLineEnd.find()) {
            lexemeError = lexemeError.substring(0, searchLineEnd.start());
        }

        return lexemeError;
    }
}
