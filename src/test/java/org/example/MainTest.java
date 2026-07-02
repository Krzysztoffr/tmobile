package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void mainPrintsWelcomeMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));
            Main.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        String text = output.toString();
        assertTrue(text.contains("Hello and welcome!"));
    }
}
