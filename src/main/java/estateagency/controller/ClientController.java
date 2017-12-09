package estateagency.controller;

import estateagency.dto.flats.FlatDto;
import estateagency.dto.PageWrapper;
import estateagency.dto.clients.ClientDto;
import estateagency.service.FlatService;
import estateagency.service.ClientService;
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
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final FlatService flatService;

    @Autowired
    public ClientController(ClientService clientService, FlatService flatService) {
        this.clientService = clientService;
        this.flatService = flatService;
    }


    /**
     * Показать страницу со списком клиентев
     */
    @GetMapping
    public String findAll(Model model, Pageable pageable) {
        model.addAttribute("flatsList", new ArrayList<FlatDto>());
        model.addAttribute("page", new PageWrapper<>(this.clientService.findAllClients(pageable)));
        return "views/clients/clients";
    }

    /**
     * Производит поиск по ключевому сочетанию символов.
     * Выводи строки из таблицы в любых полях которой встечается заданное сочетание
     *
     * @param search   сочетание символов
     * @param id       id клиента, если он был выбран для просмотра
     * @param pageable объект описывающий какую страницу нужно вывести, и сколько в ней должно быть строк
     */
    @GetMapping("/search")
    public String searchClients(@RequestParam(name = "search", required = false) String search,
                               @RequestParam(name = "id", required = false) Long id,
                               Model model,
                               Pageable pageable) {
        model.addAttribute("page", new PageWrapper<>(this.clientService.findClientByCriteria(pageable, search)));
        model.addAttribute("selectedClient", (id == null) ? null : this.clientService.findClientById(id));
        List<FlatDto> flatsList = this.flatService.findByClientId(id);
        model.addAttribute("flatsList", (id == null) ? new ArrayList<FlatDto>() : flatsList);
        model.addAttribute("search", search);
        return "views/clients/clients";
    }

    /**
     * Отобразить сраницу создания клиента
     */
    @GetMapping("/create")
    public String createClient(Model model) {
        model.addAttribute("clientDto", new ClientDto());
        return "views/clients/create_client";
    }

    /**
     * Принимает данные о клиенте и сохраняет их
     */
    @PostMapping("/create")
    public String saveNewClient(@Valid @ModelAttribute ClientDto clientDto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientDto", clientDto);
            return "views/clients/create_client";
        }
        this.clientService.saveClient(clientDto);
        return "redirect:/clients";
    }

    /**
     * Отображает страницу редактирования клиента
     */
    @GetMapping("/edit/{clientId}")
    public String editClient(@PathVariable(value = "clientId", required = false) Long id,
                            Model model) {
        model.addAttribute("clientDto", this.clientService.findClientById(id));
        return "views/clients/edit_client";
    }

    /**
     * Сохраняет ввесенные пользователем изменения
     */
    @PostMapping("/edit")
    public String save(@Valid @ModelAttribute ClientDto clientDto,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientDto", clientDto);
            return "views/clients/edit_client";
        }
        clientService.saveClient(clientDto);
        return "redirect:/clients";
    }

    /**
     * Удаление клиента
     */
    @GetMapping("/remove/{clientId}")
    public String getDeleteClient(@PathVariable("clientId") Long clientId,
                                 Model model) {
        try {
            this.clientService.removeClientById(clientId);
        } catch (RemovingErrorException e) {
            model.addAttribute("removeError", true);
            model.addAttribute("clientDto", this.clientService.findClientById(clientId));
            return "views/clients/edit_client";
        }
        return "redirect:/clients";
    }

    @GetMapping("/api/getOne")
    @ResponseBody
    public ClientDto selectRealtor(@RequestParam("id") Long id) {
        return clientService.findClientById(id);
    }

    @GetMapping("/api/search")
    @ResponseBody
    public PageWrapper<ClientDto> searchRealtors(@RequestParam(value = "search", required = false) String search,
                                                 Pageable pageable) {
        return new PageWrapper<>(clientService.findClientByCriteria(pageable, search));
    }

    @GetMapping("/edit")
    public String forI18n() {
        return "redirect:/clients";
    }
}
