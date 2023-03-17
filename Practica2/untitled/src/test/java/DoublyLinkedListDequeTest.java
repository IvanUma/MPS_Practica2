package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
* Autores: Iván Delgado Alba y Marina Sayago Gutiérrez
*
*
* Test cases:
* 1. instantiated with doublyLinkedListDeque<>().
* 2. when first() of an empty queue then raises a DoubleEndedQueueException() and if last() of an empty queue
*    then raises a DoubleEndedQueueException() and size of an empty queue is 0.
* 3. when there is only the element "Elemento" added with prepend, the size is 1 and the first and last node are the same.
* 4. when the queue has [2,1] then the first is not the same as the last and the first one is 2.
* 5. when there is only the element "Elemento" added with append, the size is 1 and the first and last node are the same.
* 6. when the queue has [2,1] then the first is not the same as the last and the last one is 2.
* 7. when deleteFirst() of an empty queue then raises a DoubleEndedQueueException().
* 8. when deleteFirst() on a queue with [2,1] then size is 1 and first() is 1.
* 9. when deleteLast() of an empty queue then raises a DoubleEndedQueueException().
* 10. when deleteLast() on a queue with [2,1] then size is 1 and last() is 1.
* 11. when remove(3) on a queue with [1,2] then size is 2 because the value "3" is not in the queue.
* 12. when remove(1) on a queue with [1,2] (removing the first element) then size is 1.
* 13. when remove(2) on a queue with [1,2] (removing the last element) then size is 1.
* 14. when remove(2) on a queue with [1,2,3] (removing an element in the middle) then size is 2.
* 15. when first() of an empty queue raises a DoubleEndedQueueException().
* 16. when last() of an empty queue raises a DoubleEndedQueueException().
* 17. when first() of a queue with [1] returns 1.
* 18. when last() of a queue with [1] returns 1.
* 19. when size() of an empty queue returns 0.
* 20. when size() of a queue with [1] returns 1 and not 0.
* 21. when get(4) on a queue with [1,2] raises a IndexOutOfBoundsException().
* 22. when get(-1) on a queue with ["Primero", "Segundo"] raises a IndexOutOfBoundsException().
* 23. when get(0) on a queue with ["Primero", "Segundo", "Tercero", "Cuarto", "Quinto"] returns "Primero" (returns the first element).
* 24. when get(0) on a queue with ["Primero", "Segundo", "Tercero", "Cuarto", "Quinto"] returns "Segundo" 
*     (returns an element which index is on the first half of the queue).
* 25. when get(4) on a queue with ["Primero", "Segundo", "Tercero", "Cuarto", "Quinto"] returns "Quinto" (returns the last element).
* 26. when get(3) on a queue with ["Primero", "Segundo", "Tercero", "Cuarto", "Quinto"] returns "Cuarto"
*     (returns an element which index is on the second half of the queue).
* 27. when contains("c") on a queue with ["a", "b"] return false because the item is not in the queue.
* 28. when contains("b") on a queue with ["a", "b", "c"] return true.
* 29. when contains("a") on a queue with ["a", "b"] return true (first node of the queue).
* 30. when contains("b") on a queue with ["a", "b"] return true (last node of the queue).
* 31. when the queue has [2,1] if sort() then first() is 1 and last() is 2.
* 32. when the queue has [1,2] if sort() then first() is 1 and last() is 2.
* 33. when the queue has [3,2,1] if sort() then first() is 1 and last() is 3.
*
*/

@DisplayName("A doubly linked list deque")
public class DoublyLinkedListDequeTest {

    DoublyLinkedListDeque doublyLinkedListDeque;

