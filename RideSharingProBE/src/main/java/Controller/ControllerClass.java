/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author TECHNOLOGY CITY
 */
import Model.User;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class ControllerClass {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "newjsp";
    }
    //-------------------------------------------------------------------

    @RequestMapping(value = "/SignOut", method = RequestMethod.GET)
    public String signOut(Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("User");
//        model.addAttribute("user", new User());
        return "redirect:/login.htm";
    }

    //-------------------------------------------------------------------
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute(value = "user") @Valid User u, BindingResult br, Model m, HttpServletRequest request) {

        if (!u.getUserName().equals("") || !u.getPassword().equals("")) {

            String url = "http://localhost:8084/RideSharingProWS/rest/getUser/" + u.getUserName() + "/" + u.getPassword() + ".json";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url,String.class);

            if (result != null) {

                Gson g = new Gson();

                User us = g.fromJson(result, User.class);

                System.out.println(us.getUserName());

//            m.addAttribute("name", us.getUserName());
                request.getSession().setAttribute("User", us);

                return "success";

            } else {
                return "newjsp";
            }

        } else {
            return "newjsp";
        }

    }

    //-------------------------------------------------------------------
    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    public String pending(Model m, @ModelAttribute(value = "user") @Valid User u, HttpServletRequest request) {

        if (request.getSession().getAttribute("User") != null) {

            String url = "http://localhost:8084/RideSharingProWS/rest/getUser/pending.json";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);

            if (result != null) {

                Gson g = new Gson();

                List us = g.fromJson(result, List.class);

                List<User> users = new ArrayList<User>();

                for (int i = 0; i < us.size(); i++) {

                    Gson gs = new Gson();

                    User user = gs.fromJson(us.get(i).toString(), User.class);

                    users.add(user);

                }

                if (users.size() <= 0) {

                    m.addAttribute("noUsers", "There is no users");
                    m.addAttribute("name", u.getUserName());

                    return "success";

                }

                m.addAttribute("users", users);

                return "PendingUsers";
            } else {
                m.addAttribute("noUsers", "There is no users");
                m.addAttribute("name", u.getUserName());

                return "success";
            }
        } else {
            return "redirect:/login.htm";
        }
    }
    //-------------------------------------------------------------------

    @RequestMapping(value = "/Deny", method = RequestMethod.GET)
    public String AcceptReq(@RequestParam("id") int id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("User") != null) {

            String url = "http://localhost:8084/RideSharingProWS/rest/getUser/DenyReq/" + id + ".json";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);

            if (result != null) {

                return "redirect:/pending.htm";

            } else {
                return "success";
            }

        } else {
            return "redirect:/login.htm";
        }
    }
    //-------------------------------------------------------------------

    @RequestMapping(value = "/Accept", method = RequestMethod.GET)
    public String DenyReq(@RequestParam("id") int id, Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("User") != null) {
            String url = "http://localhost:8084/RideSharingProWS/rest/getUser/AcceptReq/" + id + ".json";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);

            if (result != null) {

                return "redirect:/pending.htm";

            } else {
                return "login";
            }
        } else {
            return "redirect:/login.htm";
        }

    }

    //------------------------------------------------------------------
    @RequestMapping(value = "/DeniedUsers", method = RequestMethod.GET)
    public String Denied(Model m, @ModelAttribute(value = "user") @Valid User u, HttpServletRequest request) {

        String url = "http://localhost:8084/RideSharingProWS/rest/getUser/DenyUsers.json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        if (result != null) {

            Gson g = new Gson();

            List us = g.fromJson(result, List.class);

            List<User> users = new ArrayList<User>();

            for (int i = 0; i < us.size(); i++) {

                Gson gs = new Gson();

                User user = gs.fromJson(us.get(i).toString(), User.class);

                users.add(user);

            }

            if (users.size() <= 0) {

                m.addAttribute("noUsers", "There is no users");
                m.addAttribute("name", u.getUserName());

                return "success";

            }

            m.addAttribute("DenyUsers", users);

            return "DeniedUsers";
        } else {
            m.addAttribute("noUsers", "There is no users");
            m.addAttribute("name", u.getUserName());

            return "success";
        }

    }

}
