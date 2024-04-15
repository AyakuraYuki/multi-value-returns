package cc.ayakurayuki.repo.multivaluereturns.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import cc.ayakurayuki.repo.multivaluereturns.MutPair;
import cc.ayakurayuki.repo.multivaluereturns.Pair;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Ayakura Yuki
 * @date 2024/04/14-15:11
 */
@RunWith(JUnit4.class)
public class PairTest {

  @Test
  public void testComparable_1() {
    final Pair<String, String> pair1 = Pair.of("A", "D");
    final Pair<String, String> pair2 = Pair.of("B", "C");
    assertEquals(0, pair1.compareTo(pair1));
    assertTrue(pair1.compareTo(pair2) < 0);
    assertEquals(0, pair2.compareTo(pair2));
    assertTrue(pair2.compareTo(pair1) > 0);
  }

  @Test
  public void testComparable_2() {
    final Pair<String, String> pair1 = Pair.of("A", "C");
    final Pair<String, String> pair2 = Pair.of("A", "D");
    assertEquals(0, pair1.compareTo(pair1));
    assertTrue(pair1.compareTo(pair2) < 0);
    assertEquals(0, pair2.compareTo(pair2));
    assertTrue(pair2.compareTo(pair1) > 0);
  }

  @Test
  public void testCompatibilityBetweenPairs() {
    final Pair<Integer, String> pair = Pair.of(0, "foo");
    final MutPair<Integer, String> pair2 = MutPair.of(0, "foo");
    assertEquals(pair, pair2);
    assertEquals(pair.hashCode(), pair2.hashCode());

    final HashSet<Pair<Integer, String>> set = new HashSet<>();
    set.add(pair);
    assertTrue(set.contains(pair2));

    pair2.setValue("bar");
    assertNotEquals(pair, pair2);
    assertNotEquals(pair.hashCode(), pair2.hashCode());
  }

  @Test
  public void testEmptyArrayGenerics() {
    final Pair<Integer, String>[] empty = Pair.emptyArray();
    assertEquals(0, empty.length);
  }

  @Test
  public void testEmptyArrayLength() {
    @SuppressWarnings("unchecked") final Pair<Integer, String>[] empty = (Pair<Integer, String>[]) Pair.EMPTY_ARRAY;
    assertEquals(0, empty.length);
  }

  @Test
  public void testFormattable_padded() {
    final Pair<String, String> pair = Pair.of("Key", "Value");
    assertEquals("         (Key,Value)", String.format("%1$20s", pair));
  }

  @Test
  public void testFormattable_simple() {
    final Pair<String, String> pair = Pair.of("Key", "Value");
    assertEquals("(Key,Value)", String.format("%1$s", pair));
  }

  @Test
  public void testMapEntry() {
    final Pair<Integer, String> pair = Pair.of(0, "foo");
    final HashMap<Integer, String> map = new HashMap<>();
    map.put(0, "foo");
    final Entry<Integer, String> entry = map.entrySet().iterator().next();
    assertEquals(pair, entry);
    assertEquals(pair.hashCode(), entry.hashCode());
  }

  @Test
  public void testOfNonNull() {
    assertThrows(NullPointerException.class, () -> Pair.ofNonNull(null, null));
    assertThrows(NullPointerException.class, () -> Pair.ofNonNull(null, "x"));
    assertThrows(NullPointerException.class, () -> Pair.ofNonNull("x", null));
    final Pair<String, String> pair = Pair.ofNonNull("x", "y");
    assertEquals("x", pair.getLeft());
    assertEquals("y", pair.getRight());
  }

  @Test
  public void testPairOfMapEntry() {
    final HashMap<Integer, String> map = new HashMap<>();
    map.put(0, "foo");
    final Entry<Integer, String> entry = map.entrySet().iterator().next();
    final Pair<Integer, String> pair = Pair.of(entry);
    assertEquals(entry.getKey(), pair.getLeft());
    assertEquals(entry.getValue(), pair.getRight());
  }

  @Test
  public void testPairOfObjects() {
    final Pair<Integer, String> pair = Pair.of(0, "foo");
    assertTrue(pair instanceof Pair<?, ?>);
    assertEquals(0, pair.left.intValue());
    assertEquals("foo", pair.right);

    final Pair<Object, String> pair2 = Pair.of(null, "bar");
    assertTrue(pair2 instanceof Pair<?, ?>);
    assertNull(pair2.left);
    assertEquals("bar", pair2.right);

    final Pair<?, ?> pair3 = Pair.of(null, null);
    assertNull(pair3.getLeft());
    assertNull(pair3.getRight());
  }

  @Test
  public void testToString() {
    final Pair<String, String> pair = Pair.of("Key", "Value");
    assertEquals("(Key,Value)", pair.toString());
  }

  @Test
  public void testToStringCustom() {
    final Calendar date = Calendar.getInstance();
    date.set(2011, Calendar.APRIL, 25);
    final Pair<String, Calendar> pair = Pair.of("DOB", date);
    assertEquals("Test created on " + "04-25-2011", pair.toString("Test created on %2$tm-%2$td-%2$tY"));
  }

}
