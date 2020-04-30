public class BotFactory implements AbstractFactory<TimBot>{

    @Override
    public TimBot create(TimBot botType) {
        if("SpressoBot".equalsIgnoreCase(String.valueOf(botType))){
            return new SpressoBot(id, jolts);
        } else if ("BullyBot".equalsIgnoreCase(String.valueOf(botType))) {
            return new BullyBot(id, jolts);

        }
    }
}
