package server.rest;

import java.util.Map;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MapData {

	private Map<String, String> data;

	MapData() {
	}

	public MapData(Map<String, String> data) {
		this.data = data;
	}

	public Map<String, String> getData() {
		return data;
	}

}
