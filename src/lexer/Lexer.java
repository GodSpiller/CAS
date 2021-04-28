package lexer;

import org.jetbrains.annotations.Nullable;
import token.Token;
import token.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Lexer {

    private List<Token> tokens;
    private int index;


    public Lexer(String guard) {
        tokens = new ArrayList<>();
        generateTokens(guard);
    }

    private void generateTokens(String guard) {
        index = 0;
        Token token = nextToken(guard);

        while (token != null) {
            tokens.add(token);
            token = nextToken(guard);
        }
    }

    @Nullable
    private Token nextToken(String guard) {
        for (TokenType type : TokenType.values()) {
            Matcher m = type.getPattern().matcher(guard.substring(index));

            if (m.find()) {
                index += m.end();
                return new Token(type, m.group(0), index);
            }
        }
        return null;
    }

    public List<Token> getFilteredTokens() {

        List<Token> filteredTokens = new ArrayList<>();

        for (Token token : tokens) {
            if (!token.getType().isWhiteSpace()) {
                filteredTokens.add(token);
            }
        }
        return filteredTokens;
    }
}
