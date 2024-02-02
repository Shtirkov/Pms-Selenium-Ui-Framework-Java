package seleniumuiframework.frameworkcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataProvider {

	public List<HashMap<String, String>> getDataFromJsonFile(String filePath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(filePath),new TypeReference<List<HashMap<String,String>>>(){});
	}

}
