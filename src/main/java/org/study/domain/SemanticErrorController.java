package org.study.domain;

import org.study.gals.SemanticError;

public record SemanticErrorController(String text) {

    public String parseSemanticError(SemanticError e) {
        return "Erro na linha " + TextUtils.getLineNumber(text, e.getPosition()) + " - " + e.getMessage();
    }
}
