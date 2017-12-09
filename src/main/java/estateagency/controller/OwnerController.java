package estateagency.controller;

import estateagency.dto.flats.FlatDto;
import estateagency.dto.PageWrapper;
import estateagency.dto.owners.OwnerDto;
import estateagency.service.FlatService;
import estateagency.service.OwnerService;
import estateagency.service.exceptions.RemovingErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final FlatService flatService;

    @Autowired
    public OwnerController(OwnerService ownerService, FlatService flatService) {
        this.ownerService = ownerService;
        this.flatService = flatService;
    }


    /**
     * Показать страницу со списком владельцев
     */
    @GetMapping
    public String findAll(Model model, Pageable pageable) {
        model.addAttribute("flatsList", new ArrayList<FlatDto>());
        model.addAttribute("page", new PageWrapper<>(this.ownerService.findAllOwners(pageable)));
        return "views/owners/owners";
    }

    /**
     * Производит поиск по ключевому сочетанию символов.
     * Выводи строки из таблицы в любых полях которой встечается заданное сочетание
     *
     * @param search   сочетание символов
     * @param id       id владельца, если он был выбран для просмотра
     * @param pageable объект описывающий какую страницу нужно вывести, и сколько в ней должно быть строк
     */
    @GetMapping("/search")
    public String searchOwners(@RequestParam(name = "search", required = false) String search,
                               @RequestParam(name = "id", required = false) Long id,
                               Model model,
                               Pageable pageable) {
        model.addAttribute("page", new PageWrapper<>(this.ownerService.findOwnerByCriteria(pageable, search)));
        model.addAttribute("selectedOwner", (id == null) ? null : this.ownerService.findOwnerById(id));
        List<FlatDto> flatsList = this.flatService.findByOwnerId(id);
        model.addAttribute("flatsList", (id == null) ? new ArrayList<FlatDto>() : flatsList);
        model.addAttribute("search", search);
        return "views/owners/owners";
    }

    /**
     * Отобразить сраницу создания владельца
     */
    @GetMapping("/create")
    public String createOwner(Model model) {
        model.addAttribute("ownerDto", new OwnerDto());
        return "views/owners/create_owner";
    }

    /**
     * Принимает данные о владельце и сохраняет их
     */
    @PostMapping("/create")
    public String saveNewOwner(@Valid @ModelAttribute OwnerDto ownerDto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ownerDto", ownerDto);
            return "views/owners/create_owner";
        }
        this.ownerService.saveOwner(ownerDto);
        return "redirect:/owners";
    }

    /**
     * Отображает страницу редактирования владельца
     */
    @GetMapping("/edit/{ownerId}")
    public String editOwner(@PathVariable(value = "ownerId", required = false) Long id,
                            Model model) {
        model.addAttribute("ownerDto", this.ownerService.findOwnerById(id));
        return "views/owners/edit_owner";
    }

    /**
     * Сохраняет ввесенные пользователем изменения
     */
    @PostMapping("/edit")
    public String save(@Valid @ModelAttribute OwnerDto ownerDto,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ownerDto", ownerDto);
            return "views/owners/edit_owner";
        }
        ownerService.saveOwner(ownerDto);
        return "redirect:/owners";
    }

    /**
     * Удаление владельца
     */
    @GetMapping("/remove/{ownerId}")
    public String getDeleteOwner(@PathVariable("ownerId") Long ownerId,
                                 Model model) {

        try {
            this.ownerService.removeOwnerById(ownerId);
        } catch (RemovingErrorException e) {
            model.addAttribute("removeError", true);
            model.addAttribute("ownerDto", this.ownerService.findOwnerById(ownerId));
            return "views/owners/edit_owner";
        }




         return "redirect:/owners";
    }


    @GetMapping("/api/search")
    @ResponseBody
    public PageWrapper<OwnerDto> searchRealtors(@RequestParam(value = "search", required = false) String search,
                                                Pageable pageable) {
        return new PageWrapper<>(ownerService.findOwnerByCriteria(pageable, search));
    }

    @GetMapping("/api/getOne")
    @ResponseBody
    public OwnerDto selectRealtor(@RequestParam("id") Long id) {
        return ownerService.findOwnerById(id);
    }

    @GetMapping("/edit")
    public String forI18n() {
        return "redirect:/owners";
    }
}
