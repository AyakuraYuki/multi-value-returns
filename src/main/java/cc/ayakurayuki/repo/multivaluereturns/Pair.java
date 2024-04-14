package cc.ayakurayuki.repo.multivaluereturns;

import java.util.Map;
import java.util.Objects;

/**
 * An immutable pair consisting of two elements.
 *
 * @param <L> the left element type
 * @param <R> the right element type
 *
 * @author Ayakura Yuki
 */
public class Pair<L, R> extends P<L, R> {

  /**
   * Serialization version
   */
  private static final long serialVersionUID = 6873877226336491169L;

  /**
   * An immutable pair of nulls.
   */
  @SuppressWarnings("rawtypes")
  public static final Pair NULL = new Pair<>(null, null);

  /**
   * Returns an immutable pair of nulls.
   *
   * @return an immutable pair of nulls.
   */
  @SuppressWarnings("unchecked")
  public static <L, R> Pair<L, R> nullPair() {
    return (Pair<L, R>) NULL;
  }

  /**
   * An empty array.
   *
   * <p>Consider using {@link #emptyArray()} to avoid generics warnings.</p>
   */
  public static final Pair<?, ?>[] EMPTY_ARRAY = {};

  /**
   * Returns the empty array singleton that can be assigned without compiler warning.
   *
   * @return the empty array singleton that can be assigned without compiler warning.
   */
  @SuppressWarnings("unchecked")
  public static <L, R> Pair<L, R>[] emptyArray() {
    return (Pair<L, R>[]) EMPTY_ARRAY;
  }

  /**
   * Creates an immutable pair of only left element.
   *
   * @param left the left element, can be null
   *
   * @return a pair formed from the only left element, not null
   */
  public static <L, R> Pair<L, R> left(final L left) {
    return of(left, null);
  }

  /**
   * Creates an immutable pair of only right element.
   *
   * @param right the right element, can be null
   *
   * @return a pair formed from the only right element, not null
   */
  public static <L, R> Pair<L, R> right(final R right) {
    return of(null, right);
  }

  /**
   * Creates an immutable pair of two objects inferring the generic types.
   *
   * @param left  the left element, can be null
   * @param right the right element, can be null
   *
   * @return a pair formed from the two parameters, not null
   */
  public static <L, R> Pair<L, R> of(final L left, final R right) {
    return left != null || right != null ? new Pair<>(left, right) : nullPair();
  }

  /**
   * Creates an immutable pair from a map entry.
   *
   * @param pair the existing map entry
   *
   * @return a pair formed from the map entry
   */
  public static <L, R> Pair<L, R> of(final Map.Entry<L, R> pair) {
    return pair != null ? new Pair<>(pair.getKey(), pair.getValue()) : nullPair();
  }

  /**
   * Creates an immutable pair of two non-null objects inferring the generic types.
   *
   * @param left  the left element, cannot be null
   * @param right the right element, cannot be null
   *
   * @return a pair formed from the two parameters, not null
   *
   * @throws NullPointerException if any parameter is null
   */
  public static <L, R> Pair<L, R> ofNonNull(final L left, final R right) {
    return of(
        Objects.requireNonNull(left),
        Objects.requireNonNull(right)
    );
  }

  /**
   * Left element
   */
  public final L left;

  /**
   * Right element
   */
  public final R right;

  /**
   * Create a new immutable pair instance.
   *
   * @param left  the left element, can be null
   * @param right the right element, can be null
   */
  public Pair(final L left, final R right) {
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

  @Override
  public R setValue(R value) {
    throw new UnsupportedOperationException();
  }

}
