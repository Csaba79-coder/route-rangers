package hu.pannonuni.routerangers.util;

import hu.pannonuni.model.AddressCreateModel;
import hu.pannonuni.model.AddressModel;
import hu.pannonuni.routerangers.entity.address.Address;
import hu.pannonuni.routerangers.value.Country;
import hu.pannonuni.routerangers.value.StreetType;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static AddressModel mapAddressEntityToModel(Address address) {
        return modelMapper.map(address, AddressModel.class);
    }

    public static Address mapAddressCreateModelToEntity(AddressCreateModel addressCreateModel) {
        Address address = modelMapper.map(addressCreateModel, Address.class);
        if (addressCreateModel.getStreetType() != null) {
            address.setStreetType(StreetType.valueOf(addressCreateModel.getStreetType().getValue()));
        } else {
            address.setStreetType(StreetType.STREET);
        }
        if (addressCreateModel.getCountry() != null) {
            address.setCountry(Country.valueOf(addressCreateModel.getCountry().getValue()));
        } else {
            address.setCountry(Country.HUNGARY);
        }

        address.setLatitude(null);
        address.setLongitude(null);

        return address;
    }

    private Mapper() {
    }
}
