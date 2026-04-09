/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.util.ArrayList;
import java.util.Collections;



/**
 *
 * @author abidling
 */
public class LoadingProcedure 
{
    
    ArrayList<Payload> cargoCatalogue;
    
    public LoadingProcedure(ArrayList<Payload> cargoCatalogue)
    {
        this.cargoCatalogue = new ArrayList<Payload>(cargoCatalogue);
    }
    
    ///@author Andrew
    ///Adds the highest rated items first
    public CargoBay loadHighestRating()
    {
        CargoBay ratingBay = new CargoBay(700);
    	   
        ArrayList<Payload> dupCatalogue = new ArrayList<>(cargoCatalogue);

        while (true) {
            Payload bestRating = null;
            
            //loops through catalogue to find item with the max ratio
            for (int i = 0; i < dupCatalogue.size(); i++) {
                Payload current = dupCatalogue.get(i);
             //checks if weight keeps total under 700 and updates bestRatio if needed
                if (current.getWeight() + ratingBay.getCargoWeight() <= 700) {
                    if (bestRating == null || current.getValue() > bestRating.getValue()) { // small copy-paste bug, you were using getRatio rather than getValue, 
                        bestRating = current;                                               // and this meant that this method was doing exactly the same as the load by ratio method -- Ahren
                    }
                }
            }

            if (bestRating == null) {
                break;
            }
            //Prevents exception error since addPayload throws exception
            try {
                ratingBay.addPayload(bestRating);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            dupCatalogue.remove(bestRating);
        }
        
        return ratingBay;
	}	
    
    //@author Andrew
    //Adds the lightest items first
    public CargoBay loadLightest() {
        CargoBay lightestBay = new CargoBay(700);
        //Duplicate catalogue to prevent changes
        ArrayList<Payload> dupCatalogue = new ArrayList<>(cargoCatalogue);

        while (true) {
            Payload lightest = null;
            
            //loops through catalogue to find item with the minimum weight
            for (int i = 0; i < dupCatalogue.size(); i++) {
                Payload current = dupCatalogue.get(i);
             //checks if weight keeps total under 700 and updates lightest if needed
                if (current.getWeight() + lightestBay.getCargoWeight() <= 700) {
                    if (lightest == null || current.getWeight() < lightest.getWeight()) {
                        lightest = current;
                    }
                }
            }

            if (lightest == null) {
                break;
            }
            //Prevents exception error since addPayload throws exception
            try {
                lightestBay.addPayload(lightest);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            dupCatalogue.remove(lightest);
        }

        return lightestBay;
    }
        
    
    ///@author Andrew
    ///Adds items with the best ratio first
    public CargoBay loadBestRatio()
    {
        CargoBay ratioBay = new CargoBay(700);
        
        ArrayList<Payload> dupCatalogue = new ArrayList<>(cargoCatalogue);

        while (true) {
            Payload bestRatio = null;
            
            //loops through catalogue to find item with the max ratio
            for (int i = 0; i < dupCatalogue.size(); i++) {
                Payload current = dupCatalogue.get(i);
             //checks if weight keeps total under 700 and updates bestRatio if needed
                if (current.getWeight() + ratioBay.getCargoWeight() <= 700) {
                    if (bestRatio == null || current.getRatio() > bestRatio.getRatio()) {
                        bestRatio = current;
                    }
                }
            }

            if (bestRatio == null) {
                break;
            }
            //Prevents exception error since addPayload throws exception
            try {
                ratioBay.addPayload(bestRatio);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            dupCatalogue.remove(bestRatio);
        }
        
        return ratioBay;
    }
    
// Loads every possible combination of payloads and returns 
// the top 3 combinations that are under the weight limit of 700
public ArrayList<CargoBay> loadBruteForce() {
    ArrayList<CargoBay> validSubsets = new ArrayList<CargoBay>();
    
    //loops through every possible combination of payloads using a bitmask
    for (int mask = 0; mask < 4096; mask++) {
        CargoBay forceBay = new CargoBay(700);
        
        // loops through all 12 payloads and adds the current subset to the cargo bay
        for (int i = 0; i < 12; i++) {
            if ((mask & (1 << i)) != 0) {
                try {
                    forceBay.addPayload(cargoCatalogue.get(i));
                } catch (Exception e) {
                    // Skip past excpetion
                }
            }
        }
        
        // Only takes in valid combinations of payloads that are under 700
        if (forceBay.getCargoWeight() <= 700) {
            validSubsets.add(forceBay);
        }
    }
    // Sorts valids subsets and adds the top 3 into array list top 3
    validSubsets.sort((a, b) -> b.getCargoValue() - a.getCargoValue());
    ArrayList<CargoBay> top3 = new ArrayList<CargoBay>();
    top3.add(validSubsets.get(0));
    top3.add(validSubsets.get(1));
    top3.add(validSubsets.get(2));
    return top3;
}
    /// @author Ahren
    /// returns a CargoBay loaded using dynamic programming
    public CargoBay loadDynamic()
    {
        // uses load highest as the starting point loadBestRatio would be 
        // a better starting point, but I want to demontrate the improvement 
        // that comes from the dynamic programming portion
        CargoBay startingPoint = loadHighestRating();
        
        // improve is the recursive helper method that actually holds the logic
        return improve(startingPoint);
    }
    
    /// @author Ahren
    /// recursive helper for loadDynamic
    private CargoBay improve(CargoBay old)
    {
        // memory safe copy of old
        CargoBay startingPoint = new CargoBay(old);
        
        // lists to keep track of which payloads used or left unused
        ArrayList<Payload> used = startingPoint.getCargoManifest();
        ArrayList<Payload> unused = new ArrayList<>(cargoCatalogue);
        unused.removeAll(used);
        
        // CargoBay to work in, used to keep changes out of startingPoint
        // til I'm certain of what I want to stick
        CargoBay theoretical = new CargoBay(startingPoint);
        
        
        /*
        Brief summary of algorithm
        
        for each loaded payload (loop 1)
            
            remove that payload
            
            check if any of the unused payloads fit (loop 2)
        
            if one does, check if the replacement makes the CargoBay lighter
        
                in that case, check if any other unused payloads can be added on top (loop 3)
        
                if a further payload can be added on top check if it improves the CargoBay score
        
                if it improves the CargoBay score, write the changes into startingPoint
            
                recursively call improve on the new version of startingPoint to look for further improvements
        
        if no improvements were made, no more improvements can be made, so return startingPoint
        
        in all cases, if the payload either doesn't fit or doesn't improve things, revert all changes and move on to the next
        */
        for(Payload loaded : used)
        {
            // remove the current payload to make room for the possible payloads
            theoretical.removePayload(loaded);
            
            // go through the list of all unused to see if they make good replacements
            for(Payload possible : unused)
            {
                
                // if the payload can't be added in replacement of the one that was in there, skip it.
                if (theoretical.getCargoManifest().contains(possible))        {continue;}
                if (theoretical.getCargoWeight() + possible.getWeight() > 700){continue;}

                try{theoretical.addPayload(possible);}
                catch(Exception e){e.printStackTrace();}

                // if the weight improves, check if any other Payloads can be added on top to improve the score
                if (theoretical.getCargoWeight() < startingPoint.getCargoWeight())
                {
                    // for each unused payload, try adding it
                    for (Payload second : unused)
                    {
                        // if the payload can't be added in replacement of the one that was in there, skip it.
                        if (theoretical.getCargoManifest().contains(second))        {continue;}
                        if (theoretical.getCargoWeight() + second.getWeight() > 700){continue;}
                        
                        // add it
                        try{theoretical.addPayload(second);}
                        catch (Exception e){e.printStackTrace();}
 
                            
                            // if the score improves on the starting point
                            if (theoretical.getCargoValue() > startingPoint.getCargoValue())
                            {
                                // remove what was originally in that slot
                                startingPoint.removePayload(loaded);
                                
                                // add the first and second payloads
                                try
                                {
                                    startingPoint.addPayload(possible);
                                    startingPoint.addPayload(second);
                                }
                                catch(Exception e){e.printStackTrace();}
                                
                                // recursively call to attempt a further improvement
                                return improve(startingPoint);
                            }
                            
                            // if the score doesn't improve, remove the second possible payload and try the next
                            else
                            {
                                theoretical.removePayload(second);
                            }
                    }
                }
                
                // no improvement was made remove the first possible payload
                theoretical.removePayload(possible);
                
                
            }
            
            // no improvement was made, revert the cargoBay back to original by adding the original payload back in
            try{theoretical.addPayload(loaded);}
            catch(Exception e){e.printStackTrace();}
        }
            
            // base case, if there was no improvement on this recursion no further
            // recursion can improve things, so just return the startingPoint
            return startingPoint;
    }
    

    
}
