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
*/

@DisplayName("A doubly linked list deque")
public class DoublyLinkedListDequeTest {

    DoublyLinkedListDeque doublyLinkedListDeque;

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

            @Test
            @DisplayName("there is only one node when prepend to an empty queue")
            void shouldBeUniqueElementWhenPrependEmptyQueue(){
                doublyLinkedListDeque.prepend("Elemento");
                assertEquals(doublyLinkedListDeque.first(), doublyLinkedListDeque.last());
                assertEquals(1, doublyLinkedListDeque.size());
                assertEquals("Elemento", doublyLinkedListDeque.first());
            }

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

            @Test
            @DisplayName("there is only one node when append to an empty queue")
            void shouldBeUniqueElementWhenAppendEmptyQueue(){
                doublyLinkedListDeque.append("Elemento");
                assertEquals(doublyLinkedListDeque.first(), doublyLinkedListDeque.last());
                assertEquals(1, doublyLinkedListDeque.size());
                assertEquals("Elemento", doublyLinkedListDeque.last());
            }

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
            @Test
            @DisplayName("throws DoubleEndedQueueException when deleteFirst() to an empty Queue")
            void shouldThrowsExceptionWhenDeleteFirstEmptyQueue(){
                assertEquals(0, doublyLinkedListDeque.size());
                assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.deleteFirst());
            }

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

            @Test
            @DisplayName("throws DoubleEndedQueueException when deleteLast() to an empty Queue")
            void shouldThrowsExceptionWhenDeleteLastEmptyQueue(){
                assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.deleteLast());
            }

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

            @Test
            @DisplayName("try to remove an node with a value that it doesn't exists in a non empty Queue")
            void shouldNotRemoveAnyNodeTheQueueBecauseTheValueDoesNotExists(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                assertEquals(2, doublyLinkedListDeque.size());
                doublyLinkedListDeque.remove(3);
                assertEquals(2, doublyLinkedListDeque.size());
            }

            @Test
            @DisplayName("removes the first node when remove(int value) to an non empty Queue")
            void shouldRemoveFirstNodeWhenRemoveTheValueOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                assertEquals(2, doublyLinkedListDeque.size());
                doublyLinkedListDeque.remove(1);
                assertEquals(1, doublyLinkedListDeque.size());
            }

            @Test
            @DisplayName("removes the last node when remove(int value) to an non empty Queue")
            void shouldRemoveLastNodeWhenRemoveTheValueOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.append(2);
                assertEquals(2, doublyLinkedListDeque.size());
                doublyLinkedListDeque.remove(2);
                assertEquals(1, doublyLinkedListDeque.size());
            }

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
            @Test
            @DisplayName("throws exception when first() of an empty queue")
            void shouldThrowExceptionFirstOnEmptyQueue(){
                assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.first());
            }

            @Test
            @DisplayName("throws exception when last() of an empty queue")
            void shouldThrowExceptionLastOnEmptyQueue(){
                assertThrows(DoubleEndedQueueException.class, () -> doublyLinkedListDeque.last());
            }

            @Test
            @DisplayName("returns the first element when first() of a non empty queue")
            void shouldReturnFirstOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                assertEquals(1, doublyLinkedListDeque.first());
            }

            @Test
            @DisplayName("returns the last element when last() of a non empty queue")
            void shouldReturnLastOnNonEmptyQueue(){
                doublyLinkedListDeque.append(1);
                assertEquals(1, doublyLinkedListDeque.last());
            }

            @Test
            @DisplayName("returns cero when size() of an empty queue")
            void shouldReturnSizeCeroOfEmptyQueue(){
                assertEquals(0, doublyLinkedListDeque.size());
            }

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
                @Test
                @DisplayName("throws IndexOutOfBoundsException when index is greater than size")
                void indexFourOnAQueueWithSizeTwoShouldThrowException(){
                    doublyLinkedListDeque.append(1);
                    doublyLinkedListDeque.append(2);
                    assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedListDeque.get(4));
                }

                @Test
                @DisplayName("throws IndexOutOfBoundsException when index is minor than zero")
                void indexMinosOneOnAQueueWithSizeTwoShouldThrowException(){
                    doublyLinkedListDeque.append("Primero");
                    doublyLinkedListDeque.append("Segundo");
                    assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedListDeque.get(-1));
                }

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

                @Test
                @DisplayName("returns false when the item is not on the queue")
                void shouldReturnFalseWhenItemIsNotOnTheQueue(){
                    doublyLinkedListDeque.append("a");
                    doublyLinkedListDeque.append("b");
                    assertEquals(false, doublyLinkedListDeque.contains("c"));
                }

                @Test
                @DisplayName("returns true when the item is on the queue")
                void shouldReturnTrueWhenItemIsOnTheQueue(){
                    doublyLinkedListDeque.append("a");
                    doublyLinkedListDeque.append("b");
                    doublyLinkedListDeque.append("c");
                    assertEquals(true, doublyLinkedListDeque.contains("b"));
                }

                @Test
                @DisplayName("returns true when the item is on the first node of the queue")
                void shouldReturnTrueWhenItemIsFirstOnTheQueue(){
                    doublyLinkedListDeque.append("a");
                    doublyLinkedListDeque.append("b");
                    assertEquals(true, doublyLinkedListDeque.contains("a"));
                }

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

            @Test
            @DisplayName("a numeric comparator sort a disordered queue with numeric elements")
            void a(){
                Comparator mockedComparator = mock(Comparator.class);
                when(mockedComparator.compare(1,2)).thenReturn(-1);

                doublyLinkedListDeque.prepend(1);
                doublyLinkedListDeque.prepend(2);

                doublyLinkedListDeque.sort(mockedComparator);
                assertEquals(1, doublyLinkedListDeque.first());
                assertEquals(2, doublyLinkedListDeque.last());
            }

        }

    }

}
