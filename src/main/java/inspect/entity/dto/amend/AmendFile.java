package inspect.entity.dto.amend;

import it.arubapec.esecurity.docflyenum.generic.HashType;

import java.nio.file.Path;

public interface AmendFile {

	String getDocHash();

	String getDocHashFunction();

	HashType getDocHashType();

	Path getFilePath();

	String getFilename();

}
