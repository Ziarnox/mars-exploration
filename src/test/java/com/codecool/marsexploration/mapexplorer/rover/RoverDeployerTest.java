package com.codecool.marsexploration.mapexplorer.rover;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.core.ColumnOrderDependent;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverDeployerTest {

    RoverDeployer roverDeployerTester = new RoverDeployer();
    @Test
    public void EmptyMapTestCorner() {
        Coordinate spaceshipCoordinate = new Coordinate(0,0);
        Map map = new Map(new String[][] {
                {" ", " ", " "},
                {" ", "S", " "},
                {" ", " ", " "}
        }, true);
        assertEquals(new Coordinate (1,0), roverDeployerTester.findDeploymentCoordinate(spaceshipCoordinate, map));
    }
    @Test
    public void EmptyMapTestMiddle() {
        Coordinate spaceshipCoordinate = new Coordinate(1,1);
        Map map = new Map(new String[][] {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        }, true);
        assertEquals(new Coordinate (0,0), roverDeployerTester.findDeploymentCoordinate(spaceshipCoordinate, map));
    }

    @Test
    public void HalfFullMapTestMiddle() {
        Coordinate spaceshipCoordinate = new Coordinate(1,1);
        Map map = new Map(new String[][] {
                {"R", "R", "R"},
                {"R", "S", " "},
                {" ", " ", " "}
        }, true);
        System.out.println(map.getByCoordinate(new Coordinate(2,1)));
        assertEquals(new Coordinate (2,1), roverDeployerTester.findDeploymentCoordinate(spaceshipCoordinate, map));
    }

    @Test
    public void FullMapTestMiddle() {
        Coordinate spaceshipCoordinate = new Coordinate(1,1);
        Map map = new Map(new String[][] {
                {" ", " ", " "},
                {" ", "S", " "},
                {" ", " ", " "}
        }, true);
        assertEquals(null, roverDeployerTester.findDeploymentCoordinate(spaceshipCoordinate, map));
    }
}

// 00 .. 02
// .. xx ..
// 20 .. 22