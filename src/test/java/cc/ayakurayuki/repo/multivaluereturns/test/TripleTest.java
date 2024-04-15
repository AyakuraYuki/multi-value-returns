package cc.ayakurayuki.repo.multivaluereturns.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import cc.ayakurayuki.repo.multivaluereturns.MutTriple;
import cc.ayakurayuki.repo.multivaluereturns.Triple;
import java.util.Calendar;
import java.util.HashSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Ayakura Yuki
 * @date 2024/04/15-16:31
 */
@RunWith(JUnit4.class)
public class TripleTest {

  @Test
  public void testComparable1() {
    final Triple<String, String, String> triple1 = Triple.of("A", "D", "A");
    final Triple<String, String, String> triple2 = Triple.of("B", "C", "A");
    assertEquals(0, triple1.compareTo(triple1));
    assertTrue(triple1.compareTo(triple2) < 0);
    assertEquals(0, triple2.compareTo(triple2));
    assertTrue(triple2.compareTo(triple1) > 0);
  }

  @Test
  public void testComparable2() {
    final Triple<String, String, String> triple1 = Triple.of("A", "C", "B");
    final Triple<String, String, String> triple2 = Triple.of("A", "D", "B");
    assertEquals(0, triple1.compareTo(triple1));
    assertTrue(triple1.compareTo(triple2) < 0);
    assertEquals(0, triple2.compareTo(triple2));
    assertTrue(triple2.compareTo(triple1) > 0);
  }

  @Test
  public void testComparable3() {
    final Triple<String, String, String> triple1 = Triple.of("A", "A", "D");
    final Triple<String, String, String> triple2 = Triple.of("A", "B", "C");
    assertEquals(0, triple1.compareTo(triple1));
    assertTrue(triple1.compareTo(triple2) < 0);
    assertEquals(0, triple2.compareTo(triple2));
    assertTrue(triple2.compareTo(triple1) > 0);
  }

  @Test
  public void testComparable4() {
    final Triple<String, String, String> triple1 = Triple.of("B", "A", "C");
    final Triple<String, String, String> triple2 = Triple.of("B", "A", "D");
    assertEquals(0, triple1.compareTo(triple1));
    assertTrue(triple1.compareTo(triple2) < 0);
    assertEquals(0, triple2.compareTo(triple2));
    assertTrue(triple2.compareTo(triple1) > 0);
  }

  @Test
  public void testCompatibilityBetweenTriples() {
    final Triple<Integer, String, Boolean> triple = Triple.of(0, "foo", Boolean.TRUE);
    final MutTriple<Integer, String, Boolean> triple2 = MutTriple.of(0, "foo", Boolean.TRUE);
    assertEquals(triple, triple2);
    assertEquals(triple.hashCode(), triple2.hashCode());
    final HashSet<Triple<Integer, String, Boolean>> set = new HashSet<>();
    set.add(triple);
    assertTrue(set.contains(triple2));
  }

  @Test
  public void testEmptyArrayGenerics() {
    final Triple<Integer, String, Boolean>[] empty = Triple.emptyArray();
    assertEquals(0, empty.length);
  }

  @Test
  public void testEmptyArrayLength() {
    @SuppressWarnings("unchecked") final Triple<Integer, String, Boolean>[] empty = (Triple<Integer, String, Boolean>[]) Triple.EMPTY_ARRAY;
    assertEquals(0, empty.length);
  }

  @Test
  public void testFormattable_padded() {
    final Triple<String, String, String> triple = Triple.of("Key", "Something", "Value");
    assertEquals("         (Key,Something,Value)", String.format("%1$30s", triple));
  }

  @Test
  public void testFormattable_simple() {
    final Triple<String, String, String> triple = Triple.of("Key", "Something", "Value");
    assertEquals("(Key,Something,Value)", String.format("%1$s", triple));
  }

  @Test
  public void testOfNonNull() {
    assertThrows(NullPointerException.class, () -> Triple.ofNonNull(null, null, null));
    assertThrows(NullPointerException.class, () -> Triple.ofNonNull(null, null, "z"));
    assertThrows(NullPointerException.class, () -> Triple.ofNonNull(null, "y", "z"));
    assertThrows(NullPointerException.class, () -> Triple.ofNonNull("x", null, null));
    assertThrows(NullPointerException.class, () -> Triple.ofNonNull("x", "y", null));
    final Triple<String, String, String> pair = Triple.ofNonNull("x", "y", "z");
    assertEquals("x", pair.getLeft());
    assertEquals("y", pair.getMiddle());
    assertEquals("z", pair.getRight());
  }

  @Test
  public void testToString() {
    final Triple<String, String, String> triple = Triple.of("Key", "Something", "Value");
    assertEquals("(Key,Something,Value)", triple.toString());
  }

  @Test
  public void testToStringCustom() {
    final Calendar date = Calendar.getInstance();
    date.set(2011, Calendar.APRIL, 25);
    final Triple<String, String, Calendar> triple = Triple.of("DOB", "string", date);
    assertEquals("Test created on " + "04-25-2011", triple.toString("Test created on %3$tm-%3$td-%3$tY"));
  }

  @Test
  public void testTripleOf() {
    final Triple<Integer, String, Boolean> triple = Triple.of(0, "foo", Boolean.TRUE);
    assertTrue(triple instanceof Triple<?, ?, ?>);
    assertEquals(0, triple.left.intValue());
    assertEquals("foo", triple.middle);
    assertEquals(Boolean.TRUE, triple.right);
    final Triple<Object, String, Long> triple2 = Triple.of(null, "bar", Long.valueOf(200L));
    assertTrue(triple2 instanceof Triple<?, ?, ?>);
    assertNull(triple2.left);
    assertEquals("bar", triple2.middle);
    assertEquals(Long.valueOf(200L), triple2.right);
  }

}
