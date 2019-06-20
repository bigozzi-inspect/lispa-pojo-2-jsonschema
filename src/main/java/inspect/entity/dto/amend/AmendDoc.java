package inspect.entity.dto.amend;

import it.arubapec.esecurity.docflyenum.doc.DocSignStatus;
import it.arubapec.esecurity.docflyenum.generic.HashType;
import it.arubapec.esecurity.docflysharedapi.utils.FileHashUtils;
import lombok.Data;

import java.nio.file.Path;
import java.util.List;

/**
 * Amend Doc single doc information
 * @author Luca Innocenti
 */
@Data
public class AmendDoc implements AmendFile {

	static final long serialVersionUID = 20190604_1658L;

	/**
	 * Dati in input
	 */
	private String docHash;
	private String docFileName;
    private String amendedDocId;
    private Path filePath;
    private DocSignStatus signcheckoutcome;

	private List<AmendAttachment> attachments;

	public String getDocHash(){
		return FileHashUtils.extractHash(docHash);
	}

	public String getDocHashFunction(){
		return FileHashUtils.extractHashFunction(docHash);
	}

	public HashType getDocHashType(){
		return FileHashUtils.getDocHashType(docHash);
	}

	public String getFilename(){
		return docFileName;
	}

}
