package estateagency.service;

import estateagency.model.FlatImage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    String store(MultipartFile file, String dir);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void deleteOne(String dir, String fileName);
}
