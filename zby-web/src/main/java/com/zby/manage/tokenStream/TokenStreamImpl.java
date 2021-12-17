package com.zby.manage.tokenStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TokenStreamImpl implements TokenStream{
    private InputStreamReader inputStream;
    List<Token> tokenList = new ArrayList<>();
    @Override
    public Token getToken() throws IOException {
        int read = inputStream.read();
        if (read == '(') return new Token(Token.TokenType.LPAR,'(');
        if (read == ')') return new Token(Token.TokenType.RPAR,')');
        if (read == '+') return new Token(Token.TokenType.PLUS,'+');
        if (read == '-') return new Token(Token.TokenType.MINUS,'-');
        if (read == '*') return new Token(Token.TokenType.MULT,'*');
        if (read == '/') return new Token(Token.TokenType.DIV,'/');
        if (  read >= '0' && read <= '9') return new Token(Token.TokenType.INT,(char)read);
        return new Token(Token.TokenType.NONE,(char)read);
    }

    @Override
    public void consumeToken() {

    }

    public TokenStreamImpl(InputStream inputStream) {
        this.inputStream = new InputStreamReader(inputStream);
    }

    public static void main(String[] args) throws IOException {
        TokenStream ts = new TokenStreamImpl(System.in); // 标准输入的适配器
        while (ts.getToken().tokenType != Token.TokenType.NONE) {ts.consumeToken();}
    }
}
