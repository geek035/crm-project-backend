package fqw.crmprojectbackend.individual.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IndividualID {
    private final UUID value;

    public static IndividualID generateID() {
        return new IndividualID(UUID.randomUUID());
    }

    public static IndividualID from(UUID value) {
        return new IndividualID(value);
    }
}