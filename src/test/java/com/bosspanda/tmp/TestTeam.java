package com.bosspanda.tmp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTeam {

	TeamInterface team;

    @BeforeEach
	void setUp() throws Exception {
		team = new Team();
	}

    @Test
    void returns_number_of_unique_expertise_with_no_members() {
        assertEquals(0, team.numberOfUniqueExpertises(), "Number of unique expertise was incorrect.");
    }

    @Test
    void returns_number_of_unique_expertise_with_3_distinct_expertises() {
        team.addMember(new Member("adolf", Member.Expertise.COMPUTERSCIENCE));
        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.HUMANITIES));
        assertEquals(3, team.numberOfUniqueExpertises(), "Number of unique expertise was incorrect.");
    }

    @Test
    void returns_number_of_unique_expertise_with_3_members_and_2_distinct_expertise() {
        team.addMember(new Member("adolf", Member.Expertise.BUSINESS));
        team.addMember(new Member("bertram", Member.Expertise.COMPUTERSCIENCE));
        team.addMember(new Member("charlie", Member.Expertise.BUSINESS));
        assertEquals(2, team.numberOfUniqueExpertises(), "Number of unique expertise was incorrect.");
    }

    /**
     * A team has diverse expertise (GREEN) when:
     * a) the number of unique expertises equals the team size
     * You can get some help here from our helper method:
	 * use testTeamHelper.addASetOfDiverseMembers(team); to fill the team to run your tests here
	 */
	@Test
	void has_diverse_expertise_if_number_of_unique_expertise_equals_team_size(){
        team.addMember(new Member("adolf", Member.Expertise.COMPUTERSCIENCE));
        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.HUMANITIES));
        assertEquals(Team.GREEN, team.diverseExpertise(),
				"Did not return Green status with number of unique expertise equaling team size.");
	}

	/**
	 * A team has diverse expertise (GREEN) when:
	 * c) the number of unique expertises is greater or equal to the number of possible expertises
	 * use testTeamHelper.addASetOfDiverseMembers(team); use testTeamHelper.addASetOfComputerScientists(team);
	 */
	@Test
    void has_diverse_expertise_if_all_possible_expertise_are_assigned(){
        int i= 1;
        for (Member.Expertise expertise : Member.Expertise.values()){
            team.addMember(new Member(expertise.toString() + "_is_my_name", expertise));
            team.addMember(new Member(expertise.toString() + "_is_also_my_name", expertise));
            if (i == Member.Expertise.size()-1){
                assertNotEquals(Team.GREEN, team.diverseExpertise(),
                        "Did return Green status.");
            }
            i++;
        }
        assertEquals(Team.GREEN, team.diverseExpertise(),
                "Did not return Green status. Should be green if all possible expertise are assigned.");
    }

    /**
     * A team has diverse expertise (GREEN) when:
     * b) the number of unique expertises is greater than two thirds of the team size [(team size * 2/3.0)]
     * use testTeamHelper.addALargeTwoThirdsSet(team);
     */
    @Test
    void has_diverse_expertise_if_number_of_unique_expertise_is_two_thirds_the_team_size() {
        // this test only makes sense for at least 4 different expertise:
        assertTrue(Member.Expertise.size() >= 4, "This test cannot perform with too few possible expertise.");

        // exactly 2/3:
        team.addMember(new Member("adolf", Member.Expertise.COMPUTERSCIENCE));
        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.BUSINESS));
        assertNotEquals(Team.GREEN, team.diverseExpertise(),
                "Did return Green status. Unique expertise 2 to team size 3 == exactly 2/3, " +
                        "but must be greater for Green status.");

        // > 2/3:
        team.addMember(new Member("detlev", Member.Expertise.MEDIA));
        assertEquals(Team.GREEN, team.diverseExpertise(),
                "Did not return Green status. Unique expertise 3 to team size 4 == 0,75 -> is >2/3.");

        // < 2/3, but one expertise is left out to prevent rule b) from kicking in:
        team = new Team();
        int i= 1;
        for (Member.Expertise expertise : Member.Expertise.values()){
            team.addMember(new Member(expertise.toString() + "_is_my_name", expertise));
            team.addMember(new Member(expertise.toString() + "_is_also_my_name", expertise));
            if (i >= Member.Expertise.size()-1){
                assertNotEquals(Team.GREEN, team.diverseExpertise(),
                        "Did return Green status. Unique expertise to team size == 0,5 is <2/3, " +
                                "but must be greater for Green status.");
                break;
            }
            i++;
        }
    }

	/**
	 * A team has a sufficiently diverse expertise (YELLOW) when:
	 * d) the number of unique expertises is greater than one third of the team size [(team size / 3.0)]
	 *    and smaller or equal than two thirds of the team size (b))
	 * use testTeamHelper.addALargeOneThirdSet(team);
	 */
	@Test
    void has_sufficient_expertise_if_number_of_unique_expertise_is_one_thirds_the_team_size() {
        // this test only makes sense for at least 4 different expertise:
        assertTrue(Member.Expertise.size() >= 4, "This test cannot perform with too few possible expertise.");

        // < 1/3:
        team.addMember(new Member("adolf", Member.Expertise.BUSINESS));
        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.BUSINESS));
        team.addMember(new Member("detlev", Member.Expertise.BUSINESS));
        assertNotEquals(Team.YELLOW, team.diverseExpertise(),
                "Did return Yellow status. Unique expertise 1 to team size 4 == 0,25 -> is <1/3, " +
                        "but must be greater for Yellow status.");

        // exactly 1/3:
        team = new Team();
        team.addMember(new Member("adolf", Member.Expertise.BUSINESS));
        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.BUSINESS));
        assertNotEquals(Team.YELLOW, team.diverseExpertise(),
                "Did return Yellow status. Unique expertise 1 to team size 3 == exactly 1/3, " +
                        "but must be greater for Yellow status.");

        // > 1/3 && < 2/3:
        team.addMember(new Member("detlev", Member.Expertise.MEDIA));
        assertEquals(Team.YELLOW, team.diverseExpertise(),
                "Did not return Yellow status. Unique expertise 2 to team size 4 == 0,5 -> is >1/3.");

        // exactly 2/3:
        team.addMember(new Member("adolf", Member.Expertise.COMPUTERSCIENCE));
        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.BUSINESS));
        assertEquals(Team.YELLOW, team.diverseExpertise(),
                "Did not return Yellow status. Unique expertise 2 to team size 3 == exactly 2/3");
    }

	/**
	 * A team has an insufficiently diverse expertise (RED) when:
	 * e) the number of unique expertises is smaller than or equal than one third of the team size (d))
	 * use testTeamHelper.addALargeIndiverseSet(team);
	 */
    @Test
    void has_insufficient_expertise_if_number_of_unique_expertise_is_max_one_thirds_the_team_size() {
        // this test only makes sense for at least 4 different expertise:
        assertTrue(Member.Expertise.size() >= 4, "This test cannot perform with too few possible expertise.");

        // exactly 1/3:
        team.addMember(new Member("adolf", Member.Expertise.BUSINESS));
        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.BUSINESS));
        assertEquals(Team.RED, team.diverseExpertise(),
                "Did not return Red status. Unique expertise 1 to team size 3 == exactly 1/3.");

        // < 1/3:
        team.addMember(new Member("detlev", Member.Expertise.BUSINESS));
        assertEquals(Team.RED, team.diverseExpertise(),
                "Did not return Red status. Unique expertise 1 to team size 4 == 0,25 -> is <1/3.");

        // > 1/3:
        team = new Team();
        team.addMember(new Member("adolf", Member.Expertise.BUSINESS));
        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.BUSINESS));
        team.addMember(new Member("detlev", Member.Expertise.MEDIA));
        assertNotEquals(Team.RED, team.diverseExpertise(),
                "Did return Red status. Unique expertise 2 to team size 4 == 0,5 -> is >1/3, " +
                        "but has to be equal or less for Red status.");
    }

    /**
     * Test that members are not duplicated.
         * Try to add a pair of members that count as a duplicate with regards to the equals method and ensure that
         * only the first member is in the team afterwards.
     * You can also use our helper method testTeamHelper.add4MembersAndADuplicate(team); and
         * ensure that you have no duplicates, or just 4 members.
     */
    @Test
    void does_not_add_duplicate_members(){
        Member.Expertise expectedExpertise = Member.Expertise.COMPUTERSCIENCE;
        String duplicateName = "adolf";
        Member adolf_the_first = new Member(duplicateName, expectedExpertise);
        Member adolf_the_second = new Member(duplicateName, Member.Expertise.LIFESCIENCES);

        team.addMember(adolf_the_first);

        team.addMember(new Member("bertram", Member.Expertise.BUSINESS));
        team.addMember(new Member("charlie", Member.Expertise.HUMANITIES));
        team.addMember(new Member("detlef", Member.Expertise.MEDIA));

        team.addMember(adolf_the_second);

        assertAll("Checking for duplicate members:",
                () -> assertEquals(4, team.size(), "Team size is not the right size."),
                () -> assertEquals(expectedExpertise, team.getMember(duplicateName).expertise,
                        "Expertise was changed."),
                () -> assertEquals(adolf_the_first, team.getMember(duplicateName),
                        "Objects did not equal correctly."),
                () -> assertSame(adolf_the_first, team.getMember(duplicateName), "Not the same object.")
        );

    }
}
