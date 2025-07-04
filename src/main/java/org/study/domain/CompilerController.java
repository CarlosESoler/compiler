package org.study.domain;

import org.study.gals.*;

public class CompilerController {
    Lexico lexico;
    Sintatico sintatico;
    Semantico semantico;

    public String compile(String text) {
        lexico = new Lexico();
        sintatico = new Sintatico();
        semantico = new Semantico();
        final LexicalController lexicalController = new LexicalController(text);
        final SyntacticController syntacticController = new SyntacticController(text);
        final SemanticErrorController semanticController = new SemanticErrorController(text);

        lexico.setInput(text);

        try {
            lexicalController.generateLexicalAnalysis();
            sintatico.parse(lexico, semantico);
        } catch (LexicalError e) {
            return lexicalController.parseLexemeError(e);
        } catch (SyntaticError e) {
            return syntacticController.parseSyntacticError(e, sintatico.getCurrentToken());
        } catch (SemanticError e) {
            return semanticController.parseSemanticError(e);
        }
        return "Programa compilado com sucesso";
    }
}
