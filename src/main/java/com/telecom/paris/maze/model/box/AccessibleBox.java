package com.telecom.paris.maze.model.box;

/**
 * This interface permits to indicate that a box I is not isolated from the maze (not a wall). In other words, if we are
 * located in one of the boxes that surround the box I (up, down, left, right), then we can reach the box I.
 */
public interface AccessibleBox {
}
