package fqw.crmprojectbackend.company.adapter.out.persistence.mapper;

import fqw.crmprojectbackend.company.adapter.out.persistence.entity.address.RegisteredAddressJPAEntity;
import fqw.crmprojectbackend.company.domain.model.adress.*;

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

    public static RegisteredAddress toDomainModel(RegisteredAddressJPAEntity jpa) {
        return new RegisteredAddress(
                new RegisteredAddressCountry(jpa.getCountry()),
                new RegisteredAddressRegion(jpa.getRegion()),
                new RegisteredAddressCity(jpa.getCountry()),
                new RegisteredAddressStreet(jpa.getStreet()),
                new RegisteredAddressBuilding(jpa.getBuilding()),
                new RegisteredAddressOffice(jpa.getOffice()),
                new RegisteredAddressPostalCode(jpa.getPostalCode()));
    }
}
