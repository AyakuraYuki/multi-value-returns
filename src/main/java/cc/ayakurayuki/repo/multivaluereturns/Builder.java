package cc.ayakurayuki.repo.multivaluereturns;

/**
 * The Builder interface is designed to designate a class as a <em>builder</em>
 * object in the Builder design pattern. Builders are capable of creating and
 * configuring objects or results that normally take multiple steps to construct
 * or are very complex to derive.
 *
 * <p>
 * The Builder interface defines a single method {@link #build()}, that
 * classes must implement. The result of this method should be the final
 * configured object or result after all building operations are performed.
 * </p>
 *
 * @author Ayakura Yuki
 */
@FunctionalInterface
interface Builder<T> {

  T build();

}
