package cc.ayakurayuki.repo.multivaluereturns;

import java.util.Objects;

/**
 * A mutable triple consisting of three elements.
 *
 * @author Ayakura Yuki
 */
public class MutTriple<L, M, R> extends Tri<L, M, R> {

  /**
   * Serialization version
   */
  private static final long serialVersionUID = 5069558303638142054L;

  /**
   * a mutable triple of nulls.
   */
  @SuppressWarnings("rawtypes")
  public static final MutTriple NULL = new MutTriple<>(null, null, null);

  /**
   * Returns a mutable triple of nulls.
   *
   * @return a mutable triple of nulls.
   */
  @SuppressWarnings("unchecked")
  public static <L, M, R> MutTriple<L, M, R> nullTriple() {
    return (MutTriple<L, M, R>) NULL;
  }

  /**
   * An empty array.
   *
   * <p>Consider using {@link #emptyArray()} to avoid generics warnings.</p>
   */
  public static final MutTriple<?, ?, ?>[] EMPTY_ARRAY = {};

  /**
   * Returns the empty array singleton that can be assigned without compiler warning.
   *
   * @return the empty array singleton that can be assigned without compiler warning.
   */
  @SuppressWarnings("unchecked")
  public static <L, M, R> MutTriple<L, M, R>[] emptyArray() {
    return (MutTriple<L, M, R>[]) EMPTY_ARRAY;
  }

  /**
   * Creates a mutable triple of only left element.
   *
   * @param left the left element, can be null
   *
   * @return a triple formed from the only left element, not null
   */
  public static <L, M, R> MutTriple<L, M, R> left(final L left) {
    return of(left, null, null);
  }

  /**
   * Creates a mutable triple of only middle element.
   *
   * @param middle the middle element, can be null
   *
   * @return a triple formed from the only middle element, not null
   */
  public static <L, M, R> MutTriple<L, M, R> middle(final M middle) {
    return of(null, middle, null);
  }

  /**
   * Creates a mutable triple of only right element.
   *
   * @param right the right element, can be null
   *
   * @return a triple formed from the only right element, not null
   */
  public static <L, M, R> MutTriple<L, M, R> right(final R right) {
    return of(null, null, right);
  }

  /**
   * Creates a mutable triple from three objects inferring the generic types.
   *
   * @param left   the left element, can be null
   * @param middle the middle element, can be null
   * @param right  the right element, can be null
   *
   * @return a triple formed from the three parameters, not null
   */
  public static <L, M, R> MutTriple<L, M, R> of(final L left, final M middle, final R right) {
    return left != null || middle != null || right != null ? new MutTriple<>(left, middle, right) : nullTriple();
  }

  /**
   * Creates a mutable triple from three non-null objects inferring the generic types.
   *
   * @param left   the left element, cannot be null
   * @param middle the middle element, cannot be null
   * @param right  the right element, cannot be null
   *
   * @return a triple formed from the three parameters, not null
   *
   * @throws NullPointerException if any parameter is null
   */
  public static <L, M, R> MutTriple<L, M, R> ofNonNull(final L left, final M middle, final R right) {
    return of(
        Objects.requireNonNull(left),
        Objects.requireNonNull(middle),
        Objects.requireNonNull(right)
    );
  }

  /**
   * Left element
   */
  public L left;

  /**
   * Middle element
   */
  public M middle;

  /**
   * Right element
   */
  public R right;

  /**
   * Create a new triple instance of three nulls.
   */
  public MutTriple() {}

  /**
   * Create a new triple instance
   *
   * @param left   the left value, can be null
   * @param middle the middle value, can be null
   * @param right  the right value, can be null
   */
  public MutTriple(final L left, final M middle, final R right) {
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

  public void setLeft(final L left) {
    this.left = left;
  }

  public void setMiddle(final M middle) {
    this.middle = middle;
  }

  public void setRight(final R right) {
    this.right = right;
  }

}
