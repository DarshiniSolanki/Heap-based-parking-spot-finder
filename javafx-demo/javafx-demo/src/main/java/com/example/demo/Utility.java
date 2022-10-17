package com.example.demo;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utility {

    public static String getExceptionAsString(Exception exception) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter( writer );
        exception.printStackTrace( printWriter );
        printWriter.flush();
        return writer.toString();
    }
}
