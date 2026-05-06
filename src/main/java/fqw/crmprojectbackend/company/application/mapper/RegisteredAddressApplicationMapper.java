package fqw.crmprojectbackend.company.application.mapper;

import fqw.crmprojectbackend.company.application.dto.RegisteredAddressDTO;
import fqw.crmprojectbackend.company.domain.model.adress.*;

public class RegisteredAddressApplicationMapper {
    public static RegisteredAddress toDomainModel(RegisteredAddressDTO dto) {
        return new RegisteredAddress(
                new RegisteredAddressCountry(dto.country()),
                new RegisteredAddressRegion(dto.region()),
                new RegisteredAddressCity(dto.city()),
                new RegisteredAddressStreet(dto.street()),
                new RegisteredAddressBuilding(dto.building()),
                new RegisteredAddressOffice(dto.office()),
                new RegisteredAddressPostalCode(dto.postalCode()));
    }

    public static RegisteredAddressDTO fromDomainModel(RegisteredAddress address) {
        return new RegisteredAddressDTO(
                address.getCountry().value(),
                address.getRegion().value(),
                address.getCity().value(),
                address.getStreet().value(),
                address.getBuilding().value(),
                address.getOffice().value(),
                address.getPostalCode().value());
    }
}
