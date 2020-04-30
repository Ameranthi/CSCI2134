import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/* YOUR TARGET METHOD IS: int indexOf(Object o) in IndexableList.java
 *
 * FINAL EXAM NON-CODING ANSWERS
 ********************************************************
 * 1. Using git: if you have gotten this far you have used git.
 *    NOTE: To get full marks you MUST perform a commit after
 *          each question.  This way, if something goes wrong
 *          I can check each part separately and give you the
 *          marks you deserve.
 *
 * 2a. Give three (3) test cases for the TARGET method (see above)
 *     Each test case should be one line long.
 ANSWER HERE:
1) gives back the correct index of the object
2) gives back -1 if object doesn't appear in the list
3) add an element and see the index of that

 * 2b. Implement the unit tests below (after all these comments).


3. Debug the issues causing your tests to fail.
 *    There are at least a couple bugs in the code.
 *    List bugs you found and fixed below.  Give
 *    - a brief description of each bug
 *    - method where the bug occurs
 *    - how you fixed the problem
ANSWER HERE:
*nullPointerException - the add method isn't working properly as creating the indexableList does not happen properly
* currentarry needs to be instantiated not default to 0 or null
* 1000 is not correct needs to be chnaged to a final variable and to a size of next array- add method
* out of bounds - changed the numbers range to be in bounds add method
* in indexof - if (!prevArray[i].equals(o)) { got rid of the ! to produce results of return i logivc errors


* 4a.  Identify three (3) locations in the code where assertions
*      would be appropriate. Give
*      - method where assertion should be used
*      - what the assertions should assert
ANSWER HERE:
- assert that assert numElements == currentArray.length; indexOf() ensure not the previous arrays size
- assert assert lastMoved > -1; - should never be a negative indexOf()
- assert currentArray.length > prevArray.length; add() this will ensure that the new array got bigger
- assert currentArray != null: "current arry shouldnt be null"

* 4b. Write the assertions in the code.
*
* 5a. Suggest one (1) exception that would be appropriate
*     for the TARGET method and one (1) exception somewhere
*     else in the code.
*     State the condition under which the exceptions should be
*     thrown.
ANSWER HERE:
* check if the get method works correctly- must have index out of bounds exception after the amount of elements in the
* array - checking if index 4 has element, should nto have index 4 int has been chnaged throughtout the exam
*
* indexOfInputMismatchTest - check to validate right type in indexOf
*if put in int and not correct obj type string

* 5b. Implement the exceptions you suggested.
*
*
* 5c. Add unit tests to test that the exceptions are thrown when
*     appropriate.
*
* 6a. Identify three (3) procedural refactorings that can be
*     done in the code.  Give
*    - a brief description of each issue
*    - the method where the issue is
*    - how to fix the issue
ANSWER HERE:
* naming: numElements - should be numberOfElementsInCurrent also start using size() this is not descriptive n also
* doesnt take advantage of the getter
*
* naming add() methods have same name but different - not as relevant but can be confusing when using add method
*
* e should be more descriptive - element; in every method() fix : refractor rename
*
* add() method needs to have final variable for int addedLength = currentArray.length; this will help with readibility

* 6b. Perform the refactorings on the code.  Be sure to do
*     regression testing
*
* 7. Identify three (3) class-level refactorings that can be
*     done in the code.  Give
*    - a brief description of each issue
*    - where the issue is
*    - what SOLID principle (if any) are violated
*    - whether a class implementation or class interface refactoring
*      is needed
*    - how to fix the issue
ANSWER HERE:
implement isEmpty to be used when adding on to lists. If list is empty then add from the beginning
* create new method to create an array lists of array list to hold all the variations of lists that it goes through

*IndexablebList is not abstract
* Classes: indeableLkist.java, orderedIndexableList
* Issue: According to DIP, concrete classes should not be extended b/c this creates a dependency on their implementation.
* Issue: IndexableList is an abstract concept unlike the orderedindexablelist. also inhibits expansions to
* unorderedlist/randomizdindexlist
* Fix: Make indexabeleList abstract.

* violates Open/Close Principle
* Classes: indexList
* Issue: Any time a new list is added, this code will need to be changed.
* Fix: Use a Factory pattern to encapsulate the instantiation of new lists (more than one type of list many types of objects)

*has protected variables in indexablelist (DIP violation)
* Classes: indeaxablelist and subclasses•
* Location: Instance variables in indexablelist
* Issues: All subclasses now depend on a concrete implementation of the indexablelist.
* Fix: These accesses should be done with protected setters/getters to minimize the dependencies
*******************************************************
* Place written answers above this line
*******************************************************
*/

class Tests {
    /* You can clone and rename this method for all the tests that
     * you will need to do.
     *
     * All tests for all source files should be placed here.
     */

    //List<String> listSample = Arrays.asList("a", "b", "c");

    @Test
    void indexOftest_1() {
        //List<String> listSample = Arrays.asList("a", "b", "c");
        String [] sample = new String [] {"a", "b", "c"};
        IndexableList<String> listA = new IndexableList<>(sample);

        listA.indexOf("c");

        assertEquals(2, listA.indexOf("c"),
                "indexOf did not return correct value");
    }

    @Test
    void indexOftest_2() {
        //List<String> listSample = Arrays.asList("a", "b", "c");
        String [] sample = new String [] {"a", "b", "c"};
        IndexableList<String> listA = new IndexableList<>(sample);

        listA.indexOf("d");

        assertEquals(-1, listA.indexOf("d"),
                "indexOf did not return correct value");
    }
    @Test

    void indexOftest_3() {
        String [] sample = new String [] {"a", "b", "c"};
        IndexableList<String> listA = new IndexableList<String>(sample);


        listA.add("d");
        listA.indexOf("d");

        assertEquals(3, listA.indexOf("d"),
                "indexOf did not return correct value");
    }

    @Test
    void getMethodIndexOutOfBounds() {

        String [] sample = new String [] {"a", "b", "c"};
        IndexableList<String> listA = new IndexableList<String>(sample);
        try {
            listA.get(8);
            assertTrue(false, "getElem() should have thrown Exception");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "index 8");
        }
    }

    @Test
    void indexOfInputMismatchTest() {
        String [] sample = new String [] {"a", "b", "c"};
        IndexableList<String> listA = new IndexableList<String>(sample);
        try {
            listA.indexOf(99);
        } catch (InputMismatchException e) {
            assertEquals(e.getMessage(), "99");
        }
    }

}

