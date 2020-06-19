package db61b;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTable {
    @Test
    public void testcolumns() {
        String[] attributes = {"Age", "Height", "Gender", "Religion"};
        Table gurjy = new Table(attributes);
        assertEquals(4, gurjy.columns());

    }

    @Test
    public void testgettitle() {
        String[] attributes = {"Age", "Height", "Gender", "Religion"};
        Table gurjy = new Table(attributes);
        assertEquals("Age", gurjy.getTitle(0));

    }

    @Test
    public void testgetColumn() {
        String[] attributes = {"Age", "Height", "Gender", "Religion"};
        Table gurjy = new Table(attributes);
        assertEquals(0, gurjy.findColumn("Age"));
    }

    @Test
    public void testadd() {
        String[] gurjus = {"Gurjus", "Hump", "Dumpty"};
        Table gurjy = new Table(gurjus);
        String[] description = {"Hoops", "Cookie", "Bananas"};
        boolean good = gurjy.add(description);
        assertEquals(true, good);
        String[] badstring = {"Hoops", "Cookie", "Bananas"};
        boolean bad = gurjy.add(badstring);
        assertEquals(false, bad);
        String[] goodstring = {"Kings", "Lakers", "Warriors"};
        boolean goodboy = gurjy.add(goodstring);
        assertEquals(true, goodboy);
        String[] animals = {"Zebra", "Ape", "Giraffe"};
        boolean testing = gurjy.add(animals);
        String[] random = {"Jello", "Ape", "Giraffe"};
        gurjy.add(random);
        String[] r = {"Kings", "Ball", "Giraffe"};
        gurjy.add(r);
        String[] d = {"Kings", "Call", "Giraffe"};
        gurjy.add(d);
        gurjy.print();
    }

    @Test
    public void testsize() {
        String[] gurjus = {"Gurjus", "Personality"};
        Table gurjy = new Table(gurjus);
        String[] description = {"Age", "Ambitious"};
        gurjy.add(description);
        String[] description2 = {"Height", "Happy"};
        gurjy.add(description2);
        assertEquals(2, gurjy.size());


    }

    @Test
    public void testPrint() {
        String[] gurjus = {"Gurjus", "Personality", "Hello"};
        Table gurjy = new Table(gurjus);
        String[] description = {"Height", "Funny", "Gurjy"};
        gurjy.add(description);
        String[] descript2 = {"Age", "Adult", "Singh"};
        gurjy.add(descript2);
        String[] descript3 = {"Age", "Goals", "Kings"};
        gurjy.add(descript3);
        String[] descript4 = {"Funny", "Happy", "Good"};
        gurjy.add(descript4);
        gurjy.print();

    }

    @Test
    public void testaddlist() {
        String[] gurjus = {"Gurjus", "Personality"};
        Table gurjy = new Table(gurjus);
        String[] description = {"Height", "Funny"};
        gurjy.add(description);
        String[] descript2 = {"Age", "Adult"};
        gurjy.add(descript2);
        ArrayList<Column> columned = new ArrayList<>();
        Column gurj = new Column("Gurjus", gurjy);
        Column pers = new Column("Personality", gurjy);
        columned.add(gurj);
        columned.add(pers);
        gurjy.print();



    }

    @Test
    public void testaddlist2() {
        String[] gurjus = {"Name", "Department", "Building"};
        Table gurjy = new Table(gurjus);
        String[] description = {"Denero", "CS", "Cory"};
        gurjy.add(description);
        String[] descript2 = {"Lin", "Math", "Evans"};
        gurjy.add(descript2);
        String[] descript3 = {"Hilf", "CS", "Soda"};
        gurjy.add(descript3);
        ArrayList<Column> columned = new ArrayList<>();
        Column gurj = new Column("Name", gurjy);
        Column pers = new Column("Department", gurjy);
        Column build = new Column("Building", gurjy);
        columned.add(gurj);
        columned.add(pers);
        columned.add(build);
        gurjy.add(columned, 2, 2, 1);
        gurjy.add(columned, 1, 0, 0);
        gurjy.print();


    }

    @Test
    public void testget() {
        String[] gurjus = {"Gurjus", "Personality"};
        Table gurjy = new Table(gurjus);
        String[] description = {"Height", "Funny"};
        gurjy.add(description);
        String[] descript2 = {"Age", "Adult"};
        gurjy.add(descript2);
        System.out.println(gurjy.get(0, 0));
        assertEquals("Age", gurjy.get(0, 0));

    }

    @Test
    public void testselect() {
        String[] gurjus = {"Gurjus", "Personality"};
        Table gurjy = new Table(gurjus);
        String[] gurj = {"CS", "Berkeley"};
        gurjy.add(gurj);
        String[] numb = {"3.0", "4.0"};
        gurjy.add(numb);
        String[] locations = {"Soda", "Sproul"};
        gurjy.add(locations);
        Column com = new Column("Gurjus", gurjy);
        Column comb = new Column("Personality", gurjy);
        ArrayList<Column> cols = new ArrayList<>();
        cols.add(com);
        cols.add(comb);
        ArrayList<String> colsd = new ArrayList<>();
        colsd.add("Gurjus");
        colsd.add("Personality");
        Condition hopeful = new Condition(cols.get(0), "!=", "CS");

        ArrayList<Condition> cond = new ArrayList<>();
        cond.add(hopeful);
        Table filtered = gurjy.select(colsd, cond);
        filtered.print();
    }

    @Test
    public void testsselecttables() {
        String[] gurjus = {"Gurjus", "Personality"};
        Table gurjy = new Table(gurjus);
        String[] gurj = {"CS", "Berkeley"};
        gurjy.add(gurj);
        Table gurjyclone = new Table(gurjus);
        String[] gurjc = {"Data", "Science"};
        gurjyclone.add(gurjc);
        String[] gurjyc = {"CS", "Crazy"};
        gurjyclone.add(gurjyc);
        String[] numb = {"3.0", "4.0"};
        gurjy.add(numb);
        String[] locations = {"Soda", "Sproul"};
        gurjy.add(locations);
        Column com = new Column("Gurjus", gurjy);
        Column comb = new Column("Personality", gurjy);
        ArrayList<Column> cols = new ArrayList<>();
        cols.add(com);
        cols.add(comb);
        ArrayList<String> colsd = new ArrayList<>();
        colsd.add("Gurjus");
        colsd.add("Personality");
        Condition hopeful = new Condition(cols.get(0), "!=", "CS");
        ArrayList<Condition> cond = new ArrayList<>();
        cond.add(hopeful);
        Table filtered = gurjy.select(colsd, cond);
        filtered.print();
    }

    @Test
    public void testsecondselect() {
        String[] gurjus = {"Gurjus", "Personality", "Hobbies"};
        Table gurjy = new Table(gurjus);
        String[] gurj = {"Gurjus", "Basketball", "Music"};
        Table tab2 = new Table(gurj);
        String[] row1 = {"Height", "Happy", "Basketball"};
        gurjy.add(row1);
        String[] row2 = {"Height", "Three", "Punjabi"};
        tab2.add(row2);
        String[] row3 = {"Weight", "Funny", "Running"};
        gurjy.add(row3);
        String[] row4 = {"Hands", "Hoops", "Classical"};
        tab2.add(row4);
        String[] row5 = {"Hair", "Ambitious", "Sports"};
        gurjy.add(row5);
        String[] row6 = {"Hair", "Postup", "Drake"};
        tab2.add(row6);
        List<String> names = new ArrayList<>();
        names.add("Gurjus");
        names.add("Basketball");
        names.add("Music");
        names.add("Hobbies");
        List<Condition> cond = new ArrayList<>();
        Column gurjusy = new Column("Gurjus", gurjy);
        Condition getsecond = new Condition(gurjusy, "=", "Hair");
        cond.add(getsecond);
        Table select = gurjy.select(tab2, names, cond);
        select.print();

    }


}
