package com.example.cruisecompany.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class MyTag extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(MyTag.class);
    String myTagString;


    @Override
    public int doStartTag() {
        myTagString = makeMyTag();
        try {
            JspWriter out = pageContext.getOut();
            out.write(myTagString);
        } catch (IOException e) {
            LOGGER.error("Error from MyTag  - ", e);
        }
        return SKIP_BODY;
    }


    private String makeMyTag() {
        StringBuilder myTag = new StringBuilder();
        myTag.append("Admin (login: admin, password: admin) Users (password: pass) ©webmaster");
        return myTag.toString();
    }
}
