package inspect.entity.dto.amend;

import it.arubapec.esecurity.docflycommon.service.ContextService;
import it.arubapec.esecurity.docflydomain.entity.anag.*;
import it.arubapec.esecurity.docflydomain.entity.bucket.BucketConfig;
import it.arubapec.esecurity.docflydomain.mongo.AmendmentPda;
import it.arubapec.esecurity.docflyenum.amendment.UpdateType;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Amend Doc DTO for validation and deposit steps
 * @author Luca Innocenti
 */
@Data
public class AmendPdaDto {

	static final long serialVersionUID = 20190604_1652L;

	/**
	 * Dati in input
	 */
	private ContextService contextService;
    private String userPdaId;
    private String updatedPdaId;
    private UpdateType updateType;
    private String docHash;
    private FilePart docPayload;
    private Path docPayloadPath;
    private FilePart docAmendMetaPayload;
    private Path docAmendMetaPayloadPath;
    private Path bucketTmpStorage;

	private TreeMap<String, AmendPdv> amendPdvs;

	private AmendmentPda amendmentPda;
    private BucketConfig bucketConfig;
    private User user;
	private User delegateUser;
    private Archive archive;
    private UserDocClass userDocClass;
    private List<Mimetype> mimetypes;

	private Boolean signChecker;
	private List<String> payloadFiles;

	public Map<String, Map<String, List<String>>> getErrors(){
		return amendPdvs.values().stream()
				.collect(Collectors.toMap(
						AmendPdv::getNewPdvId,
						AmendPdv::getErrors
				));
	}

	private List<DocClassField> attachmentFieldList;

}
