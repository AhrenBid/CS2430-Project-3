/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package src;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    
    // initialise payload table
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
    
    // load payload table into a catogue to index into late
    ArrayList<Payload> catalogue = new ArrayList<>(List.of(cloudPatterns, solarFlares,
            solarPower, binaryStars, relativity, seedViability, sunSpots, miceTumors,
            microgravityPlantGrowth, microMeteorites, cosmicRays, yeastFermentation));
    
    // initialise a loading procedure with that catalogue
    LoadingProcedure load = new LoadingProcedure(catalogue);
    
    /// @author Ahren
    /// used to simplify console output code, returns a string that is a table of all of the payloads
    /// along with a totals row at the bottom
    /// Example output:
    ///|                       Name | Value | Weight | Ratio |
    ///|             Seed Viability |     4 |      7 |  0.57 |
    ///|         Yeast Fermentation |     7 |     27 |  0.26 |
    ///|             Cloud Patterns |     5 |     36 |  0.14 |
    ///|                Mice Tumors |     8 |     65 |  0.12 |
    ///|  Microgravity Plant Growth |     5 |     75 |  0.07 |
    ///|                Cosmic Rays |     7 |     80 |  0.09 |
    ///|                  Sun Spots |     2 |     90 |  0.02 |
    ///|                 Relativity |     8 |    104 |  0.08 |
    ///|            Micrometeorites |     9 |    170 |  0.05 |
    ///|                     Totals | 55pts |  654kg |       |
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
    
    /// @author Ahren
    /// JUnit test for loading by highest rating
    /// the other tests for loadRatio, loadLightest, and loadBruteForce are exactly
    /// the same with different assert equals blocks
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
        
        // load the cargoBay using the load highest rating method
        CargoBay loadHighestRating = load.loadHighestRating();
        
        // save the manifest of that cargobay to use it in output
        ArrayList<Payload> manifest = loadHighestRating.getCargoManifest();
        
        // output table with title
        System.out.println("Load Highest Rating\n");
        System.out.println(printManifest(manifest));
        
        // check that the manifest contains what it is expected to
        assertTrue(manifest.contains(solarFlares));
        assertTrue(manifest.contains(microMeteorites));
        assertTrue(manifest.contains(binaryStars));
        assertTrue(manifest.contains(cloudPatterns));
        assertTrue(manifest.contains(yeastFermentation));
        
        // check that  it isn't overweight
        assertTrue(loadHighestRating.getCargoWeight() <= 700);
    }
    
    /// @author Ahren
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
        
        assertTrue(manifest.contains(cloudPatterns));
        assertTrue(manifest.contains(binaryStars));
        assertTrue(manifest.contains(relativity));
        assertTrue(manifest.contains(seedViability));
        assertTrue(manifest.contains(miceTumors));
        assertTrue(manifest.contains(microMeteorites));
        assertTrue(manifest.contains(cosmicRays));
        assertTrue(manifest.contains(yeastFermentation));
        
        assertTrue(loadBruteForce.getCargoWeight() <= 700);
    }
    
    /// @author Ahren
    /// loadDynamic test. Rather than testing for specific payloads, this makes sure that
    /// the returned cargoBay is good enough compared to the brute force method, this is defined as 90% of the
    /// way there
    @Test
    public void testLoadDynamic()
    {
        
        CargoBay loadDynamic = load.loadDynamic();
        CargoBay loadBrute = load.loadBruteForce().get(0);
        
        ArrayList<Payload> manifest = loadDynamic.getCargoManifest();
        
        System.out.println("Load Dynamic\n");
        System.out.println(printManifest(manifest));
        
        assertTrue(loadDynamic.getCargoValue() >= loadBrute.getCargoValue() * 0.9); // 90% of best possible is good enough for now
        
    }
    
    /// @author Ahren
    /// tests that the CargoBay correctly rejects add commands that overfill it
    @Test
    public void testCargoBayMax()
    {
        CargoBay testMax = new CargoBay(1);
        Payload tooBig = new Payload("tooBig", 10, 10);
        
        assertThrows(Exception.class, () -> {testMax.addPayload(tooBig);});
    }
    
    /// @author Ahren
    /// tests that Payload correctly rejects negative weights
    @Test
    public void testPayloadInstantiationIllegalWeight()
    { 
        assertThrows(IllegalArgumentException.class, () -> {new Payload("don't care", 0, 0);});
    }
    
    /// @author Ahren
    /// tests that Payload correctly rejecets negative value
    @Test
    public void testPayloadInstantiationIllegalValue()
    { 
        assertThrows(IllegalArgumentException.class, () -> {new Payload("don't care", 1, -1);});
    }
    
}
