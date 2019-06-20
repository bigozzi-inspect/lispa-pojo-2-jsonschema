package inspect.entity.dto.amend;

import it.arubapec.esecurity.docflycommon.enums.BusinessMessage;
import it.arubapec.esecurity.docflycommon.exception.BusinessException;
import it.arubapec.esecurity.docflysharedapi.validation.message.FileValidationMessage;
import it.arubapec.esecurity.docflysharedapi.validation.message.PdvValidationResult;
import lombok.Data;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Amend Pdv contain all documents that have to be
 * amended for current amendment operation
 * @author Luca Innocenti
 */
@Data
public class AmendPdv {

	static final long serialVersionUID = 20190604_1658L;

	/**
	 * Dati in input
	 */
	private String pdvId;
	private String newPdvId;
	//docId -> AmendDoc
	private TreeMap<String, AmendDoc> amendDocs;

	private AmendPdaDepositHolder amendPdaDepositHolder;

	private PdvValidationResult pdvValidationResult;

	private List<String> payloadFiles;

	private Path bucketPath;

	public void initPdvValidationResult(String pdvId){
		pdvValidationResult = new PdvValidationResult(pdvId);
	}

	public void addPayloadFiles(String payload){
		if (payloadFiles == null)
			payloadFiles = new ArrayList<>();
		payloadFiles.add(payload);
	}

	Map<String, List<String>> getErrors(){
		if (pdvValidationResult != null && !pdvValidationResult.getFileValidationMessages().isEmpty())
			return pdvValidationResult.getFileValidationMessages()
				.stream().collect(Collectors.groupingBy(
						FileValidationMessage::getFilename,
						Collectors.mapping(FileValidationMessage::getMessage, toList())
						)
				);
		return Map.of();
	}

	public AmendDoc retrieveAmendDocByFilename(String filename){
		return amendDocs.values().stream().filter(amendDoc -> amendDoc.getDocFileName().equalsIgnoreCase(filename)).findFirst()
				.orElseThrow(() -> new BusinessException(BusinessMessage.NOTFOUND, "Impossibile trovare il file " + filename));
	}

}
