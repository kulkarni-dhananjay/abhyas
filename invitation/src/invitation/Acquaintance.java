package invitation;

import java.util.HashSet;
import java.util.Set;

public class Acquaintance {

    private static final int ACQUAINTANCE_PARTICIPANTS_LIMIT = 2;
    private Set<Person> members = new HashSet<Person>(ACQUAINTANCE_PARTICIPANTS_LIMIT);

    public Acquaintance(Person leftPerson, Person rightPerson)
        throws Throwable
    {
        if (leftPerson.equals(rightPerson)) {
            throw new Exception("Same person cannot be acquaintances");
        }
        if (leftPerson == null || rightPerson == null) {
            throw new Exception("Incorrect initialization of Person");
        }
        this.members.add(leftPerson);
        this.members.add(rightPerson);
    }

    public Set<Person> getMembers() {
        return members;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((members == null) ? 0 : members.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Acquaintance other = (Acquaintance) obj;
        if (members == null) {
            if (other.members != null)
                return false;
        } else if (!members.equals(other.members))
            return false;
        return true;
    }

}
