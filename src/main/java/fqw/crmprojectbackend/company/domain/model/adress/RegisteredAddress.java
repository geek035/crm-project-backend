package fqw.crmprojectbackend.company.domain.model.adress;

import lombok.Data;

@Data
public class RegisteredAddress {
    private final RegisteredAddressCountry country;
    private final RegisteredAddressRegion region;
    private final RegisteredAddressCity city;
    private final RegisteredAddressStreet street;
    private final RegisteredAddressBuilding building;
    private final RegisteredAddressOffice office;
    private final RegisteredAddressPostalCode postalCode;
}
