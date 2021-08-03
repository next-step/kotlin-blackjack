package blackjack.domain

import blackjack.fixture.PlayFixture.TEST_NAME
import blackjack.fixture.PlayFixture.TEST_SECOND_NAME
import blackjack.view.CardSuite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {

    @Test
    fun 점수륿_비교하여_우승자를_찾는다() {
        val firstPlayer = Player(TEST_NAME, Card(CardSuite.HEART, CardNumber.ACE), Card(CardSuite.SPADE, CardNumber.JACK))
        val secondPlayer = Player(TEST_SECOND_NAME, Card(CardSuite.DIAMOND, CardNumber.TEN), Card(CardSuite.SPADE, CardNumber.FOUR))

        val participants = Participants(setOf(firstPlayer, secondPlayer))

        val winner = participants.findWinnerScore()

        assertThat(winner).isEqualTo(firstPlayer)
    }

    @Test
    fun 우승_점수를_가지는경우_우승자로_만든다() {
        val firstPlayer = Player(TEST_NAME, Card(CardSuite.HEART, CardNumber.ACE), Card(CardSuite.SPADE, CardNumber.JACK))
        val secondPlayer = Player(TEST_SECOND_NAME, Card(CardSuite.DIAMOND, CardNumber.TEN), Card(CardSuite.SPADE, CardNumber.FOUR))

        val participants = Participants(setOf(firstPlayer, secondPlayer))

        participants.makeWinners(secondPlayer)

        assertThat(secondPlayer.result).isEqualTo(States.WIN)
    }
}
