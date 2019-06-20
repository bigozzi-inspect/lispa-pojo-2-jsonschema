package inspect.entity.mapper;

import inspect.entity.AmendPdaDocsRequestInternal;
import inspect.entity.AmendPdaDocsResponseInternal;
import inspect.entity.dto.amend.AmendPdaDto;
import it.arubapec.esecurity.docflycommon.domain.mapper.MapperSharedConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Mapper for AmendDocsDto
 * @author Luca Innocenti
 */

@Component
@Mapper(config = MapperSharedConfig.class, componentModel = "spring")
public interface AmendDocsDtoMapper {

    @Mapping(source = "contextService", target = "contextService")
    @Mapping(source = "updatedPdaId", target = "updatedPdaId")
    @Mapping(source = "updateType", target = "updateType")
    @Mapping(source = "docHash", target = "docHash")
    @Mapping(source = "tmpDocPayloadFile", target = "docPayloadPath")
    @Mapping(source = "tmpDocAmendmentPayloadFile", target = "docAmendMetaPayloadPath")
    AmendPdaDto mapToAmendDocsDto(AmendPdaDocsRequestInternal amendDocsRequest);

    @Mapping(source = "contextService", target = "contextService")
    @Mapping(source = "updatedPdaId", target = "updatedPdaId")
    @Mapping(source = "updateType", target = "updateType")
    @Mapping(expression = "java(amendPdaDto.getAmendPdvs().values().stream().map(pdv -> pdv.getNewPdvId()).collect(java.util.stream.Collectors.toList()))", target = "pdvIds")
    AmendPdaDocsResponseInternal mapToAmendDocsResponse(AmendPdaDto amendPdaDto);


}
