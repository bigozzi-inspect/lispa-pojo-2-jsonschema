package inspect.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import it.arubapec.esecurity.docflysharedapi.domain.archive.ArchiveDetailsResponse;

public class ArchiveDetailsResponsePojo extends ArchiveDetailsResponse {
    @Override
    @JsonProperty(required = true)
    @JsonPropertyDescription("asdasd")
    public String getUserId() {
        return super.getUserId();
    }
}
