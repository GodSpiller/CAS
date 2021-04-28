package ast;

import java.util.*;
import token.Token;

public abstract class ASTNode {

    private List<ASTNode> children;

    protected Token token;

    public ASTNode(Token token) {
        this.token = token;
        children = new ArrayList<>();
    }

    public void addChild(ASTNode child) {
        children.add(child);
    }

    public String getValue() {
        return token.getValue();
    }

    public String getType() { return token.getType().toString(); }

    public List<ASTNode> getChildren() {
        return children;
    }

    public ASTNode getChild(int index) {
        return children.get(index);
    }

    public ASTNode getLeft() {
        return children.get(0);
    }

    public ASTNode getRight() {
        return children.get(1);
    }

    public Token getToken() {
        return token;
    }

    public abstract Object accept(ASTVisitor visitor);
}