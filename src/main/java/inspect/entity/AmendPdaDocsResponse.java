package inspect.entity;

import it.arubapec.esecurity.docflycommon.service.ContextService;
import it.arubapec.esecurity.docflydomain.entity.bucket.BucketConfig;
import it.arubapec.esecurity.docflyenum.amendment.AmendmentType;
import lombok.Data;

/**
 * Amend doc internal response
 * @author Luca Innocenti
 */
@Data
public class AmendPdaDocsResponse {

	static final long serialVersionUID = 20190605_1445L;

	protected ContextService contextService;
	protected String updatedPdaId;
	protected AmendmentType updateType;
	protected BucketConfig bucketConfig;

}
