package cc.ayakurayuki.repo.multivaluereturns;

import java.util.Map;
import java.util.Objects;

/**
 * A mutable pair consisting of two elements.
 *
 * @param <L> the left element type
 * @param <R> the right element type
 *
 * @author Ayakura Yuki
 */
public class MutPair<L, R> extends P<L, R> {

  /**
   * Serialization version
   */
  private static final long serialVersionUID = -8362996534372286349L;

  /**
   * An empty array.
   *
   * <p>Consider using {@link #emptyArray()} to avoid generics warnings.</p>
   */
  public static final MutPair<?, ?>[] EMPTY_ARRAY = {};

  /**
   * Returns the empty array singleton that can be assigned without compiler warning.
   *
   * @return the empty array singleton that can be assigned without compiler warning.
   */
  @SuppressWarnings("unchecked")
  public static <L, R> MutPair<L, R>[] emptyArray() {
    return (MutPair<L, R>[]) EMPTY_ARRAY;
  }

  /**
   * Creates a mutable pair of two objects inferring the generic types.
   *
   * @param left  the left element, can be null
   * @param right the right element, can be null
   *
   * @return a pair formed from the two parameters, not null
   */
  public static <L, R> MutPair<L, R> of(final L left, final R right) {
    return new MutPair<>(left, right);
  }

  /**
   * Creates a mutable pair from a map entry.
   *
   * @param pair the existing map entry
   *
   * @return a pair formed from the map entry
   */
  public static <L, R> MutPair<L, R> of(final Map.Entry<L, R> pair) {
    final L left;
    final R right;
    if (pair != null) {
      left = pair.getKey();
      right = pair.getValue();
    } else {
      left = null;
      right = null;
    }
    return of(left, right);
  }

  /**
   * Creates a mutable pair of two non-null objects inferring the generic types.
   *
   * @param left  the left element, cannot be null
   * @param right the right element, cannot be null
   *
   * @return a pair formed from the two parameters, not null
   *
   * @throws NullPointerException if any parameter is null
   */
  public static <L, R> MutPair<L, R> ofNonNull(final L left, final R right) {
    return of(
        Objects.requireNonNull(left),
        Objects.requireNonNull(right)
    );
  }

  /**
   * Left object
   */
  public L left;

  /**
   * Right object
   */
  public R right;

  /**
   * Create a new pair instance of two nulls.
   */
  public MutPair() {}

  /**
   * Create a new pair instance
   *
   * @param left  the left value, can be null
   * @param right the right value, can be null
   */
  public MutPair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public L getLeft() {
    return left;
  }

  @Override
  public R getRight() {
    return right;
  }

  public void setLeft(final L left) {
    this.left = left;
  }

  public void setRight(final R right) {
    this.right = right;
  }

  /**
   * Sets the {@code Map.Entry} value.
   * This sets the right element of the pair.
   *
   * @param value the right value to set, not null
   *
   * @return the old value for the right element
   */
  @Override
  public R setValue(final R value) {
    final R result = getRight();
    setRight(value);
    return result;
  }

}
