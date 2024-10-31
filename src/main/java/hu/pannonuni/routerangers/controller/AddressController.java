package hu.pannonuni.routerangers.controller;

import hu.pannonuni.api.AddressApi;
import hu.pannonuni.model.AddressCreateModel;
import hu.pannonuni.model.AddressModel;
import hu.pannonuni.routerangers.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class AddressController implements AddressApi {

    private final AddressService addressService;

    @Override
    public ResponseEntity<List<AddressModel>> renderAllAddress() {
        return ResponseEntity.status(200).body(addressService.findAllAddress());
    }

    @Override
    public ResponseEntity<AddressModel> createAddress(@RequestBody AddressCreateModel addressCreateModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddress(addressCreateModel));
    }

    @Override
    public ResponseEntity<AddressModel> renderAddressById(@PathVariable("addressId") UUID addressId) {
        return ResponseEntity.status(200).body(addressService.getAddressById(addressId));
    }
}
