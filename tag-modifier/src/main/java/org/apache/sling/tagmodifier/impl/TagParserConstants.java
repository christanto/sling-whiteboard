/* Generated By:JavaCC: Do not edit this line. TagParserConstants.java */
package org.apache.sling.tagmodifier.impl;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface TagParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SPACE = 1;
  /** RegularExpression Id. */
  int ATTR_IDENTIFIER = 2;
  /** RegularExpression Id. */
  int NAME_IDENTIFIER = 3;
  /** RegularExpression Id. */
  int TAG_END = 4;
  /** RegularExpression Id. */
  int TAG_SLASHEND = 5;
  /** RegularExpression Id. */
  int TAG_START = 6;
  /** RegularExpression Id. */
  int ENDTAG_START = 7;
  /** RegularExpression Id. */
  int COMMENT_START = 8;
  /** RegularExpression Id. */
  int DECL_START = 9;
  /** RegularExpression Id. */
  int RAWTEXT = 10;
  /** RegularExpression Id. */
  int TAG_NAME = 11;
  /** RegularExpression Id. */
  int LST_ERROR = 12;
  /** RegularExpression Id. */
  int ATTR_NAME = 14;
  /** RegularExpression Id. */
  int END_OF_TAG = 15;
  /** RegularExpression Id. */
  int ATTR_EQ = 16;
  /** RegularExpression Id. */
  int LIT_ERROR = 17;
  /** RegularExpression Id. */
  int ATTR_VAL = 18;
  /** RegularExpression Id. */
  int ERROR = 19;
  /** RegularExpression Id. */
  int STRING = 20;
  /** RegularExpression Id. */
  int SQUOTE = 21;
  /** RegularExpression Id. */
  int DQUOTE = 22;
  /** RegularExpression Id. */
  int COMMENT_END = 23;
  /** RegularExpression Id. */
  int COMMENT_WORD = 24;
  /** RegularExpression Id. */
  int DECL_TAG = 25;
  /** RegularExpression Id. */
  int DECL_ATTR = 26;
  /** RegularExpression Id. */
  int DECL_END = 27;

  /** Lexical state. */
  int DECLARATION = 0;
  /** Lexical state. */
  int COMMENT = 1;
  /** Lexical state. */
  int IN_DECLARATION = 2;
  /** Lexical state. */
  int ATTR_VALUE = 3;
  /** Lexical state. */
  int IN_TAG = 4;
  /** Lexical state. */
  int START_TAG = 5;
  /** Lexical state. */
  int DEFAULT = 6;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "<SPACE>",
    "<ATTR_IDENTIFIER>",
    "<NAME_IDENTIFIER>",
    "\">\"",
    "\"/>\"",
    "\"<\"",
    "\"</\"",
    "\"<!--\"",
    "\"<!\"",
    "<RAWTEXT>",
    "<TAG_NAME>",
    "<LST_ERROR>",
    "<token of kind 13>",
    "<ATTR_NAME>",
    "<END_OF_TAG>",
    "\"=\"",
    "<LIT_ERROR>",
    "<ATTR_VAL>",
    "<ERROR>",
    "<STRING>",
    "<SQUOTE>",
    "<DQUOTE>",
    "\"-->\"",
    "<COMMENT_WORD>",
    "<DECL_TAG>",
    "<DECL_ATTR>",
    "<DECL_END>",
  };

}
