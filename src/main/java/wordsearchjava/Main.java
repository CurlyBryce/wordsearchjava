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

/**
 *
 * @author curly
 */
public class Main {

  // Variables
  final static class variables {
    static boolean invert = false;
    static String version = "0.1",
    array_size = "13x11",
    array_string = "ysjzdesdikdzjlrcanuxddnyqaqwvadxrnakhlpdxyaenalassbanunytlrvaxeangdkeieomdqvpmmtbzzdagqkieeriatlhkjmtdlxtwioaitiahliiswmbpanamaahcsaleuzenevmco";
    static byte[][] directions = {{0,1},{1,0},{1,1},{0,-1},{-1,0},{-1,-1},{-1,1},{1,-1}};
  }

  // Parse runtime options
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage:\nwordsearch-java <flags> [comma separated words to search]\n\nDescription:\nA default wordsearch of 13x11 containing countries in lowercase is used if -s and -w are not used\n\nFlags:\n-i: invert printed array\n-s: array size \"-s 12x4\" (12x4)\n-w wordsearch string \"-w abcd\"");      return;
    }

    int count = 0;
    for (String i : args) { // For every argument
      switch(i) {
        case "-i":
          variables.invert = true;
          break;
        case "-s":
          variables.array_size = args[count + 1];
          args[count + 1] = "--";
          break;
        case "-w":
          variables.array_string = args[count + 1];
          args[count + 1] = "--";
          break;
        case "--":
          break;
        default:
          Array.search(Array.create(variables.array_string, variables.array_size), args[count]);
          break;
      }
      count++;
    }
  }
  
}
