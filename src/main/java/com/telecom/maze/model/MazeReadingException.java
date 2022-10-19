package com.telecom.maze.model;

import java.io.IOException;

public class MazeReadingException extends IOException {
    public MazeReadingException(String message, String fileName, int errorLine) {
        super(String.format("[%s][%d] erreur: %s", fileName, errorLine, message));
    }
}
