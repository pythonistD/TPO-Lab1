package cheboksarov.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.lang.Math.sqrt;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Planet {
    private Float g; //gravitational constant
    private String name;
    private Integer distanceToStratosphere;


    /*public void fallingOfWhale(Whale whale){
        Integer initialHeight = whale.getHeight();
        int damage = initialHeight/ whale.getDamageRate();
        Integer timeOfFalling = calcTimeOfFalling(initialHeight);
        for(int i=0; i<initialHeight; i++){

            wh

        }

        whale.setHeight(damage);
        whale.setHeight(0);
    }*/

    public Float calcSpeedOfFalling(Float timeOfFalling){
        return g * timeOfFalling;
    }

    public Integer calcTimeOfFalling(Integer height){
        return (int) sqrt(2*height/g);
    }
    public Float getG() {
        return g;
    }

    public String getName() {
        return name;
    }

    public Integer getDistanceToStratosphere() {
        return distanceToStratosphere;
    }

    public void setG(Float g) {
        this.g = g;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistanceToStratosphere(Integer distanceToStratosphere) {
        this.distanceToStratosphere = distanceToStratosphere;
    }
}
