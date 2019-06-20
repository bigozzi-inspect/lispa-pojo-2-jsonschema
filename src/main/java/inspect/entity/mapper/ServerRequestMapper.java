package inspect.entity.mapper;

import inspect.entity.AmendPdaDocsRequestInternal;
import it.arubapec.esecurity.docflycommon.domain.mapper.MapperSharedConfig;
import it.arubapec.esecurity.docflycommon.service.ContextService;
import it.arubapec.esecurity.docflycommon.utils.FilePartUtil;
import it.arubapec.esecurity.docflydomain.mongo.AmendmentPda;
import it.arubapec.esecurity.docflyenum.amendment.UpdateType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

/**
 * Mapper for server requests
 * @author Luca Innocenti
 */
@Component
@Mapper(config = MapperSharedConfig.class, componentModel = "spring")
public interface ServerRequestMapper {

	default Mono<AmendPdaDocsRequestInternal> mapToAmendDocsRequest(ServerRequest serverRequest, ContextService ctx, AmendmentPda amendmentPda, UpdateType updateType) {
		return Mono.just(serverRequest).flatMapMany(req -> req.body(BodyExtractors.toParts()))
			.collectMap(part -> part.name(), part -> part)
			.map(partMap -> {
                AmendPdaDocsRequestInternal amendPdaDocsRequestInternal = new AmendPdaDocsRequestInternal();
                amendPdaDocsRequestInternal.setUpdateType(updateType);
                amendPdaDocsRequestInternal.setDocHash(FilePartUtil.getFieldString(partMap.get("docHash")));
                amendPdaDocsRequestInternal
					.setDocPayload(FilePartUtil.getFieldFilePart(partMap.getOrDefault("docPayload", null)));
                amendPdaDocsRequestInternal.setDocAmendMetaPayload(
					FilePartUtil.getFieldFilePart(partMap.getOrDefault("docAmendmentPayload", null)));
                amendPdaDocsRequestInternal.setContextService(ctx);
                amendPdaDocsRequestInternal.setUpdateType(updateType);
                amendPdaDocsRequestInternal.setUpdatedPdaId(amendmentPda.getPdaid());
                amendPdaDocsRequestInternal.setAmendmentPda(amendmentPda);
				return amendPdaDocsRequestInternal;
			})
			.onErrorResume(t -> Mono.just(new AmendPdaDocsRequestInternal()));
	}
}
