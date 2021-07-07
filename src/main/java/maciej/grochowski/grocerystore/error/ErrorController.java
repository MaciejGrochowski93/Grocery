//package maciej.grochowski.grocerystore.error;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//
//@ControllerAdvice
//public class ErrorController {
//
//    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
//
////    @ExceptionHandler(Throwable.class)
////    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
////    public ModelAndView exception(final Throwable throwable, final Model model) {
////        logger.error("Exception during execution of SpringSecurity application", throwable);
////
////        ModelAndView modelAndView = new ModelAndView("/error");
////        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
////        modelAndView.addObject("errorMsg", errorMessage);
////        return modelAndView;
////    }
//
//    @ExceptionHandler(Throwable.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String exception(Throwable throwable, Model model, HttpServletRequest httpRequest) {
//        logger.error("Exception during execution of shopping application", throwable);
////        String noMoneyErrorMsg = (throwable != null ? throwable.getMessage() : "You don't have enough money to pay for the products22222.");
//        String productErrorMessage = (throwable != null ? throwable.getMessage() : "Please, make sure that all Product parameters are correct22222222!");
////        String wrongPageMessage = (throwable != null ? throwable.getMessage() : "Woops, looks like this page doesn't exist!");
//
////        model.addAttribute("noMoneyErrorMsg", noMoneyErrorMsg);
//        model.addAttribute("incorrectProductData", productErrorMessage);
////        model.addAttribute("wrongPageMessage", wrongPageMessage);
//
//    //        var error = new ModelAndView("error");
//            String errorMsg = "";
//            int httpErrorCode = getErrorCode(httpRequest);
//
//            switch (httpErrorCode) {
//                case 400: {
//                    errorMsg = "Http Error Code: 400. Bad Request";
//                    break;
//                }
//                case 401: {
//                    errorMsg = "Http Error Code: 401. Unauthorized";
//                    break;
//                }
//                case 404: {
//                    errorMsg = "Http Error Code: 404. Resource not found";
//                    break;
//                }
//                case 500: {
//                    errorMsg = "Http Error Code: 500. Internal Server Error";
//                    break;
//                }
//            }
//
//            model.addAttribute("400", errorMsg);
//
//        return "error";
//    }
//
////    @GetMapping(value = "/error")
////    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
////
////        var error = new ModelAndView("error");
////        String errorMsg = "";
////        int httpErrorCode = getErrorCode(httpRequest);
////
////        switch (httpErrorCode) {
////            case 400: {
////                errorMsg = "Http Error Code: 400. Bad Request";
////                break;
////            }
////            case 401: {
////                errorMsg = "Http Error Code: 401. Unauthorized";
////                break;
////            }
////            case 404: {
////                errorMsg = "Http Error Code: 404. Resource not found";
////                break;
////            }
////            case 500: {
////                errorMsg = "Http Error Code: 500. Internal Server Error";
////                break;
////            }
////        }
////
//////        model.addAttribute("400", httpErrorCode);
////        error.addObject("400", errorMsg);
////        return error;
////    }
//
//    private int getErrorCode(HttpServletRequest httpRequest) {
//        return (Integer) httpRequest
//                .getAttribute("javax.servlet.error.status_code");
//    }
//}
