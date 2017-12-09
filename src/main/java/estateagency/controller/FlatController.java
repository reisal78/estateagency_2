package estateagency.controller;

import estateagency.dto.flats.FlatDto;
import estateagency.dto.flats.FlatSearchDto;
import estateagency.dto.PageWrapper;
import estateagency.service.FlatImagesService;
import estateagency.service.FlatService;
import estateagency.service.StorageService;
import estateagency.service.exceptions.RemovingErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/flats")
public class FlatController {

    @Autowired
    private FlatService flatService;

    @Autowired
    private FlatImagesService imagesService;

    @Autowired
    private StorageService storageService;


    /**
     * Отображает страницу подбора квартиры
     */
    @GetMapping
    public String listAllFlats(Model model,
                               Pageable pageable) {
        model.addAttribute("filters", new FlatSearchDto());
        model.addAttribute("districts", flatService.getDistrictList());
        model.addAttribute("page", new PageWrapper<>(flatService.findFlatsByCriteria(new FlatSearchDto(), pageable)));
        return "views/flats/flats";
    }


    /**
     * Отображает страницу подбора с отфильтрованным результатом
     */
    @GetMapping("/search")
    public String getFilteringFlats(@ModelAttribute FlatSearchDto form,
                                    Model model,
                                    Pageable pageable) {
        model.addAttribute("filters", form);
        model.addAttribute("districts", flatService.getDistrictList());
        model.addAttribute("page", new PageWrapper<>(flatService.findFlatsByCriteria(form, pageable)));
        return "views/flats/flats";
    }

    /**
     * Отображает форму создания квартиры
     */
    @GetMapping("/create")
    public String createFlatGET(Model model) {
        model.addAttribute("flatDto", new FlatDto());
        model.addAttribute("districtList", flatService.getDistrictList());
        return "views/flats/create_flat";
    }

    /**
     * Создает новую квартиру
     * @param flatDto данные введенные пользователем
     */
    @PostMapping("/create")
    public String createFlatPOST(@Valid @ModelAttribute FlatDto flatDto,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("flatDto", flatDto);
            model.addAttribute("districtList", flatService.getDistrictList());
            return "views/flats/create_flat";
        }
        FlatDto dto = this.flatService.saveFlat(flatDto);
        return "redirect:/flats/overview/" + dto.getId();
    }


    @GetMapping("/edit")
    public String  editFlat(@RequestParam(value = "id", required = false) Long id,
                            Model model) {
        if (id == null) {
            return "redirect:/flats";
        }
        model.addAttribute("flatDto", flatService.findById(id));
        model.addAttribute("flatImages", imagesService.getFlatImages(id));
        model.addAttribute("districtList", flatService.getDistrictList());
        return "views/flats/edit_flat";
    }

    @PostMapping("/edit")
    public String saveFlat(@Valid @ModelAttribute FlatDto flatDto,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("flatDto", flatDto);
            model.addAttribute("flatImages", imagesService.getFlatImages(flatDto.getId()));
            model.addAttribute("districtList", flatService.getDistrictList());
            return "views/flats/edit_flat";
        }
        FlatDto createdFlat = flatService.saveFlat(flatDto);
        return "redirect:/flats/overview/" + createdFlat.getId();
    }

    @GetMapping("/overview/{id}")
    public ModelAndView overviewFlat(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("views/flats/overview_flat");
        modelAndView.addObject("flat", flatService.findById(id));
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public String deleteFlat(@PathVariable Long id,
                             Model model) {
        try {
            this.flatService.removeFlat(id);
        } catch (RemovingErrorException e) {
            model.addAttribute("removeError", true);
            model.addAttribute("flatDto", this.flatService.findById(id));
            model.addAttribute("flatImages", imagesService.getFlatImages(id));
            model.addAttribute("districtList", flatService.getDistrictList());
            return "views/flats/edit_flat";
        }
        return "redirect:/flats";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("flatId") Long flatId,
                                   RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", flatId);
        imagesService.addImageForFlat(flatId, storageService.store(file, String.valueOf(flatId)));
        return "redirect:/flats/edit";
    }

    @GetMapping("/images/remove")
    @ResponseBody
    public void removeImage(@RequestParam("id") Long id) {
        imagesService.removeImage(id);
    }

}
