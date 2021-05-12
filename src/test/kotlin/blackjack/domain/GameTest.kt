package blackjack.domain

import blackjack.fixture.PlayFixture.TEST_NAME
import blackjack.view.CardSuite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameTest {

    @Test
    fun `게임에서는 카드를 한장을 뽑아서 플레이어에게 줄 수 있다`() {
        val cards = PlayerCards(
            setOf(
                Card(CardSuite.DIAMOND, CardNumber.ACE),
                Card(CardSuite.HEART, CardNumber.SIX),
                Card(CardSuite.SPADE, CardNumber.EIGHT)
            )
        )

        val player = Player(TEST_NAME, cards)

        val game = Game(Participants(setOf(player)), Dealer(cards))
        val firstPlayer = game.participants.first()

        game.draw(firstPlayer)

        assertThat(firstPlayer.cards).contains(
            Card(CardSuite.DIAMOND, CardNumber.ACE),
            Card(CardSuite.HEART, CardNumber.SIX),
            Card(CardSuite.SPADE, CardNumber.EIGHT)
        )
    }

    @Test
    fun `게임에서는 카드를 한장을 뽑아서 플레이어에게 줄 수는 있지만, 못받는 경우는 에러가 발생해야 한다`() {
        val playerCards = PlayerCards(
            setOf(
                Card(CardSuite.CLOVER, CardNumber.JACK),
                Card(CardSuite.HEART, CardNumber.JACK))
        )

        val player = Player(TEST_NAME, playerCards)

        val game = Game(Participants(setOf(player)), Dealer(playerCards))
        val firstPlayer = game.participants.first()

        game.draw(firstPlayer)

        assertThrows<IllegalStateException> {
            game.draw(firstPlayer)
        }
    }
}
