package estateagency.controller;

import estateagency.dto.PageWrapper;
import estateagency.dto.realtors.ChangePasswordForm;
import estateagency.dto.realtors.RealtorDto;
import estateagency.dto.realtors.NewRealtorForm;
import estateagency.service.RealtorsService;
import estateagency.service.exceptions.NotUniqueUserName;
import estateagency.service.exceptions.RemovingErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/realtors")
public class RealtorsController {


    private final RealtorsService realtorsService;

    @Autowired
    public RealtorsController(RealtorsService realtorsService) {
        this.realtorsService = realtorsService;
    }


    /**
     * Показать страницу с пользователями
     */
    @GetMapping
    public String findAllRealtors(Model model, Pageable pageable) {
        model.addAttribute("page", new PageWrapper<>(realtorsService.findAllRealtors(pageable)));
        return "views/realtors/realtors";
    }

    /**
     * Показать страницу создания нового пользователя
     */
    @GetMapping("/create")
    public String editRealtorGET(Model model) {
        model.addAttribute("form", new NewRealtorForm());
        return "views/realtors/create_realtor";
    }

    /**
     * Создать нового пользователя
     *
     * @param form Данные введенные пользователем
     * @return перенаправление на список пользователей
     */
    @PostMapping("/create")
    public String createRealtorPOST(@Valid @ModelAttribute("form") NewRealtorForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("form", form);
            return "views/realtors/create_realtor";
        }
        realtorsService.createRealtor(form);
        return "redirect:/realtors";
    }

    /**
     * Отображает страницу редактирования риелтора
     */
    @GetMapping("/edit/{realtorId}")
    public String editRealtor(@PathVariable Long realtorId, Model model) {
        RealtorDto realtorDto = realtorsService.findRealtorById(realtorId);
        model.addAttribute("realtorDto", realtorDto);
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "views/realtors/edit_realtor";
    }

    /**
     * Этот метод вносит изменения введенные пользователем в риелтора
     */
    @PostMapping("/edit")
    public String updateRealtor(@Valid @ModelAttribute("realtorDto") RealtorDto realtorDto,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("realtorDto", realtorDto);
            model.addAttribute("changePasswordForm", new ChangePasswordForm());
            return "views/realtors/edit_realtor";
        }
        try {
            realtorsService.updateRealtor(realtorDto);
        } catch (NotUniqueUserName notUniqueUserName) {
            result.rejectValue("username", "message.errors.non_unique_username", "User already exist");
            model.addAttribute("realtorDto", realtorDto);
            model.addAttribute("changePasswordForm", new ChangePasswordForm());
            return "views/realtors/edit_realtor";
        }
        return "redirect:/realtors";
    }


    /**
     * Производит поиск по ключевому сочетанию символов.
     * Выводи строки из таблицы в любых полях которой встечается заданное сочетание
     * @param search сочетание символов
     * @param id id риелтора, если он был выбран для просмотра
     * @param pageable объект описывающий какую страницу нужно вывести, и сколько в ней должно быть строк
     */
    @GetMapping("/search")
    public String searchRealtors(@RequestParam  (name = "search", required = false) String search,
                                 @RequestParam(name = "id", required = false) Long id,
                                 Model model,
                                 Pageable pageable) {
        model.addAttribute("page", new PageWrapper<>(realtorsService.searchRealtors(search, pageable)));
        model.addAttribute("selectedRealtor", (id == null) ? null : realtorsService.findRealtorById(id));
        model.addAttribute("search", search);
        return "views/realtors/realtors";
    }


    /**
     * Метод изменяет пароль пользователя
     */
    @PostMapping("/change_password/{id}")
    public String changePassword(@PathVariable Long id,
                                 @Valid @ModelAttribute ChangePasswordForm passwordForm,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            RealtorDto realtorDto = realtorsService.findRealtorById(id);
            model.addAttribute("realtorDto", realtorDto);
            model.addAttribute("changePasswordForm",passwordForm);
            return "views/realtors/edit_realtor";
        }
        this.realtorsService.changePassword(passwordForm.getPassword(), id);
        return "redirect:/realtors/edit/" + id;
    }

    /**
     * Удаление польтеля
     * @param id id пользователя
     */
    @GetMapping("/remove/{id}")
    public String  getDeleteRealtor(@PathVariable Long id,
                                    Model model) {
        try {
            realtorsService.removeRealtorById(id);
        } catch (RemovingErrorException e) {
            model.addAttribute("removeError", true);
            RealtorDto realtorDto = realtorsService.findRealtorById(id);
            model.addAttribute("realtorDto", realtorDto);
            model.addAttribute("changePasswordForm", new ChangePasswordForm());
            return "views/realtors/edit_realtor";
        }
        return "redirect:/realtors";
    }

    @GetMapping("/edit")
    public String forI18n() {
        return "redirect:/realtors";
    }
}
