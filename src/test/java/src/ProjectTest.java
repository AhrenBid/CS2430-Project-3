/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package src;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author abidling
 */
public class ProjectTest {
    
    public ProjectTest(){}
    
    Payload cloudPatterns          = new Payload("Cloud Patterns",          36, 5);
    Payload solarFlares            = new Payload("Solar Flares",           264, 9);
    Payload solarPower             = new Payload("Solar Power",            188, 6);
    Payload binaryStars            = new Payload("Binary Stars",           203, 8);
    Payload relativity             = new Payload("Relativity",             104, 8);
    Payload seedViability          = new Payload("Seed Viability",           7, 4);
    Payload sunSpots               = new Payload("Sun Spots",               90, 2);
    Payload miceTumors             = new Payload("Mice Tumors",             65, 8);
    Payload microgravityPlantGrowth= new Payload("Microgravity Plant Growth", 75, 5);
    Payload microMeteorites        = new Payload("Micrometeorites",        170, 9);
    Payload cosmicRays             = new Payload("Cosmic Rays",             80, 7);
    Payload yeastFermentation      = new Payload("Yeast Fermentation",      27, 7);
    
    ArrayList<Payload> catalogue = new ArrayList<>(List.of(cloudPatterns, solarFlares,
            solarPower, binaryStars, relativity, seedViability, sunSpots, miceTumors,
            microgravityPlantGrowth, microMeteorites, cosmicRays, yeastFermentation));
    
    LoadingProcedure load = new LoadingProcedure(catalogue);
    
    public String printManifest(ArrayList<Payload> manifest)
    {
        StringBuilder bld = new StringBuilder();
        
        int totalValue = 0;
        int totalWeight = 0;
        
        bld.append(String.format("| %26s | %5s | %6s | %5s |\n", "Name", "Value", "Weight", "Ratio"));
        for (Payload current : manifest)
        {
            bld.append(current.toString());
            totalValue += current.getValue();
            totalWeight += current.getWeight();
            
            bld.append("\n");
        }
        
        bld.append(String.format("| %26s | %2dpts | %4dkg | %5s |\n", "Totals", totalValue, totalWeight, ""));
        
        return bld.toString();
    }
    
    @BeforeAll
    public static void setUpClass() 
    {
        
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void loadHighestRating()
    {
        
        /*
        cargobay expected to contain:
        
        1  solarFlares              264    9
        2  microMeteorites          170    9
        3  binaryStars              203    8
        4  cloudPatterns             36    5
        5  yeastFermentation         27    4
        */
        
        CargoBay loadHighestRating = load.loadHighestRating();
        
        ArrayList<Payload> manifest = loadHighestRating.getCargoManifest();
        
        System.out.println("Load Highest Rating\n");
        System.out.println(printManifest(manifest));
        
        
        assertTrue(manifest.contains(solarFlares));
        assertTrue(manifest.contains(microMeteorites));
        assertTrue(manifest.contains(binaryStars));
        assertTrue(manifest.contains(cloudPatterns));
        assertTrue(manifest.contains(yeastFermentation));
        
        assertTrue(loadHighestRating.getCargoWeight() <= 700);
    }
    
    @Test
    public void loadLightest()
    {
       /*
        1  Seed Viability              7    4
        2  Yeast Fermentation         27    4
        3  Cloud Patterns             36    5
        4  Mice Tumors                65    8
        5  Cosmic Rays                80    7
        6  Relativity                104    8
        7  Microgravity Plant Growth  75    5
        8  Micrometeorites           170    9
        */
        
        CargoBay loadLightest = load.loadLightest();
        
        ArrayList<Payload> manifest = loadLightest.getCargoManifest();
        
        assertTrue(manifest.contains(cloudPatterns));
        assertTrue(manifest.contains(relativity));
        assertTrue(manifest.contains(seedViability));
        assertTrue(manifest.contains(sunSpots));
        assertTrue(manifest.contains(miceTumors));
        assertTrue(manifest.contains(microgravityPlantGrowth));
        assertTrue(manifest.contains(microMeteorites));
        assertTrue(manifest.contains(cosmicRays));
        assertTrue(manifest.contains(yeastFermentation));
        
        assertTrue(loadLightest.getCargoWeight() <=700);
        
        System.out.println("Load Lightest\n");
        System.out.println(printManifest(manifest));
        
        
        
        
    }
    
    @Test
    public void loadBestRatio()
    {
        /*
        expected in the cargobay:
        
        1  seedViability              7    4
        2  yeastFermentation         27    4
        3  cloudPatterns             36    5
        4  miceTumors                65    8
        5  cosmicRays                80    7
        6  relativity               104    8
        7  microgravityPlantGrowth   75    5
        8  microMeteorites          170    9

        */
        
        CargoBay loadRatio = load.loadBestRatio();
        
        ArrayList<Payload> manifest = loadRatio.getCargoManifest();
        
        System.out.println("Load Best Ratio\n");
        System.out.println(printManifest(manifest));
        
        
        assertTrue(manifest.contains(seedViability));
        assertTrue(manifest.contains(yeastFermentation));
        assertTrue(manifest.contains(cloudPatterns));
        assertTrue(manifest.contains(miceTumors));
        assertTrue(manifest.contains(cosmicRays));
        assertTrue(manifest.contains(relativity));
        assertTrue(manifest.contains(microgravityPlantGrowth));
        assertTrue(manifest.contains(microMeteorites));
        
        
        assertTrue(loadRatio.getCargoWeight() <= 700);
        
        
    }
    
    @Test
    public void loadBruteForce()
    {
        /*
        
        expected in the cargo manifest:
        
        solarFlares
        binaryStars
        relativity
        cosmicRays
        cloudPatterns
        yeastFermentation
        seedViability

        */
        
        CargoBay loadBruteForce = load.loadBruteForce().get(0);
        
        ArrayList<Payload> manifest = loadBruteForce.getCargoManifest();
        
        System.out.println("Load Brute Force\n");
        System.out.println(printManifest(manifest));
        
        assertTrue(manifest.contains(solarFlares));
        assertTrue(manifest.contains(binaryStars));
        assertTrue(manifest.contains(relativity));
        assertTrue(manifest.contains(cosmicRays));
        assertTrue(manifest.contains(cloudPatterns));
        assertTrue(manifest.contains(yeastFermentation));
        assertTrue(manifest.contains(seedViability));
        
        
        assertTrue(loadBruteForce.getCargoWeight() <= 700);
        
        
        
    }
}
