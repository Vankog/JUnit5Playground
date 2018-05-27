package com.bosspanda.tmp;

import org.jetbrains.annotations.NotNull;

public class Member implements MemberInterface {
	
	String name;
	@NotNull Expertise expertise;
	
	public enum Expertise {
	    COMPUTERSCIENCE,
	    BUSINESS,
	    HUMANITIES,
	    MEDIA,
	    LIFESCIENCES,
	    MATH;

	    public static int size() {
	    	return values().length;
	    }
	}
	
	public Member(String name, @NotNull Expertise expertise) {
		this.name = name;
		this.expertise = expertise;
	}

	@Override
	public boolean equals(Object o) {
		// Two members are equal if they have the same name
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Member member = (Member) o;

		return name != null ? name.equals(member.name) : member.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	@Override
	public String toString() {
		return this.name + ": " + this.expertise.name() + "\n";
	}
}
