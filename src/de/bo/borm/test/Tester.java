package de.bo.borm.test;

import de.bo.borm.*;
import java.sql.SQLException;
import java.util.*;
import java.sql.Date;

/**
 * This class contains a few test cases for BORM.  It requires a mysql database.
 *
 * @author sma@baltic-online.de
 * @version 1.0
 */
public class Tester {

    static void loadDriver(String className) {
        try {
            Class.forName(className);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    static void useMySQL(String connectString) {
        loadDriver("org.gjt.mm.mysql.Driver");
        try {
            Datastore.current.connect("jdbc:mysql:" + connectString, "", "");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    static void execute(boolean ignoreErrors, String sql) {
        try {
            Datastore.current.execute(sql);
        } catch (SQLException e) {
            if (ignoreErrors)
                return;
            e.printStackTrace();
            System.exit(3);
        }
    }

    static void setupTables() {
        execute(true, "drop table page");
        execute(false, "create table page (id int not null primary key, topic char(100) not null, content text, modified date, editable char(1) not null default 'N', image blob)");
        Datastore.current.register(Page.class);
    }

    public static void main(String[] args) {
        useMySQL("//localhost/test");
        setupTables();

        Page p = new Page();
        p.id = 1;
        p.topic = "Seite 1";
        p.content = "Hallo, wie geht's";
        p.modified = new Date(Calendar.getInstance().getTime().getTime());
        p.editable = false;
        p.image = new Page();

        System.out.println(p);

        Page q = new Page();
        q.id = 1;

        try {
            p.insert();
            q.reload();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(q);
    }


    static class Page extends StorableObject implements java.io.Serializable {

		private static final long serialVersionUID = 1L;

		static java.text.DateFormat fmt = new java.text.SimpleDateFormat("dd-MMM-yyyy HH:mm");

        // persistent attributes
        int id;
        String topic;
        String content;
        Date modified;
        boolean editable;
        Object image;

        public static final String tableName = "page";

        public String toString() {
            return "page [id=" + id
                + ", topic=" + topic
                + ", content=" + content
                + ", modified=" + fmt.format(modified)
                + ", editable=" + editable
//                + ", image=" + image
                + "]";
        }
    }
}