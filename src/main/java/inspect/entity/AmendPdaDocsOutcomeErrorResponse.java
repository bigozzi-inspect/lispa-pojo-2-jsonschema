package inspect.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.arubapec.esecurity.docflyenum.pdv.RdtStatus;
import it.arubapec.esecurity.docflysharedapi.utils.DateFormatUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmendPdaDocsOutcomeErrorResponse implements Serializable{

	static final long serialVersionUID = 20190605_1720L;

    private String pdaId;
	private List<AmendError> errors;
    @JsonFormat(pattern = DateFormatUtils.pattern)
    private ZonedDateTime uploadDate;
    private RdtStatus outcome;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class AmendError implements Serializable {

		private String errorCode;
		private String errorMessage;
		@JsonInclude(JsonInclude.Include.NON_NULL)
		private String filename;
	}
}
