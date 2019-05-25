import spark.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", Client.all());
            model.put("template", "templates/view.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/menu", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", Client.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", Client.all());
            model.put("template", "templates/categories.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/tasks/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/categories/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("client", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        /* Another Path the post method*/



        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", Client.all());
            model.put("template", "templates/index.vtl");
            Client client = Client.find(Integer.parseInt(request.queryParams("clientId")));
            String description = request.queryParams("description");
            Stylist newStylist = new Stylist(description,  client.getId());
            newStylist.save();
            model.put("client", client);
            model.put("template", "templates/client-stylist-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            Client newClient = new Client(name);
            newClient.save();
            model.put("template", "templates/client-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //part for updates...
        get("/clients/:client_id/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":client_id")));
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("client", client);
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        //Post

        post("/clients/:client_id/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
            String description = request.queryParams("description");
            Client client = Client.find(stylist.getClientId());
            stylist.update(description);
            String url = String.format("/clients/%d/stylists/%d", client.getId(), stylist.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        //deleting a post...



        post("/categories/:category_id/tasks/:id/delete", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
            Client client = Client.find(stylist.getClientId());
            stylist.delete();
            model.put("category", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}