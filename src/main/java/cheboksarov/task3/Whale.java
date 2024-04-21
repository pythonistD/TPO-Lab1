package cheboksarov.task3;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Whale {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private WhaleType whaleType;
    private final List<String> thoughtList;
    @Getter
    private final Integer thinkRate;
    @Getter
    private Integer height;
    @Getter
    @Setter
    private Integer health;
    private Integer durability;
    @Getter
    private WhaleStatus status;
    @Getter
    private Integer damageRate;

    public Whale(String name, WhaleType whaleType, Integer thinkRate,
                 Integer height,  Integer durability) throws IllegalArgumentException {
        this.name = name;
        this.whaleType = whaleType;
        this.thoughtList = getFullListOfWhaleThoughts();
        this.thinkRate = thinkRate;
        this.height = height;
        this.health = 100;
        this.status = WhaleStatus.ALIVE;
        if (durability <1 || durability > 3) {
            throw new IllegalArgumentException("Durability must be between 1 and 3");
        }
        this.durability = durability;
        this.damageRate = height/(durability*5) * 10;
    }


    public List<String> whatWhaleThoughtWhenFalling(Integer timeOfFalling) throws IllegalArgumentException{
        if (timeOfFalling < 0) {
            throw new IllegalArgumentException("Time of falling must be positive");
        }
       List<String> list = new ArrayList<>();
       for(int i = 0; i<timeOfFalling; i += thinkRate){
           list.add(getCurrentWhaleThought(i));
       }
       return list;
    }

    public String getCurrentWhaleThought(Integer curTime){
        int t = curTime /thinkRate;
        if (t >= thoughtList.size()){
            return "Aaaaaaaaaa...";
        }
       return this.thoughtList.get(t);
    }

    public Integer decHealth(Integer val) throws Exception {
        if (health <= 0){
            throw new Exception("Whale is dead ((");
        }
        if(val < 0){
            throw new IllegalArgumentException("Damage must be positive");
        }
        health -= val;
        if (!isAlive()){
            status = WhaleStatus.DEAD;
        }
        return health;
    }

    public Boolean isAlive(){
        return health > 0;
    }


    public List<String> getFullListOfWhaleThoughts(){
        return new ArrayList<String>(Arrays.asList(
                "Ах!.. что происходит?",
                "Э-э, простите, кто я?",
                "Что я здесь делаю? Каково мое назначение в жизни?",
                "А что я имею в виду, спрашивая, кто я?",
                "Успокойся, приди в себя... о, это интересное ощущение, что это такое?",
                "Это вроде... посасывания, дрожи у меня в... у меня в... пожалуй, мне нужно \nначинать придумывать названия для разных вещей, если я хочу чего-то",
                "достигнуть в том, что я для удобства назову миром, поэтому, скажем так: у \n меня в желудке.\nОтлично. Ого, крепчает",
                "А что это за свистящий звук у меня в том, что я буду называть ушами?",
                "Наверное, я назову это... ветер! По-моему, неплохое название.",
                "Может быть, я найду какое-нибудь получше потом, когда выясню,\nзачем он нужен.",
                "Наверное, он -- очень важная вещь, потому что его так много.\nОп, а это что за штука?",
                "Это... назовем это хвост, да, хвост. О, да я могу\nЗдорово им бить! Ух ты, ух ты! Здорово!",
                "Правда, от него не видно никакого\nтолка, но попозже я выясню, для чего он.",
                "Ну что, я составил себе отчетливу картину о природе вещей?Нет.",
                "Ну, ничего, все равно здорово. Столько нужно всего узнать, столько еще будет, просто голова кружится...",
                "это от ветра? Его так много.",
                " Ух, ты! Оба-на! Что это движется ко мне так быстро? Очень, очень быстро.",
                "Такое большое и плоское! Ему нужно очень красивое и звучное имя, например... ля... мля... земля! Точно! Хорошее название.",
                "Такое большое и плоское! Ему нужно очень красивое и звучное имя, например... ля... мля... земля! Точно! Хорошее название.",
                "мы с ней подружимся?"
        ));
    }


    public void setHeight(int i) {
        height = i;
    }
}
