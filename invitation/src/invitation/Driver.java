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
    private static final int MIN_PERMITTED_ADJACENCY = 5;

    public static void main(String[] args) {
        try {
            List<Person> population = generateMockPopulation(MAX_POPULATION_LIMIT);
            List<Acquaintance> whoKnowsWhom = generateMockAcquaintanceList(population);
            List<Person> invitees =
                InvitationGenerator.generateInvitationList(population, whoKnowsWhom,
                    MIN_PERMITTED_ADJACENCY);
            for (Person person : invitees) {
                System.out.println(person.getName());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private static List<Person> generateMockPopulation(int populationLimit)
        throws Throwable
    {
        List<Person> population = new ArrayList<Person>();
        for (int i = 0; i < populationLimit; i++) {
            Person person = new Person(UUID.randomUUID().toString());
            population.add(person);
        }
        return population;
    }

    private static List<Acquaintance> generateMockAcquaintanceList(List<Person> populationList)
        throws Throwable
    {
        Set<Acquaintance> acquaintanceSet = new HashSet<Acquaintance>();
        Random random = new Random();
        for (Person person : populationList) {
            int numAcquaintances = random.nextInt(MAX_ACQUAINTANCE_LIMIT);
            for (int i = 0; i < numAcquaintances; i++) {
                int randomPersonIndex = random.nextInt(MAX_POPULATION_LIMIT);
                Person otherPerson = populationList.get(randomPersonIndex);
                if (person.equals(otherPerson)) {
                    i--;
                } else {
                    Acquaintance acquaintance = new Acquaintance(
                        person, otherPerson);
                    acquaintanceSet.add(acquaintance);
                }
            }
        }
        return new ArrayList<Acquaintance>(acquaintanceSet);
    }

}
