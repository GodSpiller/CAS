package token;

import java.util.List;

public class TokenStream {

    private List<Token> tokens;

    private int index = 0;

    public TokenStream(List<Token> tokens) {
        this.tokens = tokens;
    }

    public boolean peek(TokenType type) {
        return (tokens.get(index).getType().equals(type));
    }

    public Token expect(TokenType tt){

        Token t = advance();

        return t;
    }

    public Token advance() {
        return tokens.get(index < tokens.size() - 1 ? index++ : index);
    }

    public Token getPrevious() {
        return tokens.get(index - 1);
    }

    public TokenType getType(){
        return tokens.get(index).getType();
    }

    public String getValue() {
        return tokens.get(index).getValue();
    }

    public Token getToken() {
        return tokens.get(index);
    }
}