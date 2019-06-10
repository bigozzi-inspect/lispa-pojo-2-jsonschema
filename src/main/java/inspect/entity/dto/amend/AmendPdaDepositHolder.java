package inspect.entity.dto.amend;

import it.arubapec.esecurity.docflydomain.entity.bucket.BucketDoc;
import it.arubapec.esecurity.docflydomain.entity.bucket.BucketPdv;
import it.arubapec.esecurity.docflydomain.mongo.Attachment;
import it.arubapec.esecurity.docflydomain.mongo.Doc;
import lombok.Data;

import java.util.List;

/**
 * Amend Doc DTO for validation and deposit steps
 * @author Luca Innocenti
 */
@Data
public class AmendPdaDepositHolder {

	static final long serialVersionUID = 20190607_1723L;

	//Deposit only fields
	protected long payloadFilesCount;
	protected long payloadFilesSize;
	protected List<Doc> docs;
	protected List<Attachment> attachments;
    protected BucketPdv bucketPdv;
    protected List<BucketDoc> buckets;

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }
}
