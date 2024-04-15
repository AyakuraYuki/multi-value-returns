package cc.ayakurayuki.repo.multivaluereturns;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Result of a method returns presents two immutable fields with <em>ok</em>
 * and <em>err</em>.
 *
 * <p>
 * Result is designed to be in the same form as Golang handles errors.
 * This design will not throw exceptions
 * </p>
 *
 * <p>
 * Although the fields in the Result are immutable, it doesn't mean that
 * the field values themselves are immutable.
 * </p>
 *
 * @param <T>   the method returns type
 * @param <Err> the method exception type
 *
 * @author Ayakura Yuki
 */
public class Result<T, Err extends Throwable> implements IsError, Serializable {

  private static final long serialVersionUID = 7524107650488679766L;

  private final T   ok;
  private final Err err;

  private Result(T ok, Err err) {
    this.ok = ok;
    this.err = err;
  }

  /**
   * Creates a result with no error.
   *
   * @param ok a method result, can be null
   *
   * @return a result formed from the only ok field
   */
  public static <T, Err extends Throwable> Result<T, Err> ok(@Nullable T ok) {
    return new Result<>(ok, null);
  }

  /**
   * Creates a result with error.
   *
   * @param err a method throw, not null
   *
   * @return a result formed from the only err field
   */
  public static <T, Err extends Throwable> Result<T, Err> err(@Nonnull Err err) {
    return new Result<>(null, err);
  }

  /**
   * Creates a result with method returns and throws.
   *
   * @param ok  a method result, can be null
   * @param err a method throw, not null
   *
   * @return a result formed from the method returns and throws
   */
  public static <T, Err extends Throwable> Result<T, Err> create(@Nullable T ok, @Nonnull Err err) {
    return new Result<>(ok, err);
  }

  /**
   * Get return object.
   */
  public T ok() {
    return ok;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Result<?, ?> result = (Result<?, ?>) o;
    return Objects.equals(ok, result.ok)
        && Objects.equals(err, result.err);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ok, err);
  }

  @Override
  public String toString() {
    return String.format(
        "Result(%s) %s error%s",
        ok,
        isError() ? "with" : "without",
        isError() ? ": " + err.getMessage() : ""
    );
  }

}
