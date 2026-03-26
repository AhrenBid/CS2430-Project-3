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
    
    public LoadingProcedure(ArrayList<Payload> cargoCatagogue)
    {
        this.cargoCatalogue = new ArrayList<Payload>(cargoCatalogue);
    }
    
    public CargoBay loadHighestRating()
    {
        CargoBay ratingBay = new CargoBay(700);
        
        return ratingBay;
    }
    
    public CargoBay loadLightest()
    {
        CargoBay lightestBay = new CargoBay(700);
        
        return lightestBay;
    }
    
    public CargoBay loadBestRatio()
    {
        CargoBay ratioBay = new CargoBay(700);
        
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
