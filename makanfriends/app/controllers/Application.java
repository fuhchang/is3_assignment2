package controllers;

import play.data.Form;
import play.mvc.*;
import play.*;
import views.html.*;
import models.MakanUser;
import java.net.*;
import java.io.*;
public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result test() {

        try {
           // URL _url = new URL("https://api.projectnimbus.org/hgwodataservice.svc/RestaurantSet?Latitude=1.3&Longitude=103.85&Distance=2000");
            //URLConnection _urlConn = _url.openConnection();
            //_urlConn.setRequestProperty("accept", "*/*");
            //_urlConn.addRequestProperty("AccountKey", "NimbusAdmin");
            //_urlConn.addRequestProperty("UniqueUserID", "A66D951F49F5463B040CFAA2FD553c");
            //BufferedReader br = new BufferedReader(new InputStreamReader(_urlConn.getInputStream()));
            //String line = null;
            //StringBuilder strBuilder = new StringBuilder();
            //while ((line = br.readLine()) != null) {
               // strBuilder.append(line);
                //Logger.info(line);
            //}

            URL url = new URL("https://api.yelp.com/v2/search/?location=Singapore&limit=20&cc=SG");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            Logger.info("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                Logger.info("new line " + output);
            }
            conn.disconnect();
        }catch(Exception e){
            Logger.info("error " + e);
        }

        return ok(test.render());
    }
    public Result addMakanUser(){

        MakanUser user = Form.form(MakanUser.class).bindFromRequest().get();
        user.save();
        return redirect(routes.Application.index());
    }
}
