package ru.alex.svalka.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ru.alex.svalka.model.Person;
import ru.alex.svalka.service.PersonDao;

@Controller
public class PersonController {
	private static final Logger logger = LoggerFactory
			.getLogger(PersonController.class);
 
    @Autowired
    private PersonDao personDao;
 
    @RequestMapping(value="/person")
    public ModelAndView personbook(HttpServletRequest request) {
        // Handle a new family (if any):
        String name = request.getParameter("name");
        String family = request.getParameter("family");
        if (name != null && family != null){
        	logger.trace("Newcomer {} {}", name, family);
        	personDao.save(new Person(name, family));
        }
 
        // Prepare the result view (person.jsp):
        return new ModelAndView("person.jsp", "personDao", personDao);
    }

}
