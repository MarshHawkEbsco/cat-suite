package com.marshhawk.mvc;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.marshhawk.mvc.model.Mij;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarcResponseTests {
	private StringWriter _stringWriter = new StringWriter();
    private ObjectMapper _mapper = new ObjectMapper();
	
	@Test
	public void GivenMarcResponse_WhenSerializing_ThenResultingStringIsCorrect() throws JsonGenerationException, JsonMappingException, IOException {
		String json = "{\n" + 
				"  \"_id\" : {\n" + 
				"    \"timestamp\" : 1540153650,\n" + 
				"    \"machineIdentifier\" : 1979058,\n" + 
				"    \"processIdentifier\" : -22074,\n" + 
				"    \"counter\" : 7275334,\n" + 
				"    \"time\" : 1540153650000,\n" + 
				"    \"date\" : 1540153650000,\n" + 
				"    \"timeSecond\" : 1540153650\n" + 
				"  },\n" + 
				"  \"leader\" : \"00892nam a22002415i 4500\",\n" + 
				"  \"fields\" : [ {\n" + 
				"    \"001\" : \"20405887\"\n" + 
				"  }, {\n" + 
				"    \"ind2\" : \" \",\n" + 
				"    \"ind1\" : \" \",\n" + 
				"    \"010\" : {\n" + 
				"      \"subfields\" : [ {\n" + 
				"        \"a\" : \"  2018937971\"\n" + 
				"      } ]\n" + 
				"    }\n" + 
				"  } ]\n" + 
				"}";
		ObjectMapper map = new ObjectMapper();
		List<Map<String, Object>> fieldList = new ArrayList<Map<String, Object>>();
		fieldList.add(newControlField());
		fieldList.add(newDataField());
		Mij mr = new Mij(new ObjectId("5bcce1321e32b2a9c66f0346"), "00892nam a22002415i 4500", fieldList);
		map.enable(SerializationFeature.INDENT_OUTPUT);
        map.writeValue(_stringWriter, mr);
        map.writeValue(System.out, mr);
        Assert.assertEquals(_stringWriter.toString(), json);
	}
	
	@Test
	public void GivenMarcResponseAsJsonString_WhenDeserializing_ResultingObjectHasCorrectProperties() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = "{\"leader\" : \"00892nam a22002415i 4500\", \"fields\" : [ { \"001\" : \"20405887\" }, { \"010\" : { \"subfields\" : [ { \"a\" : \"  2018937971\" } ], \"ind2\" : \" \", \"ind1\" : \" \" } }]}";
		Mij mij = mapper.readValue(json, Mij.class);
		Object[] fields = mij.getFields().toArray();
		Map<String, Object> field001 = (Map<String, Object>) fields[0];
		Map<String, Object> field010 = (Map<String, Object>) fields[1];
		Map<String, Object> field010List = (Map<String, Object>) field010.get("010");
		List<Map<String, Object>> field010Subfields = (ArrayList<Map<String, Object>>) field010List.get("subfields");
		Map<String, Object> subfield = (Map<String, Object>) field010Subfields.get(0);
		Assert.assertEquals(field001.get("001"), "20405887");
		Assert.assertEquals(subfield.get("a"), "  2018937971");
	}
	
	private Map<String, Object> newControlField()
    {
		Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("001", "20405887");
        return myMap;
    }
	
	private Map<String, Object> newDataField()
    {
		Map<String, Object> myMap = new HashMap<String, Object>();
		Map<String, Object> subMap = new HashMap<String, Object>();
		Map<String, Object> subField = new HashMap<String, Object>();
		List<Map<String, Object>> fields = new ArrayList();
		subField.put("a", "  2018937971");
		fields.add(subField);
		subMap.put("subfields", fields);
		myMap.put("010", subMap);
		myMap.put("ind1", " ");
		myMap.put("ind2", " ");
        return myMap;
    }

}
