package src;

import java.util.ArrayList;

/// Cargo Bay of the rocket
/// @author Ahren
public class CargoBay implements Comparable<CargoBay>
{
    
    private int maxWeight;
    private int currValue;
    private int currWeight;
    
    private ArrayList<Payload> cargoManifest;
    
    /// constructor
    /// takes the max weight allowed in the cargo bay
    /// @param int maxWeight
    public CargoBay(int maxWeight)
    {
        this.maxWeight = maxWeight;
        cargoManifest = new ArrayList<Payload>();
        currValue = 0;
        currWeight = 0;
    }
    
    public CargoBay(CargoBay other)
    {
        
        cargoManifest = new ArrayList<Payload>(other.getCargoManifest());
        maxWeight = other.getMaxWeight();
        currValue = other.getCargoValue();
        currWeight = other.getCargoWeight();
    }
    
    /// add a payload to the CargoBay
    /// throws Exception if adding the payload results
    /// in overloading the cargo bay
    /// @param Payload newLoad
    public void addPayload(Payload newLoad) throws Exception
    {
        
        if (currWeight + newLoad.getWeight() <= maxWeight)
        {
            if (cargoManifest.contains(newLoad))
            {
                throw new Exception("Payload Already In Manifest");
            }
            else
                currWeight += newLoad.getWeight();
        }
        
        else
        {
            throw new Exception("Adding Payload Exceeds Max Cargo Weight");
        }
        
        currValue += newLoad.getValue();
        cargoManifest.add(newLoad);
        
    }
    
    public void removePayload(Payload target)
    {
        cargoManifest.remove(target);
        currWeight -= target.getWeight();
        currValue  -= target.getValue();
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
    
    @Override
    public int compareTo(CargoBay other)
    {
        if (currWeight < other.getCargoWeight())
        {
            return -1;
        }
        
        else if (currWeight > other.getCargoWeight())
        {
            return 1;
        }
        
        else
        {
            return 0;
        }
        
    }
    
}
