package blackjack

import blackjack.PlayFixture.FIRST_PLAYER
import blackjack.PlayFixture.PLAYERS
import blackjack.PlayFixture.SECOND_PLAYER
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class GameTest {
    @Test
    fun `모든 플레이어가 카드를 받지 않는 상태가 되면 게임이 종료된다`() {
        val firstPlayer = FIRST_PLAYER
        val secondPlayer = SECOND_PLAYER

        firstPlayer.draw(Card(CardSuite.HEART, CardNumber.SIX))
        secondPlayer.stop()

        val game = Game(Players(setOf(firstPlayer, secondPlayer)))

        assertThat(game.state).isEqualTo(GameStates.END)
    }

    @Test
    fun `게임에서는 카드를 한장을 뽑아서 플레이어에게 줄 수 있다`() {
        val firstPlayer = Player("오길환", Card(CardSuite.HEART, CardNumber.SIX), Card(CardSuite.SPADE, CardNumber.EIGHT))

        val cards = GameCards(
            LinkedList(
                mutableListOf(
                    Card(CardSuite.DIAMOND, CardNumber.ACE),
                    Card(CardSuite.DIAMOND, CardNumber.TWO)
                )
            )
        )

        val game = Game(PLAYERS, cards)

        game.draw(firstPlayer)

        assertThat(firstPlayer.cards).containsExactlyInAnyOrder(
            Card(CardSuite.DIAMOND, CardNumber.ACE),
            Card(CardSuite.HEART, CardNumber.SIX),
            Card(CardSuite.SPADE, CardNumber.EIGHT)
        )
    }

    @Test
    fun `게임에서는 카드를 한장을 뽑아서 플레이어에게 줄 수는 있지만, 못받는 경우는 에러가 발생해야 한다`() {
        val firstPlayer = Player("오길환", Card(CardSuite.HEART, CardNumber.QUEEN), Card(CardSuite.SPADE, CardNumber.KING))
        val cards = GameCards(
            LinkedList(
                mutableListOf(
                    Card(CardSuite.DIAMOND, CardNumber.TWO),
                    Card(CardSuite.DIAMOND, CardNumber.THREE)
                )
            )
        )

        val game = Game(PLAYERS, cards)
        // hit
        game.draw(firstPlayer)
        // bust

        assertThrows<IllegalStateException> {
            game.draw(firstPlayer) // bust
        }
    }
}
