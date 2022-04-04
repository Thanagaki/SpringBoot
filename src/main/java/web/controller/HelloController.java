package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


import java.util.ArrayList;
import java.util.List;

@RequestMapping("/users")
@Controller
public class HelloController {

    private final UserService userService;

    public HelloController (UserService userService) {
        this.userService = userService;
    }


	@GetMapping()
	public String findAll (Model model) {
        model.addAttribute("users",  userService.findAll());
		return "AllUsers";
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable("id") long id, Model model ) {
		model.addAttribute("userId", userService.findById(id));
		return "UserId";
	}

	//Форма для создания нового ползователя
	@GetMapping("/new")
		public String newPerson(@ModelAttribute("newUser") User emptyUser) {
		return "new";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("newUser") User user) {
		userService.create(user);
		return "redirect:/users";
	}
	//Форма для редактирования выбранного пользователья
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") long id) {
		model.addAttribute("userUpdate", userService.findById(id));
		return "edit";
	}
	//Метод принимающий форму отредактированного пользователя
	// и делающий перевод на стриницу со всеми пользователями
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("userUpdate") User user, @PathVariable("id") long id) {
		userService.update(user,id);
		return "redirect:/users";
	}


	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		userService.deleteById(id);
		return "redirect:/users";
	}
}