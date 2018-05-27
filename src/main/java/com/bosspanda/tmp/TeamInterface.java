package com.bosspanda.tmp;

public interface TeamInterface {
	 int size();
	 Member getMember(int i);
	Member getMember(String name);
	 void addMember(Member member);
	 int numberOfUniqueExpertises();
	 String diverseExpertise();
	
	
}
