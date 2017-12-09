package estateagency.service;

import estateagency.model.FlatImage;
import estateagency.service.exceptions.StorageException;
import estateagency.service.exceptions.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    @Value("${estateagency.upload-dir.location}")
    public String location;
    private Path rootLocation;

    @PostConstruct
    public void init() {
        rootLocation = Paths.get(location);
        createFileLocation(rootLocation);
    }

    private void createFileLocation(Path fileLocation) {
        try {
            File file = new File(fileLocation.toUri());
            if (!file.exists()) {
                Files.createDirectory(fileLocation);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String store(MultipartFile file, String dir) {
        Path fileLocation = Paths.get(location + "/" + dir);
        createFileLocation(fileLocation);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            File f = new File(fileLocation.resolve(file.getOriginalFilename()).toUri());
            String fileName = file.getOriginalFilename();
            int i = 0;
            while (f.exists()) {
                fileName = ++i + "_" + fileName;
                f = new File(fileLocation.resolve(fileName).toUri());
            }
            Files.copy(file.getInputStream(), fileLocation.resolve(fileName));
            return fileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void deleteOne(String dir, String fileName) {
        Path fileLocation = Paths.get(location + "/" + dir);
        File file = new File(fileLocation.resolve(fileName).toUri());
        file.delete();
    }


}