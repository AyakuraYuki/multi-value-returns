package cc.ayakurayuki.repo.multivaluereturns;

import java.util.Objects;

/**
 * PairResult is a class combined with `Pair` and `Result`.
 *
 * <p>
 * PairResult gives two normal-return elements and an error of exception.
 * </p>
 *
 * @author Ayakura Yuki
 */
public class PairResult<L, R, Err extends Throwable> extends P<L, R> implements IsError {

  /**
   * Serialization version
   */
  private static final long serialVersionUID = -5486901219016521354L;

  /**
   * An immutable pair of nulls.
   */
  @SuppressWarnings("rawtypes")
  public static final PairResult NULL = new PairResult<>(null, null, null);

  /**
   * Returns an immutable pair of nulls.
   *
   * @return an immutable pair of nulls.
   */
  @SuppressWarnings("unchecked")
  public static <L, R, Err extends Throwable> PairResult<L, R, Err> nullPairResult() {
    return (PairResult<L, R, Err>) NULL;
  }

  /**
   * An empty array.
   *
   * <p>Consider using {@link #emptyArray()} to avoid generics warnings.</p>
   */
  public static final PairResult<?, ?, ?>[] EMPTY_ARRAY = {};

  /**
   * Returns the empty array singleton that can be assigned without compiler warning.
   *
   * @return the empty array singleton that can be assigned without compiler warning.
   */
  @SuppressWarnings("unchecked")
  public static <L, R, Err extends Throwable> PairResult<L, R, Err>[] emptyArray() {
    return (PairResult<L, R, Err>[]) EMPTY_ARRAY;
  }

  public static <L, R, Err extends Throwable> PairResult<L, R, Err> left(final L left) {
    return of(left, null, null);
  }

  public static <L, R, Err extends Throwable> PairResult<L, R, Err> right(final R right) {
    return of(null, right, null);
  }

  public static <L, R, Err extends Throwable> PairResult<L, R, Err> err(final Err err) {
    return of(null, null, err);
  }

  public static <L, R, Err extends Throwable> PairResult<L, R, Err> of(final L left, final R right, final Err err) {
    return left != null || right != null || err != null ? new PairResult<>(left, right, err) : nullPairResult();
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
   * Throwable error
   */
  public final Err err;

  public PairResult(L left, R right, Err err) {
    this.left = left;
    this.right = right;
    this.err = err;
  }

  @Override
  public L getLeft() {
    return left;
  }

  @Override
  public R getRight() {
    return right;
  }

  public Err err() {
    return err;
  }

  @Override
  public boolean isError() {
    return err != null;
  }

  @Override
  public R setValue(R value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final PairResult<?, ?, ?> other = (PairResult<?, ?, ?>) obj;
    return Objects.equals(left, other.left)
        && Objects.equals(right, other.right)
        && Objects.equals(err, other.err);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), err);
  }

  @Override
  public String toString() {
    return String.format(
        "PairResult(%s, %s) %s error%s",
        getLeft(),
        getRight(),
        isError() ? "with" : "without",
        isError() ? ": " + err.getMessage() : ""
    );
  }

}
