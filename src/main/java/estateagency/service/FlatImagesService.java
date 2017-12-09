package estateagency.service;

import estateagency.model.FlatImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlatImagesService {
    void addImageForFlat(Long flatId, String fileName);
    void removeImage(Long id);
    List<FlatImage> getFlatImages(Long flatId);
}