    //1. instantiated with doublyLinkedListDeque<>().
    @Test
    @DisplayName("is instantiated with new doublyLinkedListDeque")
    void isInstantiatedNew(){
        new DoublyLinkedListDeque<>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew{
        @BeforeEach
        void setUp(){
            doublyLinkedListDeque = new DoublyLinkedListDeque();
        }

        //2. when first() of an empty queue then raises a DoubleEndedQueueException() and if last() of an empty queue
        //then raises a DoubleEndedQueueException() and size of an empty queue is 0.
        @Test
        @DisplayName("is empty")
        void isEmpty(){
            assertEquals(0, doublyLinkedListDeque.size());
            assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.first());
            assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.last());
        }

        @Nested
        @DisplayName("when introduced new elements")
        class addNodes{

            //3. when there is only the element "Elemento" added with prepend, the size is 1 and the first and last node are the same.
            @Test
            @DisplayName("there is only one node when prepend to an empty queue")
            void shouldBeUniqueElementWhenPrependEmptyQueue(){
                doublyLinkedListDeque.prepend("Elemento");
                assertEquals(doublyLinkedListDeque.first(), doublyLinkedListDeque.last());
                assertEquals(1, doublyLinkedListDeque.size());
                assertEquals("Elemento", doublyLinkedListDeque.first());
            }

            //4. when the queue has [2,1] then the first is not the same as the last and the first one is 2.
            @Test
            @DisplayName("there are more nodes when prepend to a queue with elements")
            void shouldHasMoreThanOneWhenPrependQueueWithElements(){
                doublyLinkedListDeque.prepend(1);
                doublyLinkedListDeque.prepend(2);
                assertNotEquals(doublyLinkedListDeque.first(), doublyLinkedListDeque.last());
                assertEquals(2, doublyLinkedListDeque.size());
                assertEquals(2, doublyLinkedListDeque.first());
                assertNotEquals(1, doublyLinkedListDeque.first());
            }

            //5. when there is only the element "Elemento" added with append, the size is 1 and the first and last node are the same.
            @Test
            @DisplayName("there is only one node when append to an empty queue")
            void shouldBeUniqueElementWhenAppendEmptyQueue(){
                doublyLinkedListDeque.append("Elemento");
                assertEquals(doublyLinkedListDeque.first(), doublyLinkedListDeque.last());
                assertEquals(1, doublyLinkedListDeque.size());
                assertEquals("Elemento", doublyLinkedListDeque.last());
            }

            //6. when the queue has [2,1] then the first is not the same as the last and the last one is 2.
            @Test
            @DisplayName("there are more nodes when append to a queue with elements")
            void shouldHasMoreThanOneWhenAppendQueueWithElements(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                assertNotEquals(doublyLinkedListDeque.first(), doublyLinkedListDeque.last());
                assertEquals(2, doublyLinkedListDeque.size());
                assertEquals(2, doublyLinkedListDeque.last());
                assertNotEquals(1, doublyLinkedListDeque.last());
            }

        }

        @Nested
        @DisplayName("when delete nodes")
        class deleteNodes{

            //7. when deleteFirst() of an empty queue then raises a DoubleEndedQueueException().
            @Test
            @DisplayName("throws DoubleEndedQueueException when deleteFirst() to an empty Queue")
            void shouldThrowsExceptionWhenDeleteFirstEmptyQueue(){
                assertEquals(0, doublyLinkedListDeque.size());
                assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.deleteFirst());
            }

            //8. when deleteFirst() on a queue with [2,1] then size is 1 and first() is 1.
            @Test
            @DisplayName("deletes the first node when deleteFirst() to an non empty Queue")
            void shouldDeleteFirstNodeWhenDeleteFirstOnNonEmptyQueue(){
                doublyLinkedListDeque.prepend(1);
                doublyLinkedListDeque.prepend(2);
                assertEquals(2, doublyLinkedListDeque.size());
                doublyLinkedListDeque.deleteFirst();
                assertEquals(1, doublyLinkedListDeque.first());
                assertEquals(1, doublyLinkedListDeque.size());
            }

