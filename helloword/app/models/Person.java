package models;

import play.data.format.Formats;
import play.db.ebean.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by b4uloveme on 7/11/15.
 */

@Entity
public class Person extends Model {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    public String name;
    public int age;
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dob;



    public static Finder<Integer,Person> find = new Finder<Integer,Person>(
           Integer.class, Person.class
    );
}
