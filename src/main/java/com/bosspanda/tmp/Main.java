package com.bosspanda.tmp;

public class Main {
	public static void main(String[] args) {

		//Hier könnt ihr mit euren Teams herumspielen.
		//Für die Bewertung ist diese Datei nicht relevant.
		Team team = new Team();
		team.addMember(new Member("Tom", Member.Expertise.BUSINESS));
		team.addMember(new Member("Ralf",  Member.Expertise.COMPUTERSCIENCE));
		team.addMember(new Member("Klaus",  Member.Expertise.COMPUTERSCIENCE));
		team.addMember(new Member("Karl",  Member.Expertise.HUMANITIES));
		team.addMember(new Member("Klaus",  Member.Expertise.BUSINESS));

		System.out.println(team);
	}
}
