import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {
	Set<Road> roads = new HashSet<Road>();
	Set<Town> towns = new HashSet<Town>();
	HashMap<String, Integer> WeightMap = new HashMap<>();
	HashMap<Town, Town> prevTownMap = new HashMap<>();

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		Iterator<Road> iterator = roads.iterator();
		while(iterator.hasNext()) {
			Road currentRoad = iterator.next();
			if(currentRoad.getSource().equals(sourceVertex) && currentRoad.getDestination().equals(destinationVertex)) {
				return currentRoad;
			} else if (currentRoad.getSource().equals(destinationVertex) && currentRoad.getDestination().equals(sourceVertex)) {
				currentRoad.flipSourceAndDestination();
				return currentRoad;
			}
		}
		return null;
	}
	
	public Town getVertex(String name) {
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext()) {
			Town currentTown = iterator.next();
			if(currentTown.getName().equals(name)) {
				return currentTown;
			}
		}
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		if(roads.contains(road)) {
			return null;
		}
		roads.add(road);
		return road;
	}

	@Override
	public boolean addVertex(Town v) {
		if(towns.contains(v)) {
			return false;
		}
		towns.add(v);
		WeightMap.put(v.getName(), 1000000000);
		return true;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		Iterator<Road> iterator = roads.iterator();
		while(iterator.hasNext()) {
			Road currentRoad = iterator.next();
			if(currentRoad.contains(sourceVertex) && currentRoad.contains(destinationVertex)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		return towns.contains(v);
	}

	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Iterator<Road> iterator = roads.iterator();
		Set<Road> roadsTouchingTown = new HashSet<Road>(); 
		while(iterator.hasNext()) {
			Road currentRoad = iterator.next();
			if(currentRoad.contains(vertex)) {
				roadsTouchingTown.add(currentRoad);
			}
		}
		return roadsTouchingTown;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(!towns.contains(sourceVertex) || !towns.contains(destinationVertex) || description == null){
			return null;
		}
		Road roadToRemove;
		if(weight < 1) {
			roadToRemove = new Road(sourceVertex, destinationVertex, description);
		} else {
			roadToRemove = new Road(sourceVertex, destinationVertex, weight, description);
		}
		if(roads.contains(getEdge(sourceVertex, destinationVertex))) {
			roads.remove(getEdge(sourceVertex, destinationVertex));
			return roadToRemove;
		}
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		Town townToRemove = new Town(v);
		if(towns.contains(townToRemove)) {
			towns.remove(townToRemove);
			WeightMap.remove(townToRemove.getName());
			roads.removeAll(edgesOf(townToRemove));
			return true;
		}
		return false;
	}

	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> shortestPath = new ArrayList<String>();
		while(prevTownMap.get(destinationVertex) != null) {
			shortestPath.add(getEdge(prevTownMap.get(destinationVertex), destinationVertex).toString());
			destinationVertex = prevTownMap.get(destinationVertex);
		}
		Collections.reverse(shortestPath);
		return shortestPath;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		WeightMap.replace(sourceVertex.getName(), 0);
		Queue<Town> queue = new PriorityQueue<>();
		Set<Town> exploredVertices = new HashSet<>();
		prevTownMap.put(sourceVertex, null);
		queue.add(sourceVertex);
		while(!queue.isEmpty()) {
			Town currentTown = queue.poll();
			if(!exploredVertices.contains(currentTown)) {
				Set<Road> connectingRoads = edgesOf(currentTown);
				Iterator<Road> iterator = connectingRoads.iterator();
				while(iterator.hasNext()) {
					Road currentRoad = iterator.next();
					currentRoad = checkSwapDestinationAndSource(currentRoad, currentTown);
					Town nextTown = currentRoad.getDestination();
					queue.add(nextTown);
					if(currentRoad.getWeight() + WeightMap.get(currentTown.getName()) < WeightMap.get(nextTown.getName())) {
						WeightMap.replace(nextTown.getName(), currentRoad.getWeight() + WeightMap.get(currentTown.getName()));
						prevTownMap.put(nextTown, currentTown);
					}
				}
				exploredVertices.add(currentTown);
			}
		}
	}
	/**
	 * A checker to make sure the source of a road comes before the destination and vice versa
	 * @param road the road you want to check for the swap
	 * @param knownSourceTown the town that is the known source town
	 * @return returns a new road if it is flipped
	 */
	private Road checkSwapDestinationAndSource(Road road, Town knownSourceTown) {
		if(road.getDestination().equals(knownSourceTown)) {
			Road oldRoad = road;
			road = new Road(knownSourceTown, road.getSource(), road.getWeight(), road.getName());
			roads.add(road);
			roads.remove(oldRoad);
		}
		return road;
	}
}
