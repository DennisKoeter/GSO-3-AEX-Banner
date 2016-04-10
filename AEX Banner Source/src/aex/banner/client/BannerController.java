package aex.banner.client;

import java.util.Timer;
import aex.banner.source.*;
import java.util.TimerTask;
import javafx.application.Platform;

public class BannerController {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner) {
        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.schedule(new Task(this.banner, this.effectenbeurs), 0, 2000);
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        ((MockEffectenbeurs) effectenbeurs).cancel();
    }
    
    static class Task extends TimerTask {
        AEXBanner banner;
        IEffectenbeurs effectenbeurs;

        public Task(AEXBanner banner, IEffectenbeurs beurs) {
            this.banner = banner;
            this.effectenbeurs = beurs;
        }
        
        @Override
        public void run() {
            final StringBuilder sb = new StringBuilder();
            
            for(IFonds f : effectenbeurs.getKoersen()){
                sb.append(f.getNaam() + " ");
                if(f.getKoers() < 10){
                    sb.append("0");
                }
                sb.append(String.format("%2.2f", f.getKoers()));
                sb.append("   ");
            }
            
            
            /**
             * Deze manier was niet effectief...
             * De string werd steeds verkeerd neergezet
             */
            
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                //do handling stuff
//            }
//            
//            banner.setKoersen(sb.toString());
            
            /**
             * Deze manier werkt wel
             */
            
            Platform.runLater(new Runnable(){

                @Override
                public void run() {
                    banner.setKoersen(sb.toString());
                }
                
            });
        } 
    }
}