//package maciej.grochowski.grocerystore.error;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//@RestController
//public class CustomErrorController implements ErrorController {
//
//    private final String PATH = "/error";
//
//    @GetMapping(PATH)
//    public ModelAndView error() {
//        return new ModelAndView("/error");
//    }
//
//    @GetMapping("/403")
//    public ModelAndView error403(){
//        return new ModelAndView("/403");
//    }
//
//    @Override
//    public String getErrorPath() {
//        return null;
//    }
//}
