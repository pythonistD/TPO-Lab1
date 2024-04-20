package cheboksarov.task3;

import java.util.List;

public class God {
    private Planet planet;
    private List<Whale> whateverList;

    public God(Planet planet) {
        this.planet = planet;
    }

    public void spawnWhale(Whale whale, Integer height) {
        Integer timeOfFalling = planet.calcTimeOfFalling(height);
        System.out.println("Кит появился на высоте " + height);
        for(int i = 0; i < timeOfFalling; i += whale.getThinkRate()){
            String thought = whale.getCurrentWhaleThought(i);
            System.out.println(thought);
        }
        System.out.println("А далее, после внезапного влажного удара, была тишина.");
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public List<Whale> getWhateverList() {
        return whateverList;
    }

    public void setWhateverList(List<Whale> whateverList) {
        this.whateverList = whateverList;
    }
}
