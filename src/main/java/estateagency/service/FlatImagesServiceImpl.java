package estateagency.service;

import estateagency.dao.FlatImageRepository;
import estateagency.model.Flat;
import estateagency.model.FlatImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FlatImagesServiceImpl implements FlatImagesService {


    @Autowired
    private FlatImageRepository flatImageRepository;

    @Autowired
    private StorageService storageService;


    @Override
    public void addImageForFlat(Long flatId, String fileName) {
        Flat flat = new Flat();
        flat.setId(flatId);
        FlatImage flatImage = new FlatImage();
        flatImage.setUrl(fileName);
        flatImage.setFlat(flat);
        flatImageRepository.save(flatImage);
    }

    @Override
    @Transactional
    public void removeImage(Long id) {
        FlatImage flatImage = flatImageRepository.findOne(id);
        storageService.deleteOne(String.valueOf(flatImage.getFlat().getId()), flatImage.getUrl());
        flatImageRepository.delete(flatImage);
    }

    @Override
    public List<FlatImage> getFlatImages(Long flatId) {
        return flatImageRepository.findFlatImageByFlatId(flatId);
    }
}
