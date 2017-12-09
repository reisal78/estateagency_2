package estateagency.service;

import estateagency.dao.ContractRentRepository;
import estateagency.dao.ContractSaleRepository;
import estateagency.dao.FlatImageRepository;
import estateagency.dao.FlatRepository;
import estateagency.dto.flats.FlatDto;
import estateagency.dto.flats.FlatSearchDto;
import estateagency.model.Contract;
import estateagency.model.Flat;
import estateagency.model.FlatImage;
import estateagency.service.exceptions.RemovingErrorException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;


@Service
public class FlatServiceImpl implements FlatService {

    private final FlatRepository flatRepository;
    private final FlatImageRepository flatImageRepository;
    private final ContractSaleRepository contractSaleRepository;
    private final ContractRentRepository contractRentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FlatServiceImpl(FlatRepository flatRepository,
                           FlatImageRepository flatImageRepository,
                           ContractSaleRepository contractSaleRepository,
                           ContractRentRepository contractRentRepository,
                           ModelMapper modelMapper) {
        this.flatRepository = flatRepository;
        this.flatImageRepository = flatImageRepository;
        this.contractSaleRepository = contractSaleRepository;
        this.contractRentRepository = contractRentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> getDistrictList() {
        return flatRepository.getDistrictsList();
    }

    @Override
    public Page<FlatDto> findFlatsByCriteria(FlatSearchDto form, Pageable pageable) {

        Specification<Flat> specification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("available"), true);

        if (!form.isReserved()) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("reserved"), false);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        if (form.getTypeTrade() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("typeTrade"), form.getTypeTrade());
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        if (!form.getDistrict().isEmpty()) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("district"), form.getDistrict());
            specification = (specification == null) ? where(s) : where(specification).and(s);

        }

        if (form.getMinArea() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("area"), form.getMinArea() - 0.01);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }
        if (form.getMaxArea() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.lessThan(root.get("area"), form.getMaxArea() + 0.01);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        if (form.getMinPrice() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("price"), form.getMinPrice() - 0.01);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        if (form.getMaxPrice() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.lessThan(root.get("price"), form.getMaxPrice() + 0.01);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        if (form.getMinRoom() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("rooms"), form.getMinRoom() - 1);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        if (form.getMaxRoom() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.lessThan(root.get("rooms"), form.getMaxRoom() + 1);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        if (form.getMinFloor() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThan(root.get("floor"), form.getMinFloor() - 1);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        if (form.getMaxFloor() != null) {
            Specification<Flat> s = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.lessThan(root.get("floor"), form.getMaxFloor() + 1);
            specification = (specification == null) ? where(s) : where(specification).and(s);
        }

        Page<Flat> page = flatRepository.findAll(specification, pageable);
        return new PageImpl<>(convertListFlatsToListFlatsDto(page.getContent()), pageable, page.getTotalElements());

    }

    @Override
    public FlatDto findById(Long id) {
        return convertFlatToFlatDto(flatRepository.findOne(id));
    }

    @Override
    public FlatDto saveFlat(FlatDto flatDto) {
        return convertFlatToFlatDto(flatRepository.save(convertFlatDtoToFlat(flatDto)));
    }

    @Override
    public List<FlatDto> findByOwnerId(Long id) {
        List<FlatDto> flatDtos = convertListFlatsToListFlatsDto(flatRepository.findByOwnerId(id));
        return flatDtos;
    }

    @Override
    public Page<FlatDto> findAll(Pageable pageable) {
        Page<Flat> page = flatRepository.findByAvailable(true, pageable);
        return new PageImpl<>(convertListFlatsToListFlatsDto(page.getContent()), pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public List<FlatDto> findByClientId(Long id) {
        List<Contract> contractSaleList = contractSaleRepository.findByClientId(id);
        List<Flat> flats = new ArrayList<>();
        for (Contract contract : contractSaleList) {
            flats.add(contract.getFlat());
        }
        List<Contract> contractRentList = contractRentRepository.findByClientId(id);
        for (Contract contract : contractRentList) {
            flats.add(contract.getFlat());
        }
        List<FlatDto> flatDtos = convertListFlatsToListFlatsDto(flats);
        return flatDtos;
    }

    @Override
    @Transactional
    public void removeFlat(Long id) throws RemovingErrorException {
        if (contractSaleRepository.findByFlatId(id) != null) {
            throw new RemovingErrorException("Flat have sale contracts");
        }
        if (contractRentRepository.findByFlatId(id) != null) {
            throw new RemovingErrorException("Flat have rent contracts");
        }
        if (!this.flatImageRepository.findFlatImageByFlatId(id).isEmpty()) {
            throw new RemovingErrorException("Flat have upload images");
        }
        this.flatRepository.delete(id);
    }

    private FlatDto convertFlatToFlatDto(Flat flat) {
        FlatDto dto = modelMapper.map(flat, FlatDto.class);
        dto.setArea(new BigDecimal(dto.getArea()).setScale(2, RoundingMode.UP).doubleValue());
        dto.setPrice(new BigDecimal(dto.getPrice()).setScale(2, RoundingMode.UP).doubleValue());
        dto.setOwnerFullName(flat.getOwner().getLastName() + " " + flat.getOwner().getFirstName() + " "
                + flat.getOwner().getSurName());
        for (FlatImage flatImage : flatImageRepository.findFlatImageByFlatId(flat.getId())) {
            StringBuilder sb = new StringBuilder();
            sb.append("/files/").append(flat.getId()).append("/").append(flatImage.getUrl());
            dto.getImagesPath().add(sb.toString());
        }
        return dto;
    }

    private Flat convertFlatDtoToFlat(FlatDto dto) {
        Flat flat = modelMapper.map(dto, Flat.class);
        return flat;
    }

    private List<FlatDto> convertListFlatsToListFlatsDto(List<Flat> flats) {
        List<FlatDto> dtos = new ArrayList<>();
        for (Flat flat : flats) {
            FlatDto dto = modelMapper.map(flat, FlatDto.class);
            for (FlatImage flatImage : flatImageRepository.findFlatImageByFlatId(flat.getId())) {
                StringBuilder sb = new StringBuilder();
                sb.append("/files/").append(flat.getId()).append("/").append(flatImage.getUrl());
                dto.getImagesPath().add(sb.toString());
            }
            dtos.add(dto);
        }
        return dtos;
    }
}
