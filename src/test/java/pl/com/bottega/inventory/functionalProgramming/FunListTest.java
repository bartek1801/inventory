package pl.com.bottega.inventory.functionalProgramming;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class FunListTest {

    @Test
    public void addsElementToEmptyList(){
        FunList<String> list = FunList.empty();

        FunList newList = list.add("element");

        assertThat(newList.size()).isEqualTo(1);
    }


    @Test
    public void addElementsToEmptyList(){
        FunList<Integer> list = FunList.empty();

        FunList<Integer> newList = list.add(1).add(2).add(3);

        assertThat(newList.size()).isEqualTo(3);

    }


    @Test
    public void removeElementsFromNonEmptyLIst(){
        FunList<Integer> list = FunList.empty();

        FunList<Integer> newList = list.add(1).add(2).add(2).remove(1).remove(2);

        assertThat(newList.size()).isEqualTo(1);
    }

    @Test
    public void removeElementsFromTheMiddleNonEmptyLIst(){
        FunList<Integer> list = FunList.empty(Integer.class).add(1).add(2).add(3);

        FunList newList = list.remove(1).remove(2);

        assertThat(newList.size()).isEqualTo(1);
    }

    @Test
    public void  canHoldNullValues(){
        FunList list = FunList.empty().add(1).add(null).add(2).remove(null);

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void  isGeneric(){
        FunList<Integer> intList = FunList.empty();

        intList.add(1);
    }

    @Test
    public void iteratesOverEachElement(){
        FunList<String> strings = FunList.empty(String.class).add("1").add("2").add("3");

        StringBuilder stringBuilder = new StringBuilder();
        //strings.each(string -> stringBuilder.append(string));
        strings.each(stringBuilder::append);

        assertThat(stringBuilder.toString()).isEqualTo("123");
    }

    @Test
    public void mapsLists(){
        FunList<String> strings = FunList.empty(String.class).add("a").add("b").add("c");

        FunList<String> upperStrings = strings.map(String::toUpperCase);

        assertThat(upperStrings).isEqualTo(FunList.empty(String.class).add("A").add("B").add("C"));
    }
    
    @Test
    public void foldsLeft(){
        FunList<String> strings = FunList.empty(String.class).add("a").add("b").add("c");
        
        String concatenated = strings.foldLeft("", (acc, string) -> acc + string );
        Integer lengthSum = strings.foldLeft(0, (acc, string) -> acc + string.length() );

        assertThat(concatenated).isEqualTo("abc");
        assertThat(lengthSum).isEqualTo(3);
    }

    @Test
    public void foldsLeftWithIntegers(){
        FunList<Integer> integerFunList = FunList.empty(Integer.class).add(1).add(2).add(3);

        Integer fold = integerFunList.foldLeft(0, (acc, number) -> acc + number );

        assertThat(fold).isEqualTo(6);
    }

    @Test
    public void filtersElements(){
        FunList<Integer> ints = FunList.empty(Integer.class).add(1).add(2).add(3).add(4).add(5);

        FunList<Integer> odds = ints.filter(n -> n % 2 == 1);

        assertThat(odds).isEqualTo(FunList.empty(Integer.class).add(1).add(3).add(5));
    }

}
