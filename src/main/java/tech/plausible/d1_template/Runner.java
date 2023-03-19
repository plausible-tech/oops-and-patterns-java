package tech.plausible.d1_template;


import java.util.HashMap;
import java.util.Map;

class Request {

}

class Response {
    final String name;

    Response(String name) {
        this.name = name;
    }
}

// Parent
abstract class View {
    public void handle() {
        this.validate();
        System.out.println(this.respond().name);
    }

    abstract boolean validate();
    abstract Response respond();
}

// Child
class HomeView extends View {

    @Override
    boolean validate() {
        return true;
    }

    @Override
    Response respond() {
        return new Response("Home");
    }
}

class AboutView extends View {

    @Override
    boolean validate() {
        return true;
    }

    @Override
    Response respond() {
        throw new RuntimeException("You messed up");
    }
}

class Runner {
    static Map<String, View> routes = new HashMap<>();

    public static void main(String[] args) {
        routes.put("/home", new HomeView());
        routes.put("/about", new AboutView());

        final View handler = routes.get("/about");
        handler.handle();
    }
}
