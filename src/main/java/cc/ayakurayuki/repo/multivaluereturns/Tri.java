package cc.ayakurayuki.repo.multivaluereturns;

import java.io.Serializable;
import java.util.Objects;

/**
 * Tri is a declaration of both immutable and mutable triple, consisting of three elements.
 *
 * <p>
 * This class is an abstract implementation defining the basic API.
 * It refers to the elements as 'left', 'middle' and 'right'.
 * </p>
 *
 * <p>
 * Subclass implementations may be mutable or immutable.
 * However, there is no restriction on the type of the stored objects that may be stored.
 * If mutable objects are stored in the pair, then the pair itself effectively becomes mutable.
 * </p>
 */
abstract class Tri<L, M, R> implements Comparable<Tri<L, M, R>>, Serializable {

  /**
   * Serialization version
   */
  private static final long serialVersionUID = 9121943858153355849L;

  public static final Tri<?, ?, ?>[] EMPTY_ARRAY = {};

  @SuppressWarnings("unchecked")
  public static <L, M, R> Tri<L, M, R>[] emptyArray() {
    return (Tri<L, M, R>[]) EMPTY_ARRAY;
  }

  /**
   * Gets the left element from this triple.
   *
   * @return the left element, may be null
   */
  public abstract L getLeft();

  /**
   * Gets the middle element from this triple.
   *
   * @return the middle element, may be null
   */
  public abstract M getMiddle();

  /**
   * Gets the right element from this triple.
   *
   * @return the right element, may be null
   */
  public abstract R getRight();

  @Override
  public int compareTo(Tri<L, M, R> other) {
    return new CompareToBuilder()
        .append(getLeft(), other.getLeft())
        .append(getMiddle(), other.getMiddle())
        .append(getRight(), other.getRight())
        .toComparison();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Tri<?, ?, ?>) {
      final Tri<?, ?, ?> other = (Tri<?, ?, ?>) obj;
      return Objects.equals(getLeft(), other.getLeft())
          && Objects.equals(getMiddle(), other.getMiddle())
          && Objects.equals(getRight(), other.getRight());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getLeft()) ^ Objects.hashCode(getMiddle()) ^ Objects.hashCode(getRight());
  }

  @Override
  public String toString() {
    return "(" + getLeft() + "," + getMiddle() + "," + getRight() + ")";
  }

  public String toString(final String format) {
    return String.format(format, getLeft(), getMiddle(), getRight());
  }

}
