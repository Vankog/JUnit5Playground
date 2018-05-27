package com.bosspanda.tmp;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Team implements TeamInterface {
	
	public static final String RED = "red";
	public static final String YELLOW = "yellow";
	public static final String GREEN = "green";
		
	LinkedHashMap<String, Member> team;
	HashMap<Member.Expertise, Integer> uniqueExpertises;
	
	public Team() {
		team = new LinkedHashMap<>();
		uniqueExpertises = new HashMap<>();
	}

	/**
	 * Return the member at a given position
	 * @param i Position of the desired member
	 * @return desired member
	 */
	public Member getMember(int i){
		int j = 0;
	    for (Member member : team.values()){
		    if (j == i)
		        return member;
	        j++;
        }
        throw new IndexOutOfBoundsException("Member position " + i  + " is not available.");
	}

    /**
     * Return the member at a given position
     * @param name Name of the desired member
     * @return desired member
     */
    public Member getMember(String name){
        return team.get(name);
    }
	
	/**
	 * Determines how many different expertises (n) are available in the team.
	 * Example: COMPUTERSCIENCE, COMPUTERSCIENCE, COMPUTERSCIENCE, BUSINESS
	 * => n = 2
	 * 
	 * @return the number of unique expertises in the team
	 */
	public int numberOfUniqueExpertises() {
		return uniqueExpertises.size();
	}
	
	/**
	 * A team has diverse expertise (GREEN) when:
	 * a) the number of unique expertises equals the team size
	 * b) the number of unique expertises is greater than two thirds of the team size [(team size * 2/3.0)]
	 * c) the number of unique expertises is greater or equal to the number of possible expertises
	 * 
	 * A team has a sufficiently diverse expertise (YELLOW) when:
	 * d) the number of unique expertises is greater than one third of the team size [(team size / 3.0)] 
	 *    and smaller than or equal two thirds of the team size (b))
	 * 
	 * A team has an insufficiently diverse expertise (RED) when:
	 * e) the number of unique expertises is smaller than or equal than one third of the team size (d))
	 * 
	 * @return the state of the team's diversity (GREEN, YELLOW, RED)
	 */
	public String diverseExpertise() {
		if (uniqueExpertises.size() >= Member.Expertise.size() || uniqueExpertises.size() > 2.0/3.0 * team.size())
		    return GREEN;
		else if (uniqueExpertises.size() > 1.0/3.0 * team.size())
		    return YELLOW;
		return RED;
	}

	/**
	 * Only add a member if the member is not in the team yet.
	 * 
	 * @param m the Member to be added
	 */
	public void addMember(Member m) {
		if(team.putIfAbsent(m.name, m) == null) {
            uniqueExpertises.compute(m.expertise,
                    (exp, count) -> (count == null) ? 1 : count++);
		}
	}
	
	@Override
	public String toString() {
		final StringBuilder output = new StringBuilder();
		team.forEach((name, member) -> output.append(member.toString()));
		output.append("uex: ").append(numberOfUniqueExpertises());
		return output.toString();
	}

	@Override
	public int size() {
		return team.size();
	}
	
	private void addExpertise(Member m) {
		uniqueExpertises.put(m.expertise, 1);
	}
	
	private void increaseExpertise(Member m, int count) {
		uniqueExpertises.put(m.expertise, count++);
	}
}
