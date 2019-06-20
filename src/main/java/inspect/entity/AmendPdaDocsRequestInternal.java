package inspect.entity;

import it.arubapec.esecurity.docflycommon.service.ContextService;
import it.arubapec.esecurity.docflydomain.mongo.AmendmentPda;
import it.arubapec.esecurity.docflyenum.amendment.UpdateType;
import it.arubapec.esecurity.docflysharedapi.domain.amendment.AmendPdaDocsRequest;
import it.arubapec.esecurity.docflysharedapi.validation.annotation.NotNullNotEmpty;
import it.arubapec.esecurity.docflysharedapi.validation.annotation.ValidationMessage;
import it.arubapec.esecurity.docflysharedapi.validation.annotation.ZeroByteCheck;
import it.arubapec.esecurity.docflysharedapi.validation.groups.PrimaryValidationValidationGroup;
import it.arubapec.esecurity.docflysharedapi.validation.groups.SecondaryValidationValidationGroup;
import lombok.Data;

import java.nio.file.Path;

/**
 * Amend Doc internal request
 * @author Luca Innocenti
 */
@Data
public class AmendPdaDocsRequestInternal extends AmendPdaDocsRequest {

	private AmendmentPda amendmentPda;

	private ContextService contextService;

	@NotNullNotEmpty(messageValidation = @ValidationMessage(message = "updatePdaId non può essere vuoto"), groups = PrimaryValidationValidationGroup.class)
	private String updatedPdaId;

	private UpdateType updateType;

	@ZeroByteCheck(messageValidation = @ValidationMessage(message = "Il file '[filename]' non ha contenuti (0 Byte)"), groups = SecondaryValidationValidationGroup.class)
	private Path tmpDocPayloadFile;

	@ZeroByteCheck(messageValidation = @ValidationMessage(message = "Il file '[filename]' non ha contenuti (0 Byte)"), groups = SecondaryValidationValidationGroup.class)
	private Path tmpDocAmendmentPayloadFile;


}
