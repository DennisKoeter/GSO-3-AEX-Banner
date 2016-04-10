/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Dennis
 */
public class MockEffectenbeurs implements IEffectenbeurs{
    private List<IFonds> fondsen;
    private Timer timer;
    private Random random;
    
    // constructor with pre-made list
    public MockEffectenbeurs(List<IFonds> f) {
        this.fondsen = f;
        this.timer = new Timer();
        this.random = new Random();
        start();
    }
    
    // contructor without pre-made list
    public MockEffectenbeurs() {
        this.fondsen = generateMockList();
        this.timer = new Timer();
        this.random = new Random();
        start();
    }
    
    /**
     * a helper method to generate a fake list of Fonds objects
     * @return the created list
     */
    private List<IFonds> generateMockList(){
        List<IFonds> f = new ArrayList<>();
        f.add(new Fonds("ING", 5.01));
        f.add(new Fonds("Bunq", 85.22));
        f.add(new Fonds("SNS", 50.98));
        f.add(new Fonds("Rabobank", 60.04));
        return f;
    }
    
    /**
     * starting the timer, every 2 seconds the stock exchange is randomly changed
     */
    private void start() {
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(IFonds f : fondsen)
                   ((Fonds) f).setKoers(random.nextDouble() + random.nextInt(100));
            }      
        }, 0, 2000);
    }
    
    /**
     * stopping the timer
     */
    public void cancel() {
        this.timer.cancel();
    }
    
    /**
     * adding an exchange object to the list
     * @param f the object to be added
     */
    public void addKoers(IFonds f) {
        fondsen.add(f);
    }
    
    /**
     * removing an exchange object from the list
     * @param f the object to be removed
     */
    public void removeKoers(IFonds f) {
        fondsen.remove(f);
    }
    
    @Override
    public List<IFonds> getKoersen() {
        return Collections.unmodifiableList(fondsen);
    }
}
