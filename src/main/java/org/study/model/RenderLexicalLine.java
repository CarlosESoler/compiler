package org.study.model;

public class RenderLexicalLine {

    private int line;
    private String clazz;
    private String lexeme;

    public RenderLexicalLine(int line, String clazz, String lexeme) {
        this.line = line;
        this.clazz = clazz;
        this.lexeme = lexeme;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return line + " " + clazz + " " + lexeme;
    }
}
