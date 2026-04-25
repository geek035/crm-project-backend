package fqw.crmprojectbackend.company.adapter.out.persistence.mapper;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.address.RegisteredAddressJPAEntity;
import fqw.crmprojectbackend.company.domain.model.adress.RegisteredAddress;

public class RegisteredAddressPersistenceMapper {
    public static RegisteredAddressJPAEntity fromDomainModel(RegisteredAddress registeredAddress) {
        var registeredAddressJPA = new RegisteredAddressJPAEntity();
        registeredAddressJPA.setCountry(registeredAddress.getCountry().value());
        registeredAddressJPA.setRegion(registeredAddress.getRegion().value());
        registeredAddressJPA.setCity(registeredAddress.getCity().value());
        registeredAddressJPA.setStreet(registeredAddress.getStreet().value());
        registeredAddressJPA.setBuilding(registeredAddress.getBuilding().value());
        registeredAddressJPA.setOffice(registeredAddress.getOffice().value());
        registeredAddressJPA.setPostalCode(registeredAddress.getPostalCode().value());

        return registeredAddressJPA;
    }
}
