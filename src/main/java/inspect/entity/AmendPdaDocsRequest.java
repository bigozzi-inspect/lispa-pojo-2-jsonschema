package inspect.entity;

import it.arubapec.esecurity.docflycommon.service.ContextService;
import it.arubapec.esecurity.docflyenum.amendment.AmendmentType;
import it.arubapec.esecurity.docflysharedapi.validation.annotation.*;
import it.arubapec.esecurity.docflysharedapi.validation.groups.PrimaryValidationValidationGroup;
import it.arubapec.esecurity.docflysharedapi.validation.groups.SecondaryValidationValidationGroup;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;

/**
 * Amend Doc internal request
 * @author Luca Innocenti
 */
@Data
@HashFileMatch(hashField = "docHash", fileFieldToHash = "tmpDocPayloadFile", messageValidation = @ValidationMessage(message = "Valore hash del campo 'docHash' non valido per il file dichiarato sul campo 'docPayload'"), groups = SecondaryValidationValidationGroup.class)
public class AmendPdaDocsRequest {

	static final long serialVersionUID = 20190605_1234L;

	/**
	 * Dati in input
	 */
	protected ContextService contextService;

	@NotNullNotEmpty(messageValidation = @ValidationMessage(message = "updatePdaId non può essere vuoto"), groups = PrimaryValidationValidationGroup.class)
	protected String updatedPdaId;

	protected AmendmentType updateType;

    @NotNullNotEmpty(messageValidation = @ValidationMessage(message =  "docHash non può essere vuoto"), groups = PrimaryValidationValidationGroup.class)
	protected String docHash;

    @NotNull(message = "Il campo 'docPayload' non può essere vuoto", groups = PrimaryValidationValidationGroup.class)
    @FilenameMatch(pattern = "(?i)amendment.zip", messageValidation = @ValidationMessage(message = "Nome file 'docPayload' deve essere 'amendment.zip'"), groups = PrimaryValidationValidationGroup.class)
    protected FilePart docPayload;

    @NotNull(message = "Il campo 'docMetadataPayload' non può essere vuoto", groups = PrimaryValidationValidationGroup.class)
    @FilenameMatch(pattern = "(?i)amendment.json", messageValidation = @ValidationMessage(message = "Nome del file nel campo 'docAmendMetaPayload' non consentito ammesso solo amendment.json"), groups = PrimaryValidationValidationGroup.class)
    protected FilePart docAmendMetaPayload;

    @ZeroByteCheck(messageValidation = @ValidationMessage(message = "Il file '[filename]' non ha contenuti (0 Byte)"), groups = SecondaryValidationValidationGroup.class)
    private Path tmpDocPayloadFile;

    @ZeroByteCheck(messageValidation = @ValidationMessage(message = "Il file '[filename]' non ha contenuti (0 Byte)"), groups = SecondaryValidationValidationGroup.class)
    private Path tmpDocAmendmentPayloadFile;


}
