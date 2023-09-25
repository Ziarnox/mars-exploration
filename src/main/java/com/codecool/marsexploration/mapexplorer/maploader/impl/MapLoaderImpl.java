package com.codecool.marsexploration.mapexplorer.maploader.impl;

import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoaderImpl implements MapLoader {
    @Override
    public Map load(String mapFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mapFile));
            String line;
            StringBuilder mapContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                mapContent.append(line).append("\n");
            }

            reader.close();

            String[] rows = mapContent.toString().split("\n");
            int numRows = rows.length;
            int numCols = rows[0].length();
            String[][] mapRepresentation = new String[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    mapRepresentation[i][j] = Character.toString(rows[i].charAt(j));
                }
            }

            return new Map(mapRepresentation, true);
        } catch (IOException e) {
            e.printStackTrace();
            return new Map(new String[0][0], false);
        }
    }
}