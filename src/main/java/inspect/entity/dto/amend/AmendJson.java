package inspect.entity.dto.amend;

import lombok.Data;

import java.util.TreeMap;

/**
 * Amend Json contain all json data
 * @author Luca Innocenti
 */
@Data
public class AmendJson {

	static final long serialVersionUID = 20190606_1241L;


	//docId -> AmendDoc
    protected TreeMap<String, AmendPdv> amendPdvs;



}
