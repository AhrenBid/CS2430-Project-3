package src;

import java.util.ArrayList;

/// Cargo Bay of the rocket
/// @author Ahren
public class CargoBay 
{
    
    int maxWeight;
    int currValue;
    int currWeight;
    
    ArrayList<Payload> cargoManifest;
    
    /// constructor
    /// takes the max weight allowed in the cargo bay
    /// @param int maxWeight
    public CargoBay(int maxWeight)
    {
        this.maxWeight = maxWeight;
    }
    
    /// add a payload to the CargoBay
    /// throws Exception if adding the payload results
    /// in overloading the cargo bay
    /// @param Payload newLoad
    public void addPayload(Payload newLoad) throws Exception
    {
        
        if (currWeight + newLoad.getWeight() <= maxWeight)
        {
            currWeight += newLoad.getWeight();
        }
        
        else
        {
            throw new Exception("Adding Payload Exceeds Max Cargo Weight");
        }
        
        currValue += newLoad.getValue();
        cargoManifest.add(newLoad);
        
    }
    
    /// get the current total value inside of the cargo bay
    public int getCargoValue()
    {
        return currValue;
    }
    
    /// get the current total weight of the cargo
    public int getCargoWeight()
    {
        return currWeight;
    }
    
    /// get the maximum allowed weight of the cargo bay
    public int getMaxWeight()
    {
        return maxWeight;
    }
    
    /// get the cargo manifest
    public ArrayList<Payload> getCargoManifest()
    {
        return new ArrayList<Payload>(cargoManifest);
    }
    
}
