/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.util.ArrayList;



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
                    if (bestRating == null || current.getRatio() > bestRating.getRatio()) {
                        bestRating = current;
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
    
    public CargoBay loadBruteForce()
    {
        CargoBay forceBay = new CargoBay(700);
        
        return forceBay;
    }
    
    public CargoBay loadDynamic()
    {
        CargoBay dynamicBay = new CargoBay(700);
        
        return dynamicBay;
    }
    
}
