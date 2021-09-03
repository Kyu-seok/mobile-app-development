package ...;

import java.util.Random;

/**
 * Represents the overall map, and contains a grid of MapElement objects (accessible using the
 * get(row, col) method). The two static constants WIDTH and HEIGHT indicate the size of the map.
 *
 * There is a static get() method to be used to obtain an instance (rather than calling the
 * constructor directly).
 *
 * There is also a regenerate() method. The map is randomly-generated, and this method will invoke
 * the algorithm again to replace all the map data with a new randomly-generated grid.
 */
public class MapData
{
    public static final int WIDTH = 30;
    public static final int HEIGHT = 10;

    private static final int WATER = R.drawable.ic_water;
    private static final int[] GRASS = {R.drawable.ic_grass1, R.drawable.ic_grass2,
            R.drawable.ic_grass3, R.drawable.ic_grass4};

    private static final Random rng = new Random();
    private MapElement[][] grid;

    private static MapData instance = null;

    public static MapData get()
    {
        if(instance == null)
        {
            instance = new MapData(generateGrid());
        }
        return instance;
    }

    private static MapElement[][] generateGrid()
    {
        final int HEIGHT_RANGE = 256;
        final int WATER_LEVEL = 112;
        final int INLAND_BIAS = 24;
        final int AREA_SIZE = 1;
        final int SMOOTHING_ITERATIONS = 2;

        int[][] heightField = new int[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++)
        {
            for(int j = 0; j < WIDTH; j++)
            {
                heightField[i][j] =
                    rng.nextInt(HEIGHT_RANGE)
                    + INLAND_BIAS * (
                        Math.min(Math.min(i, j), Math.min(HEIGHT - i - 1, WIDTH - j - 1)) -
                        Math.min(HEIGHT, WIDTH) / 4);
            }
        }

        int[][] newHf = new int[HEIGHT][WIDTH];
        for(int s = 0; s < SMOOTHING_ITERATIONS; s++)
        {
            for(int i = 0; i < HEIGHT; i++)
            {
                for(int j = 0; j < WIDTH; j++)
                {
                    int areaSize = 0;
                    int heightSum = 0;

                    for(int areaI = Math.max(0, i - AREA_SIZE);
                            areaI < Math.min(HEIGHT, i + AREA_SIZE + 1);
                            areaI++)
                    {
                        for(int areaJ = Math.max(0, j - AREA_SIZE);
                                areaJ < Math.min(WIDTH, j + AREA_SIZE + 1);
                                areaJ++)
                        {
                            areaSize++;
                            heightSum += heightField[areaI][areaJ];
                        }
                    }

                    newHf[i][j] = heightSum / areaSize;
                }
            }

            int[][] tmpHf = heightField;
            heightField = newHf;
            newHf = tmpHf;
        }

        MapElement[][] grid = new MapElement[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++)
        {
            for(int j = 0; j < WIDTH; j++)
            {
                MapElement element;

                if(heightField[i][j] >= WATER_LEVEL)
                {
                    boolean waterN = (i == 0)          || (heightField[i - 1][j] < WATER_LEVEL);
                    boolean waterE = (j == WIDTH - 1)  || (heightField[i][j + 1] < WATER_LEVEL);
                    boolean waterS = (i == HEIGHT - 1) || (heightField[i + 1][j] < WATER_LEVEL);
                    boolean waterW = (j == 0)          || (heightField[i][j - 1] < WATER_LEVEL);

                    boolean waterNW = (i == 0) ||          (j == 0) ||         (heightField[i - 1][j - 1] < WATER_LEVEL);
                    boolean waterNE = (i == 0) ||          (j == WIDTH - 1) || (heightField[i - 1][j + 1] < WATER_LEVEL);
                    boolean waterSW = (i == HEIGHT - 1) || (j == 0) ||         (heightField[i + 1][j - 1] < WATER_LEVEL);
                    boolean waterSE = (i == HEIGHT - 1) || (j == WIDTH - 1) || (heightField[i + 1][j + 1] < WATER_LEVEL);

                    boolean coast = waterN || waterE || waterS || waterW ||
                                    waterNW || waterNE || waterSW || waterSE;

                    grid[i][j] = new MapElement(
                        !coast,
                        choose(waterN, waterW, waterNW,
                            R.drawable.ic_coast_north, R.drawable.ic_coast_west,
                            R.drawable.ic_coast_northwest, R.drawable.ic_coast_northwest_concave),
                        choose(waterN, waterE, waterNE,
                            R.drawable.ic_coast_north, R.drawable.ic_coast_east,
                            R.drawable.ic_coast_northeast, R.drawable.ic_coast_northeast_concave),
                        choose(waterS, waterW, waterSW,
                            R.drawable.ic_coast_south, R.drawable.ic_coast_west,
                            R.drawable.ic_coast_southwest, R.drawable.ic_coast_southwest_concave),
                        choose(waterS, waterE, waterSE,
                            R.drawable.ic_coast_south, R.drawable.ic_coast_east,
                            R.drawable.ic_coast_southeast, R.drawable.ic_coast_southeast_concave),
                        null);
                }
                else
                {
                    grid[i][j] = new MapElement(
                        false, WATER, WATER, WATER, WATER, null);
                }
            }
        }
        return grid;
    }

    private static int choose(boolean nsWater, boolean ewWater, boolean diagWater,
        int nsCoastId, int ewCoastId, int convexCoastId, int concaveCoastId)
    {
        int id;
        if(nsWater)
        {
            if(ewWater)
            {
                id = convexCoastId;
            }
            else
            {
                id = nsCoastId;
            }
        }
        else
        {
            if(ewWater)
            {
                id = ewCoastId;
            }
            else if(diagWater)
            {
                id = concaveCoastId;
            }
            else
            {
                id = GRASS[rng.nextInt(GRASS.length)];
            }
        }
        return id;
    }

    protected MapData(MapElement[][] grid)
    {
        this.grid = grid;
    }

    public void regenerate()
    {
        this.grid = generateGrid();
    }

    public MapElement get(int i, int j)
    {
        return grid[i][j];
    }
}
