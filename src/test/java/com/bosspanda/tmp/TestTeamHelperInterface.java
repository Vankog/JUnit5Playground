package com.bosspanda.tmp;

import com.bosspanda.tmp.TeamInterface;

public interface TestTeamHelperInterface {

	void addASetOfDiverseMembers(TeamInterface teamInterface);

	/**
	 * 3 * 2 / 3 = 2... smallest possible TeamInterface that fulfills the two thirds rule
	 */
	void addASmallTwoThirdsSet(TeamInterface teamInterface);

	/**
	 * 7 * 2 / 3 = 4.666... biggest possible TeamInterface that does not already fulfill condition c)
	 */
	void addALargeTwoThirdsSet(TeamInterface teamInterface);

	/**
	 * 14 / 3 = 4.666... biggest possible TeamInterface that fulfills the one third rule
	 */
	void addALargeOneThirdSet(TeamInterface teamInterface);

	/**
	 * 3 / 3 = 1... smallest possible TeamInterface that fulfills the one third rule
	 */
	void addASmallOneThirdSet(TeamInterface teamInterface);

	void addASmallIndiverseSet(TeamInterface teamInterface);

	void addALargeIndiverseSet(TeamInterface teamInterface);

	void addASetOfComputerScientists(TeamInterface teamInterface);

	void add4MembersAndADuplicate(TeamInterface teamInterface);

	void add3MembersWith2Expertises(TeamInterface teamInterface);

}
