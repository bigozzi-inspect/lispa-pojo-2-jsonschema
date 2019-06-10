package inspect.entity.dto.amend;

import it.arubapec.esecurity.docflyenum.generic.HashType;
import it.arubapec.esecurity.docflysharedapi.utils.FileHashUtils;
import lombok.Data;

import java.nio.file.Path;

/**
 * Amend Doc attachment
 * @author Luca Innocenti
 */
@Data
public class AmendAttachment implements AmendFile {

	static final long serialVersionUID = 20190606_1045L;

	protected String filename;
	protected String mimetype;
	protected String impronta;
	protected String oggetto;
	protected Path filePath;
	protected String identificativo;

	public String getDocHash(){
		return FileHashUtils.extractHash(impronta);
	}

	public String getDocHashFunction(){
		return FileHashUtils.extractHashFunction(impronta);
	}

	public HashType getDocHashType(){
		return FileHashUtils.getDocHashType(impronta);
	}

}
