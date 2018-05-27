package com.bosspanda.tmp;

import com.bosspanda.tmp.Member;
import com.bosspanda.tmp.MemberInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class TestMember {
	MemberInterface klaus1;
	MemberInterface klaus2;
	MemberInterface karl;

	@BeforeEach
	void setUp() throws Exception {
		klaus1 = new Member("Klaus", Member.Expertise.BUSINESS);
		klaus2 = new Member("Klaus", Member.Expertise.MATH);
		karl = new Member("Karl", Member.Expertise.BUSINESS);
	}

	@ParameterizedTest
	@EnumSource(Member.Expertise.class)
	void a_member_retains_its_assigned_expertise(Member.Expertise expertise){
		Member testMember = new Member(expertise.toString()+"_is_my_name", expertise);
		assertEquals(expertise, testMember.expertise,
				"Member '" +testMember.name + "' has not the right expertise");
	}

	@Test
	void a_member_must_have_some_expertise(){
		assertThrows(IllegalArgumentException.class,
				() -> new Member("Testosterone", null),
				"Assigning no expertise to a member did not throw an exception.");
	}

	@Test
	void testSameMembers() {
		// ensure that two members with the same name are considered to be equal.
		assertAll("Checking that hashes and equals have to match:",
				() -> assertEquals(klaus1, klaus2, "The same name failed to be the same member."),
				() -> assertEquals(klaus1.hashCode(), klaus2.hashCode(),
						"The same name failed to have the same hash.")
		);
	}

	@Test
	void testDifferentMembers() {
		// ensure that two members with different names are not considered to be equal.
		assertAll("Checking that hashes and equals have to differ:",
				() -> assertNotEquals(klaus1, karl, "Differing names failed to be different members."),
				() -> assertNotEquals(klaus1.hashCode(), karl.hashCode(),
						"Differing names failed to have different hashes.")
		);
	}

}
