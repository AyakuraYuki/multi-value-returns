package cc.ayakurayuki.repo.multivaluereturns.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import cc.ayakurayuki.repo.multivaluereturns.MutPair;
import java.util.HashMap;
import java.util.Map.Entry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Ayakura Yuki
 * @date 2024/04/14-15:12
 */
@RunWith(JUnit4.class)
public class MutPairTest {

  @Test
  public void testBasic() {
    MutPair<Integer, String> oldPair = new MutPair<>(0, "foo");
    MutPair<Integer, String> nowPair;
    for (int i = 0; i < 4; i++) {
      nowPair = MutPair.of(oldPair);
      assertEquals(0, nowPair.left.intValue());
      assertEquals(0, nowPair.getLeft().intValue());
      assertEquals("foo", nowPair.right);
      assertEquals("foo", nowPair.getRight());
      assertEquals(oldPair, nowPair);
      oldPair = nowPair;
    }

    MutPair<Object, String> oldPair2 = new MutPair<>(null, "bar");
    MutPair<Object, String> nowPair2;
    for (int i = 0; i < 4; i++) {
      nowPair2 = MutPair.of(oldPair2);
      assertNull(nowPair2.left);
      assertNull(nowPair2.getLeft());
      assertEquals("bar", nowPair2.right);
      assertEquals("bar", nowPair2.getRight());
      oldPair2 = nowPair2;
    }
  }

  @Test
  public void testDefault() {
    final MutPair<Integer, String> pair = new MutPair<>();
    assertNull(pair.getLeft());
    assertNull(pair.getRight());
  }

  @Test
  public void testEmptyArrayGenerics() {
    final MutPair<Integer, String>[] empty = MutPair.emptyArray();
    assertEquals(0, empty.length);
  }

  @Test
  public void testEmptyArrayLength() {
    @SuppressWarnings("unchecked") final MutPair<Integer, String>[] empty = (MutPair<Integer, String>[]) MutPair.EMPTY_ARRAY;
    assertEquals(0, empty.length);
  }

  @Test
  public void testEquals() {
    assertEquals(MutPair.of(null, "foo"), MutPair.of(null, "foo"));
    assertNotEquals(MutPair.of("foo", 0), MutPair.of("foo", null));
    assertNotEquals(MutPair.of("foo", "bar"), MutPair.of("xyz", "bar"));

    final MutPair<String, String> p = MutPair.of("foo", "bar");
    assertEquals(p, p);
    assertNotEquals(p, new Object());
  }

  @Test
  public void testHashCode() {
    assertEquals(MutPair.of(null, "foo").hashCode(), MutPair.of(null, "foo").hashCode());
  }

  @Test
  public void testMutate() {
    final MutPair<Integer, String> pair = new MutPair<>(0, "foo");
    pair.setLeft(42);
    pair.setRight("bar");
    assertEquals(42, pair.getLeft().intValue());
    assertEquals("bar", pair.getRight());
  }

  @Test
  public void testOfNonNull() {
    assertThrows(NullPointerException.class, () -> MutPair.ofNonNull(null, null));
    assertThrows(NullPointerException.class, () -> MutPair.ofNonNull(null, "x"));
    assertThrows(NullPointerException.class, () -> MutPair.ofNonNull("x", null));
    final MutPair<String, String> pair = MutPair.ofNonNull("x", "y");
    assertEquals("x", pair.left);
    assertEquals("y", pair.right);
  }

  @Test
  public void testPairOfMapEntry() {
    final HashMap<Integer, String> map = new HashMap<>();
    map.put(0, "foo");
    final Entry<Integer, String> entry = map.entrySet().iterator().next();
    final MutPair<Integer, String> pair = MutPair.of(entry);
    assertEquals(entry.getKey(), pair.getLeft());
    assertEquals(entry.getValue(), pair.getRight());
  }

  @Test
  public void testPairOfObjects() {
    final MutPair<Integer, String> pair = MutPair.of(0, "foo");
    assertEquals(0, pair.getLeft().intValue());
    assertEquals("foo", pair.getRight());
    final MutPair<Object, String> pair2 = MutPair.of(null, "bar");
    assertNull(pair2.getLeft());
    assertEquals("bar", pair2.getRight());
    final MutPair<?, ?> pair3 = MutPair.of(null, null);
    assertNull(pair3.left);
    assertNull(pair3.right);
  }

  @Test
  public void testToString() {
    assertEquals("(null,null)", MutPair.of(null, null).toString());
    assertEquals("(null,two)", MutPair.of(null, "two").toString());
    assertEquals("(one,null)", MutPair.of("one", null).toString());
    assertEquals("(one,two)", MutPair.of("one", "two").toString());
  }

}
