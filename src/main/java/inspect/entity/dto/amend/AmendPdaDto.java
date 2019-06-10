package inspect.entity.dto.amend;

import it.arubapec.esecurity.docflycommon.service.ContextService;
import it.arubapec.esecurity.docflydomain.entity.anag.Archive;
import it.arubapec.esecurity.docflydomain.entity.anag.Mimetype;
import it.arubapec.esecurity.docflydomain.entity.anag.User;
import it.arubapec.esecurity.docflydomain.entity.anag.UserDocClass;
import it.arubapec.esecurity.docflydomain.entity.bucket.BucketConfig;
import it.arubapec.esecurity.docflyenum.amendment.AmendmentType;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

import java.nio.file.Path;
import java.util.List;
import java.util.TreeMap;

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
    private AmendmentType updateType;
    private String docHash;
    private FilePart docPayload;
    private Path docPayloadPath;
    private FilePart docAmendMetaPayload;
    private Path docAmendMetaPayloadPath;
    private Path bucketTmpStorage;

	protected TreeMap<String, AmendPdv> amendPdvs;

    protected BucketConfig bucketConfig;
    protected User user;
    protected Archive archive;
    protected UserDocClass userDocClass;
    protected List<Mimetype> mimetypes;

    protected Boolean signChecker;
    protected List<String> payloadFiles;

	protected Boolean isMultiple;

	public boolean isMultiple(){
		return isMultiple == null ? payloadFiles.size() > 1 : isMultiple;
	}

}
