package cc.ayakurayuki.repo.multivaluereturns.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import cc.ayakurayuki.repo.multivaluereturns.MutTriple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Ayakura Yuki
 * @date 2024/04/15-16:39
 */
@RunWith(JUnit4.class)
public class MutTripleTest {

  @Test
  public void testBasic() {
    final MutTriple<Integer, String, Boolean> triple = new MutTriple<>(0, "foo", Boolean.FALSE);
    assertEquals(0, triple.getLeft().intValue());
    assertEquals("foo", triple.getMiddle());
    assertEquals(Boolean.FALSE, triple.getRight());

    final MutTriple<Object, String, String> triple2 = new MutTriple<>(null, "bar", "hello");
    assertNull(triple2.getLeft());
    assertEquals("bar", triple2.getMiddle());
    assertEquals("hello", triple2.getRight());
  }

  @Test
  public void testDefault() {
    final MutTriple<Integer, String, Boolean> triple = new MutTriple<>();
    assertNull(triple.getLeft());
    assertNull(triple.getMiddle());
    assertNull(triple.getRight());
  }

  @Test
  public void testEmptyArrayGenerics() {
    final MutTriple<Integer, String, Boolean>[] empty = MutTriple.emptyArray();
    assertEquals(0, empty.length);
  }

  @Test
  public void testEmptyArrayLength() {
    @SuppressWarnings("unchecked") final MutTriple<Integer, String, Boolean>[] empty = (MutTriple<Integer, String, Boolean>[]) MutTriple.EMPTY_ARRAY;
    assertEquals(0, empty.length);
  }

  @Test
  public void testEquals() {
    assertEquals(MutTriple.of(null, "foo", "baz"), MutTriple.of(null, "foo", "baz"));
    assertNotEquals(MutTriple.of("foo", 0, Boolean.TRUE), MutTriple.of("foo", null, Boolean.TRUE));
    assertNotEquals(MutTriple.of("foo", "bar", "baz"), MutTriple.of("xyz", "bar", "baz"));
    assertNotEquals(MutTriple.of("foo", "bar", "baz"), MutTriple.of("foo", "bar", "blo"));

    final MutTriple<String, String, String> p = MutTriple.of("foo", "bar", "baz");
    assertEquals(p, p);
    assertNotEquals(p, new Object());
  }

  @Test
  public void testHashCode() {
    assertEquals(MutTriple.of(null, "foo", "baz").hashCode(), MutTriple.of(null, "foo", "baz").hashCode());
  }

  @Test
  public void testMutate() {
    final MutTriple<Integer, String, Boolean> triple = new MutTriple<>(0, "foo", Boolean.TRUE);
    triple.setLeft(42);
    triple.setMiddle("bar");
    triple.setRight(Boolean.FALSE);
    assertEquals(42, triple.getLeft().intValue());
    assertEquals("bar", triple.getMiddle());
    assertEquals(Boolean.FALSE, triple.getRight());
  }

  @Test
  public void testOfNonNull() {
    assertThrows(NullPointerException.class, () -> MutTriple.ofNonNull(null, null, null));
    assertThrows(NullPointerException.class, () -> MutTriple.ofNonNull(null, null, "z"));
    assertThrows(NullPointerException.class, () -> MutTriple.ofNonNull(null, "y", "z"));
    assertThrows(NullPointerException.class, () -> MutTriple.ofNonNull("x", null, null));
    assertThrows(NullPointerException.class, () -> MutTriple.ofNonNull("x", "y", null));
    final MutTriple<String, String, String> pair = MutTriple.ofNonNull("x", "y", "z");
    assertEquals("x", pair.left);
    assertEquals("y", pair.middle);
    assertEquals("z", pair.right);
  }

  @Test
  public void testToString() {
    assertEquals("(null,null,null)", MutTriple.of(null, null, null).toString());
    assertEquals("(null,two,null)", MutTriple.of(null, "two", null).toString());
    assertEquals("(one,null,null)", MutTriple.of("one", null, null).toString());
    assertEquals("(one,two,null)", MutTriple.of("one", "two", null).toString());
    assertEquals("(null,two,three)", MutTriple.of(null, "two", "three").toString());
    assertEquals("(one,null,three)", MutTriple.of("one", null, "three").toString());
    assertEquals("(one,two,three)", MutTriple.of("one", "two", "three").toString());
  }

  @Test
  public void testTripleOf() {
    final MutTriple<Integer, String, Boolean> triple = MutTriple.of(0, "foo", Boolean.TRUE);
    assertEquals(0, triple.getLeft().intValue());
    assertEquals("foo", triple.getMiddle());
    assertEquals(Boolean.TRUE, triple.getRight());
    final MutTriple<Object, String, String> triple2 = MutTriple.of(null, "bar", "hello");
    assertNull(triple2.getLeft());
    assertEquals("bar", triple2.getMiddle());
    assertEquals("hello", triple2.getRight());
  }

}
