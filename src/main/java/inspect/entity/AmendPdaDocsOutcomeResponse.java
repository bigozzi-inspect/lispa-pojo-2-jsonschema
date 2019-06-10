package inspect.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.arubapec.esecurity.docflyenum.pdv.RdtStatus;
import it.arubapec.esecurity.docflysharedapi.utils.DateFormatUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Amendment general Outcome Response
 * @author Luca Innocenti
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmendPdaDocsOutcomeResponse implements Serializable {

	static final long serialVersionUID = 20190117_1150L;

	private String pdaId;
	@JsonFormat(pattern = DateFormatUtils.pattern)
	private ZonedDateTime uploadDate;
	private RdtStatus outcome;

}
