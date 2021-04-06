/*
 * The MIT License
 *
 * Copyright 2021 curly.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package wordsearchjava;

import java.util.ArrayList;

/**
 *
 * @author curly
 */
public class Array {
  
  // Create 2d array
  static ArrayList<ArrayList<Character>> create(String array_string, String array_size) {
    // Init variables
    String[] split = array_size.split("x");
    int rows = 0, cols = 0;

    // Try to extract array dimensions from string
    try {
      cols = Integer.valueOf(split[0]);
      rows = Integer.valueOf(split[1]);
    } catch (Exception e) {
      System.out.println("Invalid array dimensions");
    }

    // Init 2d array
    ArrayList<ArrayList<Character>> array_2d = new ArrayList<>();

    // Populate 2d array
    for (int i = 0; i < rows; i++) {
      ArrayList<Character> createtype = new ArrayList<>();
      array_2d.add(createtype); // Init each array in the 2d array

      for (int j = 0; j < cols; j++) {
        int pos = ((i * cols) + j);
        array_2d.get(i).add(array_string.charAt(pos));
      }
    }

    return array_2d;
  }

  // Print 2d array
  static void print(ArrayList<ArrayList<Character>> array) {
    ArrayList<ArrayList<Integer>> remove = new ArrayList<ArrayList<Integer>>();
    print(array, remove);
  }
  static void print(ArrayList<ArrayList<Character>> array, ArrayList<ArrayList<Integer>> remove) {
    int x_int = 0, y_int = 0;
    for (ArrayList<Character> i : array) {
      for (char j : i) {

        boolean skip = false;
        for (ArrayList<Integer> h : remove) {
          if (h.get(0) == x_int && h.get(1) == y_int) {
            skip = true;
          }
        }

        if (Main.variables.invert == true && skip == true) {
          skip = false;
        } else if (Main.variables.invert == true && skip == false) {
          skip = true;
        }

        if (skip == false) {
          System.out.print(j + " ");
        } else {
          System.out.print((". "));
        }

        x_int++;
      }
      y_int++;
      x_int = 0;
      System.out.println();
    }
  }

  // Search through the array for a directional array of characters
  static String search(ArrayList<ArrayList<Character>> array, String genki) {
    int row = 0, col = 0;
    ArrayList<ArrayList<Integer>> remove = new ArrayList<>();

    for (String words : genki.split(",")) {
      col = 0;
      row = 0;

      for (ArrayList<Character> i : array ) {
        for (char x : i) {
          if (x == words.charAt(0)) {

            // Start directional search from this
            for (byte[] direction : Main.variables.directions) {
              ArrayString wordstring = new ArrayString();

              wordstring.init(row, col, direction, words);
              for (ArrayList<Integer> y : wordstring.iterate(array)) {
                remove.add(y);
              }
              
            }
          }
          col++;
        }
        row++;
        col = 0;
      }
    }
    print(array, remove);
    return "unimplemented";
  }

  static class ArrayString {
    String word = "";
    int word_offset = 1;
    int[] last = {-2,-2};
    byte[] direction = {0, 0};
    ArrayList<ArrayList<Integer>> remove = new ArrayList<>();

    void init(int col, int row, byte[] direction, String genki) {
      this.last[0] = col;
      this.last[1] = row;
      this.direction = direction;
      this.word = genki;

      ArrayList<Integer> remove_tmp = new ArrayList<>();
      remove_tmp.add(this.last[1]);
      remove_tmp.add(this.last[0]);
      this.remove.add(remove_tmp);
    }

    ArrayList<ArrayList<Integer>> iterate(ArrayList<ArrayList<Character>> array) {
      if (this.word_offset == this.word.length()) {
        return this.remove;
      }

      char next_character;
      int new_row = this.last[0] + this.direction[0];
      int new_col = this.last[1] + this.direction[1];

      // Get next character from array
      ArrayList<Character> row = new ArrayList<>();
      try {
        row = array.get(new_row);
        next_character = row.get(new_col);
      } catch(IndexOutOfBoundsException e) {
        return new ArrayList<>();
      }

      if (next_character == this.word.charAt(this.word_offset)) { // If next letter is next letter

        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(new_col);
        tmp.add(new_row);

        this.last[0] = new_row;
        this.last[1] = new_col;

        this.remove.add(tmp);
        this.word_offset++;
        this.iterate(array);
      }

      if (this.word_offset == this.word.length()) {
        return this.remove;
      } else {
        return new ArrayList<>();
      }
    }
  }
}
