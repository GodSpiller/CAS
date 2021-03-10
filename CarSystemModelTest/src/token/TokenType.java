package token;

import java.util.regex.*;

public enum TokenType {

    WHITE_SPACE("^ "),

    LT_EQUAL("^\\<="),

    GT_EQUAL("^\\>="),

    EQUAL("^\\=="),

    NOT_EQUAL("^\\!="),

    LESS_THAN("^\\<"),

    GREATER_THAN("^\\>"),

    AND("^\\&&"),

    OR("^\\|\\|"),

    NUMBER("\\b(^[1-9][0-9]*[\\.][0-9]+|^[0]+[\\.][0-9]+|^[1-9][0-9]*|^[0])\\b"),

    VARIABLE("^\\w+");

    private final Pattern pattern;

    TokenType(final String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    public boolean isWhiteSpace() {
        return this == WHITE_SPACE;
    }

}
