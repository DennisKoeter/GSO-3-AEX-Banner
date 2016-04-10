/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner.source;

import java.util.List;

/**
 *
 * @author Dennis
 */
public interface IEffectenbeurs {
    /**
     * gets all exchange rates for the stock exchange
     * @return a list with IFonds objects
     */
    public List<IFonds> getKoersen();
}
