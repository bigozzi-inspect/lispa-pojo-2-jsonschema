package inspect.entity;

import it.arubapec.esecurity.docflycommon.service.ContextService;
import it.arubapec.esecurity.docflydomain.entity.bucket.BucketConfig;
import it.arubapec.esecurity.docflysharedapi.domain.amendment.AmendPdaDocsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Amend doc internal response
 * @author Luca Innocenti
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmendPdaDocsResponseInternal extends AmendPdaDocsResponse {

	static final long serialVersionUID = 20190605_1445L;

	private ContextService contextService;
	private BucketConfig bucketConfig;

}
