package cc.ayakurayuki.repo.multivaluereturns;

/**
 * @author Ayakura Yuki
 * @date 2024/04/15-10:41
 */
public class PairResult<L, R, Err extends Throwable> extends Pair<L, R> implements IsError {

  private static final long serialVersionUID = -3374939805352963841L;

  private final Err err;

  /**
   * Create a new immutable pair instance.
   *
   * @param left  the left element, can be null
   * @param right the right element, can be null
   */
  public PairResult(L left, R right, Err err) {
    super(left, right);
    this.err = err;
  }

  /**
   * Get exception.
   */
  public Err err() {
    return err;
  }

  @Override
  public boolean isError() {
    return err != null;
  }

}
