/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;


/// @author Ahren
/// Payload class, holds all of the details of the payloads
/// of the rocket.
public class Payload {
    
    int weight;
    int value;
    double ratio;
    
    String name;
    
    /// constructor
    /// @param name   //holds the name of the payload, only used in toString()
    /// @param weight //self explanatory
    /// @param value  //self explanatory
    public Payload (String name, int weight, int value) throws IllegalArgumentException
    {
        if (weight > 0)
        {
            this.weight = weight;
        }
        
        else
        {
            throw new IllegalArgumentException("Illegal Weight");
        }
        
        
        if (value>=0)
        {
            this.value = value;
        }
        
        else
        {
            throw new IllegalArgumentException("Illegal Value");
        }
        this.name = name;
        
        ratio = (double) value / weight;
        
    }
    
    /// return the weight of the payload
    public int getWeight()
    {
        return weight;
    }
    
    /// return the value of the payload
    public int getValue()
    {
        return value;
    }
    
    /// return the value to weight ratio of the payload as a double
    public double getRatio()
    {
        return ratio;
    }
    
    /// returns "| <name> | <value> | <weight> | <ratio> |"
    /// spacing "| %8s | %3d | %3d | %2.2f |"
    /// no new line
    @Override 
    public String toString()
    {
        return String.format("| %26s | %5d | %6d | %5.2f |", name, value, weight, ratio);
    }
    
    
    
}