            //9. when deleteLast() of an empty queue then raises a DoubleEndedQueueException().
            @Test
            @DisplayName("throws DoubleEndedQueueException when deleteLast() to an empty Queue")
            void shouldThrowsExceptionWhenDeleteLastEmptyQueue(){
                assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.deleteLast());
            }

            //10. when deleteLast() on a queue with [2,1] then size is 1 and last() is 1.
            @Test
            @DisplayName("deletes the last node when deleteLast() to an non empty Queue")
            void shouldDeleteLastNodeWhenDeleteLastOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                assertEquals(2, doublyLinkedListDeque.size());
                doublyLinkedListDeque.deleteLast();
                assertEquals(1, doublyLinkedListDeque.last());
                assertEquals(1, doublyLinkedListDeque.size());
            }

            //11. when remove(3) on a queue with [1,2] then size is 2 because the value "3" is not in the queue.
            @Test
            @DisplayName("try to remove a node with a value that it doesn't exists in a non empty Queue")
            void shouldNotRemoveAnyNodeOfTheQueueBecauseTheValueDoesNotExists(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                assertEquals(2, doublyLinkedListDeque.size());
                doublyLinkedListDeque.remove(3);
                assertEquals(2, doublyLinkedListDeque.size());
            }

            //12. when remove(1) on a queue with [1,2] (removing the first element) then size is 1.
            @Test
            @DisplayName("removes the first node when remove(int value) to an non empty Queue")
            void shouldRemoveFirstNodeWhenRemoveTheValueOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                assertEquals(2, doublyLinkedListDeque.size());
                doublyLinkedListDeque.remove(1);
                assertEquals(1, doublyLinkedListDeque.size());
            }

            //13. when remove(2) on a queue with [1,2] (removing the last element) then size is 1.
            @Test
            @DisplayName("removes the last node when remove(int value) to an non empty Queue")
            void shouldRemoveLastNodeWhenRemoveTheValueOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                assertEquals(2, doublyLinkedListDeque.size());
                doublyLinkedListDeque.remove(2);
                assertEquals(1, doublyLinkedListDeque.size());
            }

            //14. when remove(2) on a queue with [1,2,3] (removing an element in the middle) then size is 2.
            @Test
            @DisplayName("removes the node which contains the value when remove(int value) to an non empty Queue")
            void shouldRemoveTheNodeWhichContainsTheValueWhenRemoveTheValueOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                doublyLinkedListDeque.append(3);
                assertEquals(3, doublyLinkedListDeque.size());
                doublyLinkedListDeque.remove(2);
                assertEquals(2, doublyLinkedListDeque.size());
            }
        }

        @Nested
        @DisplayName("when getting information of the queue")
        class getFirstLastAndSize{

            //15. when first() of an empty queue raises a DoubleEndedQueueException().
            @Test
            @DisplayName("throws exception when first() of an empty queue")
            void shouldThrowExceptionFirstOnEmptyQueue(){
                assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.first());
            }

            //16. when last() of an empty queue raises a DoubleEndedQueueException().
            @Test
            @DisplayName("throws exception when last() of an empty queue")
            void shouldThrowExceptionLastOnEmptyQueue(){
                assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.last());
            }

            //17. when first() of a queue with [1] returns 1.
            @Test
            @DisplayName("returns the first element when first() of a non empty queue")
            void shouldReturnFirstOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                assertEquals(1, doublyLinkedListDeque.first());
            }

            //18. when last() of a queue with [1] returns 1.
            @Test
            @DisplayName("returns the last element when last() of a non empty queue")
            void shouldReturnLastOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                assertEquals(1, doublyLinkedListDeque.last());
            }

            //19. when size() of an empty queue returns 0.
            @Test
            @DisplayName("returns cero when size() of an empty queue")
            void shouldReturnSizeCeroOfEmptyQueue(){
                assertEquals(0, doublyLinkedListDeque.size());
            }

            //20. when size() of a queue with [1] returns 1 and not 0.
            @Test
            @DisplayName("returns nonCero when size() of an non empty queue")
            void shouldNotReturnSizeCeroOfNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                assertNotEquals(0, doublyLinkedListDeque.size());
                assertEquals(1, doublyLinkedListDeque.size());
            }


            @Nested
            @DisplayName("when using get()")
            class getFunction{

                //21. when get(4) on a queue with [1,2] raises a IndexOutOfBoundsException().
                @Test
                @DisplayName("throws IndexOutOfBoundsException when index is greater than size")
                void indexFourOnAQueueWithSizeTwoShouldThrowException(){
                    doublyLinkedListDeque.append(1);
                    doublyLinkedListDeque.append(2);
                    assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedListDeque.get(4));
                }

                //22. when get(-1) on a queue with ["Primero", "Segundo"] raises a IndexOutOfBoundsException().
                @Test
                @DisplayName("throws IndexOutOfBoundsException when index is minor than zero")
                void indexMinosOneOnAQueueWithSizeTwoShouldThrowException(){
                    doublyLinkedListDeque.append("Primero");
                    doublyLinkedListDeque.append("Segundo");
                    assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedListDeque.get(-1));
                }

                //23. when get(0) on a queue with ["Primero", "Segundo", "Tercero", "Cuarto", "Quinto"] returns "Primero" (returns the first element).
                @Test
                @DisplayName("returns the first element when index is zero")
                void indexZeroShouldReturnTheFirstElementOfTheQueue(){
                    doublyLinkedListDeque.append("Primero");
                    doublyLinkedListDeque.append("Segundo");
                    doublyLinkedListDeque.append("Tercero");
                    doublyLinkedListDeque.append("Cuarto");
                    doublyLinkedListDeque.append("Quinto");
                    assertEquals("Primero", doublyLinkedListDeque.get(0));
                }

                //24. when get(0) on a queue with ["Primero", "Segundo", "Tercero", "Cuarto", "Quinto"] returns "Segundo"
                // (returns an element which index is on the first half of the queue).
                @Test
                @DisplayName("returns the element when index is on the first half of the queue")
                void indexOneShouldReturnTheSecondElementOfAQueueWithSizeFive(){
                    doublyLinkedListDeque.append("Primero");
                    doublyLinkedListDeque.append("Segundo");
                    doublyLinkedListDeque.append("Tercero");
                    doublyLinkedListDeque.append("Cuarto");
                    doublyLinkedListDeque.append("Quinto");
                    assertEquals("Segundo", doublyLinkedListDeque.get(1));
                }

                //25. when get(4) on a queue with ["Primero", "Segundo", "Tercero", "Cuarto", "Quinto"] returns "Quinto" (returns the last element).
                @Test
                @DisplayName("returns the last element when index is size minus one")
                void indexSizeMinosOneShouldReturnTheLastElementOfTheQueue(){
                    doublyLinkedListDeque.append("Primero");
                    doublyLinkedListDeque.append("Segundo");
                    doublyLinkedListDeque.append("Tercero");
                    doublyLinkedListDeque.append("Cuarto");
                    doublyLinkedListDeque.append("Quinto");
                    assertEquals("Quinto", doublyLinkedListDeque.get(4));
                }

                //26. when get(3) on a queue with ["Primero", "Segundo", "Tercero", "Cuarto", "Quinto"] returns "Cuarto"
                // (returns an element which index is on the second half of the queue).
                @Test
                @DisplayName("returns the element when index is on the second half of the queue")
                void indexThreeShouldReturnTheFourthElementOfAQueueWithSizeFive(){
                    doublyLinkedListDeque.append("Primero");
                    doublyLinkedListDeque.append("Segundo");
                    doublyLinkedListDeque.append("Tercero");
                    doublyLinkedListDeque.append("Cuarto");
                    doublyLinkedListDeque.append("Quinto");
                    assertEquals("Cuarto", doublyLinkedListDeque.get(3));
                }
            }

            @Nested
            @DisplayName("when using contains()")
            class containsTest{

                //27. when contains("c") on a queue with ["a", "b"] return false because the item is not in the queue.
                @Test
                @DisplayName("returns false when the item is not on the queue")
                void shouldReturnFalseWhenItemIsNotOnTheQueue(){
                    doublyLinkedListDeque.append("a");
                    doublyLinkedListDeque.append("b");
                    assertEquals(false, doublyLinkedListDeque.contains("c"));
                }

                //28. when contains("b") on a queue with ["a", "b", "c"] return true.
                @Test
                @DisplayName("returns true when the item is on the queue")
                void shouldReturnTrueWhenItemIsOnTheQueue(){
                    doublyLinkedListDeque.append("a");
                    doublyLinkedListDeque.append("b");
                    doublyLinkedListDeque.append("c");
                    assertEquals(true, doublyLinkedListDeque.contains("b"));
                }

                //29. when contains("a") on a queue with ["a", "b"] return true (first node of the queue).
                @Test
                @DisplayName("returns true when the item is on the first node of the queue")
                void shouldReturnTrueWhenItemIsFirstOnTheQueue(){
                    doublyLinkedListDeque.append("a");
                    doublyLinkedListDeque.append("b");
                    assertEquals(true, doublyLinkedListDeque.contains("a"));
                }

                //30. when contains("b") on a queue with ["a", "b"] return true (last node of the queue).
                @Test
                @DisplayName("returns true when the item is on the last node of the queue")
                void shouldReturnTrueWhenItemIsLastOnTheQueue(){
                    doublyLinkedListDeque.append("a");
                    doublyLinkedListDeque.append("b");
                    assertEquals(true, doublyLinkedListDeque.contains("b"));
                }
            }

        }

        @Nested
        @DisplayName("when using sort()")
        class sortTest{

            //31. when the queue has [2,1] if sort() then first() is 1 and last() is 2.
            @Test
            @DisplayName("a numeric comparator sort on a disordered queue with numeric elements")
            void disorderedSimpleNumericQueue(){
                Comparator mockedComparator = mock(Comparator.class);
                when(mockedComparator.compare(2,1)).thenReturn(1);

                doublyLinkedListDeque.prepend(1);
                doublyLinkedListDeque.prepend(2);

                doublyLinkedListDeque.sort(mockedComparator);
                assertEquals(1, doublyLinkedListDeque.first());
                assertEquals(2, doublyLinkedListDeque.last());
            }

            //32. when the queue has [1,2] if sort() then first() is 1 and last() is 2.
            @Test
            @DisplayName("a numeric comparator sort on an ordered queue with numeric elements")
            void OrderedNumericQueue(){
                Comparator mockedComparator = mock(Comparator.class);
                when(mockedComparator.compare(1,2)).thenReturn(-1);

                doublyLinkedListDeque.prepend(2);
                doublyLinkedListDeque.prepend(1);

                doublyLinkedListDeque.sort(mockedComparator);
                assertEquals(1, doublyLinkedListDeque.first());
                assertEquals(2, doublyLinkedListDeque.last());
            }

            //33. when the queue has [3,2,1] if sort() then first() is 1 and last() is 3.
            @Test
            @DisplayName("a numeric comparator sort on a disordered queue with three items")
            void disorderedWithMoreItemsNumericQueue(){
                Comparator mockedComparator = mock(Comparator.class);
                when(mockedComparator.compare(2,1)).thenReturn(1);
                when(mockedComparator.compare(3,2)).thenReturn(1);
                when(mockedComparator.compare(3,1)).thenReturn(2);
                when(mockedComparator.compare(1,2)).thenReturn(-1);
                when(mockedComparator.compare(2,3)).thenReturn(-1);
                when(mockedComparator.compare(1,3)).thenReturn(-2);

                doublyLinkedListDeque.prepend(1);
                doublyLinkedListDeque.prepend(2);
                doublyLinkedListDeque.prepend(3);

                doublyLinkedListDeque.sort(mockedComparator);

                assertEquals(1, doublyLinkedListDeque.first());
                assertEquals(3, doublyLinkedListDeque.last());
            }
        }

    }

}
