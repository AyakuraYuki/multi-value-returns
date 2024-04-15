package cc.ayakurayuki.repo.multivaluereturns;

import java.util.Objects;

/**
 * An immutable triple consisting of three elements.
 *
 * @param <L> the left element type
 * @param <M> the middle element type
 * @param <R> the right element type
 *
 * @author Ayakura Yuki
 */
public class Triple<L, M, R> extends Tri<L, M, R> {

  /**
   * Serialization version
   */
  private static final long serialVersionUID = 3111910262598217942L;

  /**
   * An immutable triple of nulls.
   */
  @SuppressWarnings("rawtypes")
  public static final Triple NULL = new Triple<>(null, null, null);

  /**
   * Returns an immutable triple of nulls.
   *
   * @return an immutable triple of nulls.
   */
  @SuppressWarnings("unchecked")
  public static <L, M, R> Triple<L, M, R> nullTriple() {
    return (Triple<L, M, R>) NULL;
  }

  /**
   * An empty array.
   *
   * <p>Consider using {@link #emptyArray()} to avoid generics warnings.</p>
   */
  public static final Triple<?, ?, ?>[] EMPTY_ARRAY = {};

  /**
   * Returns the empty array singleton that can be assigned without compiler warning.
   *
   * @return the empty array singleton that can be assigned without compiler warning.
   */
  @SuppressWarnings("unchecked")
  public static <L, M, R> Triple<L, M, R>[] emptyArray() {
    return (Triple<L, M, R>[]) EMPTY_ARRAY;
  }

  /**
   * Creates an immutable triple of only left element.
   *
   * @param left the left element, can be null
   *
   * @return a triple formed from the only left element, not null
   */
  public static <L, M, R> Triple<L, M, R> left(final L left) {
    return of(left, null, null);
  }

  /**
   * Creates an immutable triple of only middle element.
   *
   * @param middle the middle element, can be null
   *
   * @return a triple formed from the only middle element, not null
   */
  public static <L, M, R> Triple<L, M, R> middle(final M middle) {
    return of(null, middle, null);
  }

  /**
   * Creates an immutable triple of only right element.
   *
   * @param right the right element, can be null
   *
   * @return a triple formed from the only right element, not null
   */
  public static <L, M, R> Triple<L, M, R> right(final R right) {
    return of(null, null, right);
  }

  /**
   * Creates an immutable triple from three objects inferring the generic types.
   *
   * @param left   the left element, can be null
   * @param middle the middle element, can be null
   * @param right  the right element, can be null
   *
   * @return a triple formed from the three parameters, not null
   */
  public static <L, M, R> Triple<L, M, R> of(final L left, final M middle, final R right) {
    return left != null || middle != null || right != null ? new Triple<>(left, middle, right) : nullTriple();
  }

  /**
   * Creates an immutable triple from three non-null objects inferring the generic types.
   *
   * @param left   the left element, cannot be null
   * @param middle the middle element, cannot be null
   * @param right  the right element, cannot be null
   *
   * @return a triple formed from the three parameters, not null
   *
   * @throws NullPointerException if any parameter is null
   */
  public static <L, M, R> Triple<L, M, R> ofNonNull(final L left, final M middle, final R right) {
    return of(
        Objects.requireNonNull(left),
        Objects.requireNonNull(middle),
        Objects.requireNonNull(right)
    );
  }

  /**
   * Left element
   */
  public final L left;

  /**
   * Middle element
   */
  public final M middle;

  /**
   * Right element
   */
  public final R right;

  /**
   * Create a new immutable triple instance
   *
   * @param left   the left element, can be null
   * @param middle the middle element, can be null
   * @param right  the right element, can be null
   */
  public Triple(L left, M middle, R right) {
    this.left = left;
    this.middle = middle;
    this.right = right;
  }

  @Override
  public L getLeft() {
    return left;
  }

  @Override
  public M getMiddle() {
    return middle;
  }

  @Override
  public R getRight() {
    return right;
  }

}
