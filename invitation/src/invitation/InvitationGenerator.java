package invitation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvitationGenerator {

    public static List<Person> generateInvitationList(List<Person> population,
        List<Acquaintance> whoKnowsWhom, int minPermittedAdjacency)
    {
        // build adjacency graph
        Map<Person, List<Person>> adjacencyGraph = new HashMap<Person, List<Person>>();
        for (Acquaintance relation : whoKnowsWhom) {
            Set<Person> edges = relation.getMembers();
            for (Person person : edges) {
                Set<Person> members = relation.getMembers();
                members.remove(person);
                if (adjacencyGraph.containsKey(person)) {
                    List<Person> neighbours = adjacencyGraph.get(person);
                    for (Person acquaintance : members) {
                        if (!neighbours.contains(acquaintance)) {
                            neighbours.add(acquaintance);
                        }
                    }
                    adjacencyGraph.put(person, neighbours);
                } else {
                    List<Person> neighbours = new ArrayList<Person>();
                    for (Person acquaintance : members) {
                        neighbours.add(acquaintance);
                    }
                    adjacencyGraph.put(person, neighbours);
                }
            }
        }

        // trim graph
        for (Map.Entry<Person, List<Person>> entry : adjacencyGraph.entrySet()) {
            Person person = entry.getKey();
            List<Person> acquaintances = entry.getValue();
            if (acquaintances.size() < minPermittedAdjacency) {
                adjacencyGraph.remove(person);
                for (Person friendLoser : acquaintances) {
                    if (adjacencyGraph.containsKey(friendLoser)) {
                        List<Person> updatedAcq = adjacencyGraph.get(friendLoser);
                        updatedAcq.remove(person);
                        adjacencyGraph.put(friendLoser, updatedAcq);
                    }
                }
            }
        }
        return new ArrayList<Person>(adjacencyGraph.keySet());
    }

}
