import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {
	private TownGraphManager sinnohMap;
	  
	@BeforeEach
	public void setUp() throws Exception {
		sinnohMap = new TownGraphManager();
	
		sinnohMap.addTown("Canalave City");
		sinnohMap.addTown("Jubilife City");
		sinnohMap.addTown("Oreburgh City");
		sinnohMap.addTown("Floaroma City");
		sinnohMap.addTown("Sandgem Town");
		sinnohMap.addTown("Twin Leaf Town");
		sinnohMap.addTown("Pal Park");
		sinnohMap.addTown("Eterna City");
		sinnohMap.addTown("Hearthome City");
		sinnohMap.addTown("Pastoria City");
		sinnohMap.addTown("Solaceon City");
		sinnohMap.addTown("Celestic City");
		sinnohMap.addTown("Veilstone City");
		sinnohMap.addTown("Sunyshore City");
		sinnohMap.addTown("Elite 4");
		sinnohMap.addTown("Snowpoint City");
		
		sinnohMap.addRoad("Pal Park", "Sandgem Town", 3, "221");
		sinnohMap.addRoad("Twin Leaf Town", "Sandgem Town", 1, "201");
		sinnohMap.addRoad("Jubilife City", "Sandgem Town", 1, "202");
		sinnohMap.addRoad("Jubilife City", "Canalave City", 2, "218");
		sinnohMap.addRoad("Jubilife City", "Oreburgh City", 2, "203");
		sinnohMap.addRoad("Jubilife City", "Floaroma City", 2, "204");
		sinnohMap.addRoad("Eterna City", "Floaroma City", 3, "205");
		sinnohMap.addRoad("Eterna City", "Oreburgh City", 4, "206");
		sinnohMap.addRoad("Hearthome City", "Oreburgh City", 4, "207");
		sinnohMap.addRoad("Eterna City", "Celestic City", 2, "211");
		sinnohMap.addRoad("Solaceon City", "Celestic City", 5, "210");
		sinnohMap.addRoad("Solaceon City", "Hearthome City", 3, "209");
		sinnohMap.addRoad("Pastoria City", "Hearthome City", 5, "212");
		sinnohMap.addRoad("Pastoria City", "Veilstone City", 8, "213");
		sinnohMap.addRoad("Solaceon City", "Veilstone City", 4, "215");
		sinnohMap.addRoad("Celestic City", "Veilstone City", 6, "210");
		sinnohMap.addRoad("Sunyshore City", "Veilstone City", 7, "214");
		sinnohMap.addRoad("Pastoria City", "Sunyshore City", 6, "222");
		sinnohMap.addRoad("Elite 4", "Sunyshore City", 3, "223");
		sinnohMap.addRoad("Snowpoint City", "Oreburgh City", 20, "217");
		sinnohMap.addRoad("Snowpoint City", "Eterna City", 13, "216");
		sinnohMap.addRoad("Snowpoint City", "Celestic City", 13, "Mt. Coronet");
		sinnohMap.addRoad("Hearthome City", "Snowpoint City", 19, "208");
	}

	@After
	public void tearDown() throws Exception {
		sinnohMap = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = sinnohMap.allRoads();
		sinnohMap.addTown("Lake Verity");
		assertEquals("201", roads.get(0));
		assertEquals("202", roads.get(1));
		assertEquals("203", roads.get(2));
		assertEquals("204", roads.get(3));
		sinnohMap.addRoad("Lake Verity", "Sandgem Town", 1,"101");
		roads = sinnohMap.allRoads();
		assertEquals("101", roads.get(0));
		assertEquals("201", roads.get(1));
		assertEquals("202", roads.get(2));
		assertEquals("203", roads.get(3));
		assertEquals("204", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("218", sinnohMap.getRoad("Canalave City", "Jubilife City"));
		assertEquals("206", sinnohMap.getRoad("Eterna City", "Oreburgh City"));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, sinnohMap.containsTown("Full Moon Island"));
		sinnohMap.addTown("Full Moon Island");
		assertEquals(true, sinnohMap.containsTown("Full Moon Island"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, sinnohMap.containsTown("Full Moon Island"));
		sinnohMap.addTown("Full Moon Island");
		ArrayList<String> path = sinnohMap.getPath("Jubilife City","Full Moon Island");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, sinnohMap.containsTown("Twin Leaf Town"));
		assertEquals(false, sinnohMap.containsTown("Full Moon Island"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, sinnohMap.containsRoadConnection("Eterna City", "Snowpoint City"));
		assertEquals(false, sinnohMap.containsRoadConnection("Veilstone City", "Hearthome City"));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = sinnohMap.allRoads();
		assertEquals("201", roads.get(0));
		assertEquals("202", roads.get(1));
		assertEquals("203", roads.get(2));
		assertEquals("204", roads.get(3));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, sinnohMap.containsRoadConnection("Jubilife City", "Oreburgh City"));
		sinnohMap.deleteRoadConnection("Jubilife City", "Oreburgh City", "203");
		assertEquals(false, sinnohMap.containsRoadConnection("Jubilife City", "Oreburgh City"));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, sinnohMap.containsTown("Elite 4"));
		sinnohMap.deleteTown("Elite 4");
		assertEquals(false, sinnohMap.containsTown("Elite 4"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = sinnohMap.allTowns();
		assertEquals("Canalave City", roads.get(0));
		assertEquals("Celestic City", roads.get(1));
		assertEquals("Elite 4", roads.get(2));
		assertEquals("Eterna City", roads.get(3));
		assertEquals("Floaroma City", roads.get(4));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = sinnohMap.getPath("Eterna City", "Elite 4");
		for(int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));
		}
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Eterna City via 211 to Celestic City 2 mi",path.get(0).trim());
		  assertEquals("Celestic City via 210 to Veilstone City 6 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = sinnohMap.getPath("Snowpoint City", "Elite 4");
		for(int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));
		}
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Snowpoint City via Mt. Coronet to Celestic City 13 mi",path.get(0).trim());
		  assertEquals("Celestic City via 210 to Veilstone City 6 mi",path.get(1).trim());
		  assertEquals("Veilstone City via 214 to Sunyshore City 7 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = sinnohMap.getPath("Pastoria City", "Floaroma City");
		for(int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));
		}
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Pastoria City via 212 to Hearthome City 5 mi",path.get(0).trim());
		  assertEquals("Hearthome City via 207 to Oreburgh City 4 mi",path.get(1).trim());
		  assertEquals("Oreburgh City via 203 to Jubilife City 2 mi",path.get(2).trim());
		  assertEquals("Jubilife City via 204 to Floaroma City 2 mi",path.get(3).trim());
	}
}
