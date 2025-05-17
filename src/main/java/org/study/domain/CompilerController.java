package org.study.domain;

import org.study.gals.*;

public class CompilerController {
    private final Lexico lexico = new Lexico();
    private final Sintatico sintatico = new Sintatico();
    private final Semantico semantico = new Semantico();

    public String compile(String text) {
        LexicalController lexicalController = new LexicalController(text);
        SyntaticController syntaticController = new SyntaticController(text);

        lexico.setInput(text);
        try {
            lexicalController.generateLexicalAnalysis();
            sintatico.parse(lexico, semantico);
        } catch (LexicalError e) {
            return lexicalController.parseLexemeError(e);
        } catch (SyntaticError e) {
            return syntaticController.parseSyntaticError(e, sintatico.getCurrentToken());
        } catch (SemanticError e) {
            return "Erro semântico: " + e.getMessage();
        }
        return "Programa compilado com sucesso";
    }
}
