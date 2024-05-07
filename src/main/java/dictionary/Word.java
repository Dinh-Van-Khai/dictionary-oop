package dictionary;

import java.util.Objects;

public class Word {

  private String word_target;
  private String word_explain;

  /**
   * Constructor initialized with 2 parameters.
   *
   * @param word_target  eng word
   * @param word_explain vn word
   */
  public Word(String word_target, String word_explain) {
    word_target = word_target.toLowerCase();
    this.word_explain = word_explain;
    this.word_target = word_target;
  }

  /**
   * Constructor initialized without parameters.
   */
  public Word() {
    this.word_target = "Duc";
    this.word_explain = "handsome";
  }

  /**
   * Getter method for vn word.
   *
   * @return word_explain
   */
  public String getWord_explain() {
    return word_explain;
  }

  /**
   * Getter method for vn word.
   *
   * @return word_target
   */
  public String getWord_target() {
    return word_target;
  }

  /**
   * Setter method for eng word.
   *
   * @param word_target eng word
   */
  public void setWord_target(String word_target) {
    this.word_target = word_target;
  }

  /**
   * Setter method for vn word.
   *
   * @param word_explain vn word
   */
  public void setWord_explain(String word_explain) {
    this.word_explain = word_explain;
  }

  /**
   * toString.
   *
   * @return string has combo target and explain
   */
  public String toString() {
    return this.word_target + "\t" + this.word_explain;
  }

  /**
   * Check if the obj are equal (first bring them to the same class).
   *
   * @param obj Other Object needs to check
   * @return true/false
   */
  public boolean equal(Object obj) {
    if (!(obj instanceof Word)) {
      return false;
    } else {
      return this.word_explain.equals(((Word) obj).word_explain) && this.word_target.equals(((Word) obj).word_target);
    }
  }

  /**
   * Override for hashCode in class Objects.
   *
   * @return hash(target, explain)
   */
  public int hashCode(){
    return Objects.hash(this.word_target, this.word_explain);
  }
}
