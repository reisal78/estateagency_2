package estateagency.controller;

import estateagency.dto.contracts.ContractDto;
import estateagency.dto.contracts.ContractSearchDto;
import estateagency.dto.PageWrapper;
import estateagency.service.ContractsSalesService;
import estateagency.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Controller
@RequestMapping("/contracts/sale")
public class SaleContractsController {


    private final ContractsSalesService contractsSalesService;
    private final FlatService flatService;

    @Autowired
    public SaleContractsController(ContractsSalesService contractsSalesService,
                                   FlatService flatService) {
        this.contractsSalesService = contractsSalesService;
        this.flatService = flatService;
    }

    /**
     * Этот метод может понадобится для коректной конвертации даты переданной из web
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, "completeDate", new CustomDateEditor(sdf, true));

    }

    /**
     * Отображает страницу создания контракта
     * @param flatId id квартиры, на которую заключается контракт
     */
    @GetMapping("/create")
    public String createContract(@RequestParam(value = "id", required = false) Long flatId,
                                 Model model) {
        if (flatId == null) {
            return "redirect:/flats/";
        }
        ContractDto contractDto = contractsSalesService.getNewContract(flatId);
        model.addAttribute("contractDto", contractDto);
        return "/views/contract_sale/create_contract";
    }

    /**
     * Создание контракта
     * @param form данные пользователя
     */
    @PostMapping("/create")
    public String createContract(@Valid @ModelAttribute ContractDto form,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contractDto", form);
            return "/views/contract_sale/create_contract";
        }
        contractsSalesService.createContract(form);
        return "redirect:/flats/overview/" + form.getFlatId();
    }

    /**
     * Отображает страницу редактирования контракта
     * @param flatId id квартиры контракта
     */
    @GetMapping("/edit")
    public String editContract(@RequestParam(value = "id", required = false) Long flatId,
                               Model model) {
        if (flatId == null) {
            return "redirect:/flats/";
        }
        model.addAttribute("contractDto", contractsSalesService.getExistingContract(flatId));
        return "/views/contract_sale/edit_contract";
    }

    @PostMapping("/update")
    public String updateContract(@Valid @ModelAttribute ContractDto form,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contractDto", form);
            return "/views/contract_sale/edit_contract";
        }
        ContractDto contractDto = contractsSalesService.saveContract(form);
        return "redirect:/flats/overview/" + contractDto.getFlatId();
    }


    @GetMapping
    public String getAllContract(Model model,
                                 Pageable pageable) {
        Page<ContractDto> dto = this.contractsSalesService.findByCriteria(new ContractSearchDto(), pageable);
        model.addAttribute("filters", new ContractSearchDto());
        model.addAttribute("page", new PageWrapper<>(dto));
        return "/views/contract_sale/contracts";
    }

    @GetMapping("/search")
    public ModelAndView getFilteringContract(@ModelAttribute ContractSearchDto dto, Pageable pageable) {
        Page<ContractDto> result = this.contractsSalesService.findByCriteria(new ContractSearchDto(), pageable);
        ModelAndView modelAndView = new ModelAndView("/views/contract_sale");
        modelAndView.addObject("filters", dto);
        modelAndView.addObject("page", new PageWrapper<>(result));
        return modelAndView;
    }

    @GetMapping("/close")
    public String closeContract(@RequestParam("id") Long id) {
        ContractDto contractDto = this.contractsSalesService.closeContract(id);
        return "redirect:/contracts/sale/overview/" + contractDto.getFlatId();
    }



    @GetMapping("/delete")
    public String deleteContract(@RequestParam("id") Long flatId) {
        contractsSalesService.deleteContract(flatId);
        return "redirect:/flats/overview/" + flatId;
    }

    @GetMapping("/overview/{id}")
    public String  getOneContract(@PathVariable Long id,
                                  Model model) {
        model.addAttribute("flatDto", this.flatService.findById(id));
        model.addAttribute("contractDto", contractsSalesService.findByFlatId(id));
        return "views/contract_sale/overview_contract";
    }

    @GetMapping("/act/{id}")
    public String getAct(@PathVariable Long id, Model model) {


        ContractDto dto = contractsSalesService.findById(id);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dto.getCompleteDate());
        model.addAttribute("year", calendar.get(Calendar.YEAR));
        model.addAttribute("month", calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, LocaleContextHolder.getLocale()));
        model.addAttribute("day", calendar.get(Calendar.DAY_OF_MONTH));
        model.addAttribute("contract", dto);
        model.addAttribute("flat", flatService.findById(dto.getFlatId()));
        return "views/contract_sale/act";
    }


}
