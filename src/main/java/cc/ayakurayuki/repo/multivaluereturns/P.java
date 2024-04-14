package cc.ayakurayuki.repo.multivaluereturns;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * P is a declaration of both immutable and mutable pair.
 *
 * <p>
 * This class is an abstract implementation defining the basic API.
 * It refers to the elements as 'left' and 'right'. It also implements the
 * {@code Map.Entry} interface where the key is 'left' and the value is 'right'.
 * </p>
 *
 * <p>
 * Subclass implementations may be mutable or immutable.
 * However, there is no restriction on the type of the stored objects that may be stored.
 * If mutable objects are stored in the pair, then the pair itself effectively becomes mutable.
 * </p>
 */
abstract class P<L, R> implements Map.Entry<L, R>, Comparable<P<L, R>>, Serializable {

  private static final long serialVersionUID = -8121276832502138200L;

  public static final P<?, ?>[] EMPTY_ARRAY = {};

  @SuppressWarnings("unchecked")
  public static <L, R> P<L, R>[] emptyArray() {
    return (P<L, R>[]) EMPTY_ARRAY;
  }

  /**
   * Gets the left element from this pair.
   *
   * <p>When treated as a key-value pair, this is the key.</p>
   *
   * @return the left element, could be null
   */
  public abstract L getLeft();

  /**
   * Gets the right element from this pair.
   *
   * <p>When treated as a key-value pair, this is the value.</p>
   *
   * @return the right element, could be null
   */
  public abstract R getRight();

  /**
   * Gets the key from this pair.
   *
   * <p>
   * This method implements the {@code Map.Entry} interface returning the
   * left element as the key.
   * </p>
   *
   * @return the left element as the key, may be null
   */
  @Override
  public L getKey() {
    return getLeft();
  }

  /**
   * Gets the value from this pair.
   *
   * <p>
   * This method implements the {@code Map.Entry} interface returning the
   * right element as the value.
   * </p>
   *
   * @return the right element as the value, may be null
   */
  @Override
  public R getValue() {
    return getRight();
  }

  @Override
  public int compareTo(P<L, R> other) {
    return new CompareToBuilder()
        .append(getLeft(), other.getLeft())
        .append(getRight(), other.getRight())
        .toComparison();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Map.Entry<?, ?>) {
      final Map.Entry<?, ?> other = (Map.Entry<?, ?>) obj;
      return Objects.equals(getKey(), other.getKey())
          && Objects.equals(getValue(), other.getValue());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
  }

  @Override
  public String toString() {
    return "(" + getLeft() + ", " + getRight() + ")";
  }

  public String toString(final String format) {
    return String.format(format, getLeft(), getRight());
  }

}
