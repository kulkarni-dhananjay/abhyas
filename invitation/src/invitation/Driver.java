package invitation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Driver {

    private static final int MAX_ACQUAINTANCE_LIMIT = 10;
    private static final int MAX_POPULATION_LIMIT = 100;

    public static void main(String[] args) {
        try {
            Set<Person> population = generateMockPopulation(MAX_POPULATION_LIMIT);
            Set<Acquaintance> whoKnowsWhom = generateMockAcquaintanceList(population);
            List<Person> invitees =
                InvitationGenerator.generateInvitationList(population, whoKnowsWhom);
            for (Person person : invitees) {
                System.out.println(person.getName());
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

    }

    private static Set<Person> generateMockPopulation(int populationLimit)
        throws Throwable
    {
        Set<Person> population = new HashSet<Person>();
        for (int i = 0; i < populationLimit; i++) {
            Person person = new Person(UUID.randomUUID().toString());
            population.add(person);
        }
        return population;
    }

    private static Set<Acquaintance> generateMockAcquaintanceList(Set<Person> population)
        throws Throwable
    {
        List<Person> populationList = new ArrayList<Person>(population);
        Set<Acquaintance> acquaintanceSet = new HashSet<Acquaintance>();
        Random random = new Random();
        for (Person person : populationList) {
            int numAcquaintances = random.nextInt(MAX_ACQUAINTANCE_LIMIT);
            for (int i = 0; i < numAcquaintances; i++) {
                int randomPersonIndex = random.nextInt(MAX_POPULATION_LIMIT);
                Acquaintance acquaintance = new Acquaintance(
                    person, populationList.get(randomPersonIndex));
                acquaintanceSet.add(acquaintance);
            }
        }
        return acquaintanceSet;
    }

}
