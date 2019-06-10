package inspect.entity.dto.amend;

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
	protected String docHash;
	protected String docFileName;
    protected String amendedDocId;
    protected Path filePath;

    protected List<AmendAttachment> attachments;

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
