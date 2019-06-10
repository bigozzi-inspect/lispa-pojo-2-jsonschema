package inspect.entity.dto.amend;

import lombok.Data;

import java.util.TreeMap;

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
	protected String pdvId;
	protected String newPdvId;
	//docId -> AmendDoc
    protected TreeMap<String, AmendDoc> amendDocs;

	private AmendPdaDepositHolder amendPdaDepositHolder;



}
