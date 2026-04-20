package fqw.crmprojectbackend.common.web.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class EntityPageDTO<T> {
        private final Long total;
        private final List<T> data;
}
