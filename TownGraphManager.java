import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface{
	Graph graph = new Graph();

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if (graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		return graph.getVertex(name);
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roadsList = new ArrayList<>();
		Set<Road> roads = graph.edgeSet();
		Iterator<Road> iterator = roads.iterator();
		while(iterator.hasNext()) {
			roadsList.add(iterator.next().getName());
		}
		Collections.sort(roadsList);
		return roadsList;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if(graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> townsList = new ArrayList<>();
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext()) {
			townsList.add(iterator.next().getName());
		}
		Collections.sort(townsList);
		return townsList;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	public void populateTownGraph(File selectedFile)throws IOException {
		FileReader reader = new FileReader(selectedFile);
		BufferedReader buffer = new BufferedReader(reader);
		for(String line1 = buffer.readLine(); line1 != null; line1 = buffer.readLine()) {
			String[] cityInfo = line1.split("[;,]");
			addTown(cityInfo[2]);
			addTown(cityInfo[3]);
			addRoad(cityInfo[2], cityInfo[3], Integer.parseInt(cityInfo[1]), cityInfo[0]);
		}
		buffer.close();
	}

}
