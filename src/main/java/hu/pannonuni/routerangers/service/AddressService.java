package hu.pannonuni.routerangers.service;

import hu.pannonuni.model.AddressCreateModel;
import hu.pannonuni.model.AddressModel;
import hu.pannonuni.routerangers.controller.exception.EntityAlreadyExistsException;
import hu.pannonuni.routerangers.entity.address.Address;
import hu.pannonuni.routerangers.persistence.AddressRepository;
import hu.pannonuni.routerangers.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static hu.pannonuni.routerangers.util.Mapper.mapAddressCreateModelToEntity;
import static hu.pannonuni.routerangers.util.Mapper.mapAddressEntityToModel;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;
    private final GeocodingService geocodingService;

    public List<AddressModel> findAllAddress() {
        return addressRepository.findAll().stream().map(Mapper::mapAddressEntityToModel).toList();
    }

    public AddressModel getAddressById(UUID addressId) {
        return addressRepository.findById(addressId)
                .map(Mapper::mapAddressEntityToModel)
                .orElseThrow(() -> {
                    String message = String.format("Address not found with id: %s", addressId);
                    log.info(message);
                    return new NoSuchElementException(message);
                });
    }

    public AddressModel saveAddress(AddressCreateModel addressCreateModel) {
        Optional<Address> existingAddress = addressRepository.findByStreetAndHouseNumberAndCityAndPostalCode(
                addressCreateModel.getStreet(),
                addressCreateModel.getHouseNumber(),
                addressCreateModel.getCity(),
                addressCreateModel.getPostalCode()
        );

        if (existingAddress.isPresent()) {
            String message = String.format("The address already exists with id: %s", existingAddress.get().getId());
            log.info(message);
            throw new EntityAlreadyExistsException(String.format("The address already exists with id: %s", existingAddress.get().getId()));
        }

        Address address = mapAddressCreateModelToEntity(addressCreateModel);
        Double[] coordinates = geocodingService.fetchCoordinatesForAddress(addressCreateModel);

        if (coordinates != null) {
            address.setLatitude(coordinates[0]); // latitude
            address.setLongitude(coordinates[1]); // longitude
        } else {
            address.setLatitude(null);
            address.setLongitude(null);
        }

        addressRepository.save(address);
        return mapAddressEntityToModel(address);
    }
}
