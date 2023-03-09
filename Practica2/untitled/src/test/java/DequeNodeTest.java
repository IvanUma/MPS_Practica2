import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A dequeNode")
public class DequeNodeTest {
    DequeNode<Integer> dequeNode;

    @BeforeEach
    void setup() {
        dequeNode = new DequeNode<Integer>(0,null,null);
    }

    @Test
    @DisplayName("is instantiated with new DequeNode(0, null, null)")
    void isInstantiatedWithNew(){
        assertNotNull(dequeNode);
    }

    @Test
    @DisplayName("returns the same node (item, previous and next node) as instantiated")
    void shouldReturnNodeInstantiated() {
        assertEquals(0, dequeNode.getItem());
        assertEquals(null, dequeNode.getNext());
        assertEquals(null, dequeNode.getPrevious());
    }

    @Test
    @DisplayName("returns the updated values using setters")
    void shouldReturnUpdatedValues(){
        DequeNode<Integer> node2 = new DequeNode<>(2, dequeNode, null);
        DequeNode<Integer> node3 = new DequeNode<>(6, null, dequeNode);
        dequeNode.setItem(5);
        dequeNode.setNext(node2);
        dequeNode.setPrevious(node3);
        assertEquals(5, dequeNode.getItem());
        assertEquals(node2, dequeNode.getNext());
        assertEquals(node3, dequeNode.getPrevious());
    }

    @Test
    @DisplayName("is the first and last node if does not have previous nor next")
    void shouldBeFirstAndLastNode(){
        assertTrue(dequeNode.isFirstNode());
        assertTrue(dequeNode.isLastNode());
    }

    @Nested
    @DisplayName("evaluating if it is a terminal node")
    class isTerminalNode{

        @Test
        @DisplayName("is not a terminal node if it has previous and next")
        void shouldReturnTrueWhenHasPreviousAndNext(){
            DequeNode<Integer> node2 = new DequeNode<>(2, dequeNode, null);
            DequeNode<Integer> node3 = new DequeNode<>(6, null, dequeNode);
            dequeNode.setNext(node2);
            dequeNode.setPrevious(node3);
            assertTrue(dequeNode.isNotATerminalNode());
        }

        @Test
        @DisplayName("is a terminal node if it does not have previous and next")
        void shouldReturnFalseWhenDoesNotHavePreviousAndNext(){
            assertTrue(!dequeNode.isNotATerminalNode());
        }


        @Test
        @DisplayName("is a terminal node if it does not have previous")
        void shouldReturnFalseWhenDoesNotHavePrevious(){
            DequeNode<Integer> node2 = new DequeNode<>(2, null, dequeNode);
            dequeNode.setPrevious(node2);
            assertTrue(!dequeNode.isNotATerminalNode());
        }

        @Test
        @DisplayName("is a terminal node if it does not have next")
        void shouldReturnFalseWhenDoesNotHaveNext(){
            DequeNode<Integer> node2 = new DequeNode<>(2, dequeNode, null);
            dequeNode.setNext(node2);
            assertTrue(!dequeNode.isNotATerminalNode());
        }
    }




}
