package controllers;

import play.data.Form;
import play.mvc.*;
import play.*;
import views.html.*;
import models.MakanUser;
import java.net.*;
import java.io.*;
public class HomeController extends Controller {

    public Result index() {
        return ok(HomePage.render());
    }
}
