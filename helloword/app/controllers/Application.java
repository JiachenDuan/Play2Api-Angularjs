package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.Person;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static play.libs.Json.toJson;

public class Application extends Controller {


//    public  Result addPerson() {
//        Person person = Form.form(Person.class).bindFromRequest().get();
//        person.save();
//        return redirect(routes.Application.index());
//    }

    /*
    * Define any extra CORS headers needed for option requests (see http://enable-cors.org/server.html for more info)
    */
//    public Result preflight(String all) {
//        response().setHeader("Access-Control-Allow-Origin", "*");
//        response().setHeader("Allow", "*");
//        response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
//        response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
//        return ok();
//
//
//    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result  addPerson() throws ParseException {

        JsonNode jsonNode = Controller.request().body().asJson();
        String name  = jsonNode.findPath("name").asText();
        int age =  jsonNode.findPath("age").asInt();
        String dob = jsonNode.findPath("dob").asText();
        Person person = new Person();
        person.name = name;
        person.age = age;


        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = format.parse(dob);
        person.dob = date;
        person.save();
        return Results.created();


    }

    public  Result index() {
        return ok(index.render("Hellow World"));
    }

    public Result getPersons(){
        List<Person> persons = Person.find.all();
        return ok(toJson(persons));
    }



}
