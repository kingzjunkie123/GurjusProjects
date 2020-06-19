package db61b;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static db61b.Utils.error;

/**
 * A single table in a database.
 *
 * @author P. N. Hilfinger
 */
class Table {
    /**
     * My column titles.
     */
    private final String[] _titles;
    /**
     * My columns. Row i consists of _columns[k].get(i) for all k.
     */
    private final ValueList[] _columns;
    /**
     * Rows in the database are supposed to be sorted. To do so, we
     * have a list whose kth element is the index in each column
     * of the value of that column for the kth row in lexicographic order.
     * That is, the first row (smallest in lexicographic order)
     * is at position _index.get(0) in _columns[0], _columns[1], ...
     * and the kth row in lexicographic order in at position _index.get(k).
     * When a new row is inserted, insert its index at the appropriate
     * place in this list.
     * (Alternatively, we could simply keep each column in the proper order
     * so that we would not need _index.  But that would mean that inserting
     * a new row would require rearranging _rowSize lists (each list in
     * _columns) rather than just one.
     */
    private final ArrayList<Integer> _index = new ArrayList<>();
    /**
     * My number of columns (redundant, but convenient).
     */
    private final int _rowSize;
    /**
     * My number of rows (redundant, but convenient).
     */
    private int _size;

    /**
     * A new Table whose columns are given by COLUMNTITLES, which may
     * not contain duplicate names.
     */
    Table(String[] columnTitles) {
        if (columnTitles.length == 0) {
            throw error("table must have at least one column");
        }
        _size = 0;
        _rowSize = columnTitles.length;

        for (int i = columnTitles.length - 1; i >= 1; i -= 1) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (columnTitles[i].equals(columnTitles[j])) {
                    throw error("duplicate column name: %s",
                            columnTitles[i]);
                }
            }
        }

        _titles = columnTitles;
        _columns = new ValueList[columnTitles.length];
        for (int i = 0; i < _rowSize; i++) {
            _columns[i] = new ValueList();
        }
    }

    /**
     * A new Table whose columns are give by COLUMNTITLES.
     */
    Table(List<String> columnTitles) {
        this(columnTitles.toArray(new String[columnTitles.size()]));
    }

    /**
     * Add a new row whose column values are VALUES to me if no equal
     * row already exists.  Return true if anything was added,
     * false otherwise.
     */

    /**
     * Classmate helped me. RETURNS TABLE, TAKES IN STRING NAME.
     **/
    static Table readTable(String name) {
        BufferedReader input;
        Table table;
        input = null;
        table = null;
        try {
            input =
                new BufferedReader(new FileReader(name + ".db"));
            String header = input.readLine();
            if (header == null) {
                throw error("missing header in DB file");
            }
            String[] columnNames = header.split(",");
            String anotherone = input.readLine();
            table = new Table(columnNames);
            while (anotherone != null) {
                String[] row = anotherone.split(",");
                table.add(row);
            }
        } catch (FileNotFoundException e) {
            throw error("could not find %s.db", name);
        } catch (IOException e) {
            throw error("problem reading from %s.db", name);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    /* Ignore IOException */
                }
            }
        }
        return table;
    }

    /**
     * Got significant help from my tutor.
     * Takes in List of COLUMN TYPE COMMON2
     * from Table 1, Common Columns of COMMON1 from Table2 and
     * INT ROW1 AND INT ROW2. RETURNS BOOLEAN.
     */
    private static boolean equijoin(List<Column> common1, List<Column> common2,
                                    int row1, int row2) {
        for (int i = 0; i < common1.size(); i++) {
            if (!common1.get(i).getFrom(row1)
                    .equals(common2.get(i).getFrom(row2))) {
                return false;

            }

        }

        return true;
    }

    /**
     * Did this helper function to clean up code. TAKES IN INT SIZE.
     **/
    public void compareindex(int size) {
        for (int k = 0; k < size - 1; k++) {
            int newrows = compareRows(size - 1, _index.get(k));
            if (newrows < 0) {
                _index.add(k, size - 1);
                return;
            }


        }
        _index.add(size - 1, size - 1);
    }


    /**
     * Add a new row whose column values are extracted by COLUMNS from
     * the rows indexed by ROWS, if no equal row already exists.
     * Return true if anything was added, false otherwise. See
     * Column.getFrom(Integer...) for a description of how Columns
     * extract values.
     */

    /**
     * Return true if the columns COMMON1 from ROW1 and COMMON2 from
     * ROW2 all have identical values.  Assumes that COMMON1 and
     * COMMON2 have the same number of elements and the same names,
     * that the columns in COMMON1 apply to this table, those in
     * COMMON2 to another, and that ROW1 and ROW2 are indices, respectively,
     * into those tables.
     * <p>
     * <p>
     * /**
     * Return the number of columns in this table.
     */
    public int columns() {
        return _rowSize;
    }


    /**
     * Read the contents of the file NAME.db, and return as a Table.
     * Format errors in the .db file cause a DBException.
     */

    /**
     * Return the title of the Kth column.  Requires 0 <= K < columns().
     */
    public String getTitle(int k) {
        if (k >= 0 && k < columns()) {
            return _titles[k];
        } else {
            throw error("Not in Index");
        }

    }

    /**
     * Write the contents of TABLE into the file NAME.db. Any I/O errors
     * cause a DBException.
     */

    /**
     * Return the number of the column whose title is TITLE, or -1 if
     * there isn't one.
     */
    public int findColumn(String title) {
        for (int i = 0; i < columns(); i++) {
            if (title.equals(getTitle(i))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Print my contents on the standard output, separated by spaces
     * and indented by two spaces.
     */

    /**
     * Return the number of rows in this table.
     */
    public int size() {
        return _size;
    }

    /**
     * Return the value of column number COL (0 <= COL < columns())
     * of record number ROW (0 <= ROW < size()).
     */
    public String get(int row, int col) {
        try {
            return _columns[col].get(_index.get(row));
        } catch (IndexOutOfBoundsException excp) {
            throw error("invalid row or column");

        }
    }

    /**
     * got significant help from a tutor on the add method. RETURNS BOOLEAN AND
     * TAKES IN STRING ARRAY VALUES.
     **/
    public boolean add(String[] values) {
        if (size() == 0) {
            int i = 0;
            while (i < values.length) {
                _columns[i].add(values[i]);
                i++;

            }
            _index.add(0, 0);
            _size++;
            return true;
        }


        if (size() > 0) {
            if (!checkvalues(values)) {
                for (int i = 0; i < _columns.length; i++) {
                    _columns[i].add(values[i]);
                }

            } else {
                return false;
            }

        }

        _size++;
        compareindex(size());
        return true;
    }

    /**
     * Made a new helper according to a class mate. RETURNS BOOLEAN
     * TAKES IN STRING ARRAY VALUES.
     **/
    public boolean checkvalues(String[] values) {
        for (int i = 0; i < size(); i++) {
            boolean access = true;
            for (int j = 0; j < values.length; j++) {
                if (!values[j].equals(_columns[j].get(i))) {
                    access = false;
                }

            }
            if (access) {
                return true;
            }


        }

        return false;


    }

    /**
     * Understood this but got help from a tutor. RETURNS BOOLEAN and TAKES IN
     * LIST TYPE COLUMN NAMED COLUMNS AND ROWS.
     **/
    public boolean add(List<Column> columns, Integer... rows) {
        String[] val = new String[columns()];
        for (int i = 0; i < columns.size(); i++) {
            val[i] = columns.get(i).getFrom(rows);

        }


        return add(val);

    }

    /**
     * My classmate helped me on this.
     * TAKES IN STRING NAME
     */
    void writeTable(String name) {
        PrintStream output;
        output = null;
        try {
            output = new PrintStream(name + ".db");
            for (int i = 0; i < columns(); i++) {
                if (i == columns() - 1) {
                    output.print(_titles[i]);
                } else {
                    output.print(_titles[i]);
                    output.print(",");
                }
            }
            for (int r = 0; r < size(); r++) {
                output.println();
                for (int c = 0; c < columns(); c++) {
                    if (c == columns() - 1) {
                        output.print(get(r, c));
                    } else {
                        output.println(get(r, c));
                        output.print(",");
                    }
                }
            }
        } catch (IOException e) {
            throw error("trouble writing to %s.db", name);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * Got significant help from my tutor.
     **/
    void print() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < columns(); j++) {
                System.out.print(" " + _columns[j].get(_index.get(i)));
                if (j == columns() - 1) {
                    break;
                }
                System.out.print(" ");
            }

            System.out.println(" ");

        }


    }

    /**
     * Return a new Table whose columns are COLUMNNAMES, selected from
     * rows of this table that satisfy CONDITIONS.
     */
    /**
     * Got significant help from my tutor.
     * RETURNS TABLE, TAKES IN COLUMNNAMES AND CONDITIONS.
     **/
    Table select(List<String> columnNames, List<Condition> conditions) {
        Table result = new Table(columnNames);
        for (int i = 0; i < size(); i++) {
            boolean cond = Condition.test(conditions, i);
            if (cond) {
                String[] val = new String[columnNames.size()];
                for (int j = 0; j < columnNames.size(); j++) {
                    int colindx = findColumn(columnNames.get(j));
                    val[j] = get(i, colindx);

                }
                result.add(val);

            }
        }
        return result;

    }

/**
 * Return a new Table whose columns are COLUMNNAMES, selected
 * from pairs of rows from this table and from TABLE2 that match
 * on all columns with identical names and satisfy CONDITIONS.
 */
    /**
     * Got significant help from a tutor on the select method.
     * Takes in TABLE2 TYPE TABLE
     * TAKES IN LIST COLUMN TYPE OF
     * COLUMNNAMES AND TAKES IN LIST OF TYPE CONDITION OF CONDITIONS.
     * RETURNS A TABLE
     */
    Table select(Table table2, List<String> columnNames,
                 List<Condition> conditions) {
        Table result = new Table(columnNames);
        List<Column> cols = new ArrayList<>();
        List<Column> c1 = new ArrayList<>();
        List<Column> c2 = new ArrayList<>();

        for (String title : columnNames) {
            cols.add(new Column(title, this, table2));
        }
        for (int j = 0; j < this.columns(); j++) {
            int i = table2.findColumn(getTitle(j));
            if (i >= 0) {
                c1.add(new Column(this.getTitle(j), this));
                c2.add(new Column(table2.getTitle(i), table2));
            }
        }
        for (int i = 0; i < size(); i++) {
            for (int k = 0; k < table2.size(); k++) {
                boolean cond = Condition.test(conditions, i, k);
                if (cond && equijoin(c1, c2, i, k)) {
                    result.add(cols, i, k);
                }
            }
        }
        return result;

    }


    /**
     * Return <0, 0, or >0 depending on whether the row formed from
     * the elements _columns[0].get(K0), _columns[1].get(K0), ...
     * is less than, equal to, or greater than that formed from elememts
     * _columns[0].get(K1), _columns[1].get(K1), ....  This method ignores
     * the _index.
     */


    private int compareRows(int k0, int k1) {
        for (int i = 0; i < _columns.length; i += 1) {
            int c = _columns[i].get(k0).compareTo(_columns[i].get(k1));
            if (c != 0) {
                return c;
            }
        }
        return 0;
    }

    /**
     * A class that is essentially ArrayList<String>.  For technical reasons,
     * we need to encapsulate ArrayList<String> like this because the
     * underlying design of Java does not properly distinguish between
     * different kinds of ArrayList at runtime (e.g., if you have a
     * variable of type Object that was created from an ArrayList, there is
     * no way to determine in general whether it is an ArrayList<String>,
     * ArrayList<Integer>, or ArrayList<Object>).  This leads to annoying
     * compiler warnings.  The trick of defining a new type avoids this
     * issue.
     */
    private static class ValueList extends ArrayList<String> {
    }
}
