package cc.ayakurayuki.repo.multivaluereturns;

/**
 * Represent a result contained with an exception or not in a method.
 *
 * <p>
 * The IsError interface is designed to catch all exceptions and
 * return as an error field in normal returns in a method.
 * </p>
 *
 * <p>
 * The IsError interface defines a single method {@link #isError()}, that
 * classes must implement.
 * </p>
 *
 * @author Ayakura Yuki
 */
@FunctionalInterface
public interface IsError {

  boolean isError();

}
