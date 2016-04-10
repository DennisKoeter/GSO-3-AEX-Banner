/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner.source;

/**
 *
 * @author Dennis
 */
public interface IFonds {
    /**
     * gets the name of this object
     * @return a string containing the name
     */
    public String getNaam();
    
    /**
     * gets the exchange rate of this object
     * @return a double containing the value
     */
    public double getKoers();
}
