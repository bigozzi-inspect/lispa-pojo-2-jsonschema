package inspect.entity.dto.amend;

import it.arubapec.esecurity.docflydomain.entity.bucket.BucketDoc;
import it.arubapec.esecurity.docflydomain.entity.bucket.BucketPdv;
import it.arubapec.esecurity.docflydomain.mongo.Attachment;
import it.arubapec.esecurity.docflydomain.mongo.Doc;
import it.arubapec.esecurity.docflydomain.mongo.Pdv;
import it.arubapec.esecurity.docflyenum.amendment.UpdateStatus;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Amend Doc DTO for validation and deposit steps
 * @author Luca Innocenti
 */
@Data
public class AmendPdaDepositHolder {

	static final long serialVersionUID = 20190607_1723L;

	//Deposit only fields
	private long payloadFilesCount;
	private long payloadFilesSize;
	private Pdv pdv;
	private List<Doc> docs;
	private List<Attachment> attachments;
    private BucketPdv bucketPdv;
    private List<BucketDoc> bucketDocs;

    protected OriginalPdvInfo originalPdvInfo = new OriginalPdvInfo();


    public void addAttachment(Attachment attachment) {
        if (attachments == null)
        	attachments = new ArrayList<>();
    	attachments.add(attachment);
    }

	public void addDoc(Doc doc) {
		if (docs == null)
			docs = new ArrayList<>();
    	docs.add(doc);
	}

    public static AmendPdaDepositHolder init(){
    	AmendPdaDepositHolder amendPdaDepositHolder = new AmendPdaDepositHolder();
    	amendPdaDepositHolder.setPayloadFilesCount(0L);
    	amendPdaDepositHolder.setPayloadFilesSize(0L);
    	return amendPdaDepositHolder;
	}

	@Data
	public class OriginalPdvInfo{
        private UpdateStatus updateStatus;
        private ZonedDateTime updatedDate;
    }

}
