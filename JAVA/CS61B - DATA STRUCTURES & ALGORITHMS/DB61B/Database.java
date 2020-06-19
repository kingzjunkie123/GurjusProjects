package db61b;

import java.util.HashMap;

/**
 * A collection of Tables, indexed by name.
 *
 * @author Gurjus Singh
 */
class Database {

    /**
     * Used for storing Tables.
     **/
    private HashMap<String, Table> tabs;

    /**
     * An empty database.
     */
    public Database() {
        tabs = new HashMap<String, Table>();
    }

    /**
     * Return the Table whose name is NAME stored in this database, or null
     * if there is no such table.
     */
    public Table get(String name) {
        if (tabs.get(name) == null) {
            return null;
        }
        return tabs.get(name);
    }

    /**
     * Set or replace the table named NAME in THIS to TABLE.  TABLE and
     * NAME must not be null, and NAME must be a valid name for a table.
     */
    public void put(String name, Table table) {
        if (name == null || table == null) {
            throw new IllegalArgumentException("null argument");
        }
        tabs.put(name, table);
    }
}
