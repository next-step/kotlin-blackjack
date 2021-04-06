package blackjack

import blackjack.PlayFixture.TEST_NAME
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.LinkedList

class GameTest {
    @Test
    fun `모든 플레이어가 카드를 받지 않는 상태가 되면 게임이 종료된다`() {
        val names = Names("오길환,상구좌")
        val game = Game(names)

        val firstPlayer = game.players.first()
        val secondPlayer = game.players.last()

        firstPlayer.draw(Card(CardSuite.HEART, CardNumber.SIX))
        firstPlayer.draw(Card(CardSuite.HEART, CardNumber.QUEEN))
        firstPlayer.draw(Card(CardSuite.HEART, CardNumber.KING))
        secondPlayer.stop()

        assertThat(game.state).isEqualTo(GameStates.END)
    }

    @Test
    fun `게임에서는 카드를 한장을 뽑아서 플레이어에게 줄 수 있다`() {
        val cards = GameCards(
            LinkedList(
                mutableListOf(
                    Card(CardSuite.DIAMOND, CardNumber.ACE),
                    Card(CardSuite.HEART, CardNumber.SIX),
                    Card(CardSuite.SPADE, CardNumber.EIGHT)
                )
            )
        )

        val game = Game(Names(TEST_NAME), cards)
        val firstPlayer = game.players.first()

        game.draw(firstPlayer)

        assertThat(firstPlayer.cards).containsExactlyInAnyOrder(
            Card(CardSuite.DIAMOND, CardNumber.ACE),
            Card(CardSuite.HEART, CardNumber.SIX),
            Card(CardSuite.SPADE, CardNumber.EIGHT)
        )
    }

    @Test
    fun `게임에서는 카드를 한장을 뽑아서 플레이어에게 줄 수는 있지만, 못받는 경우는 에러가 발생해야 한다`() {
        val cards = GameCards(
            LinkedList(
                mutableListOf(
                    Card(CardSuite.DIAMOND, CardNumber.TWO),
                    Card(CardSuite.DIAMOND, CardNumber.THREE),
                    Card(CardSuite.DIAMOND, CardNumber.SIX)
                )
            )
        )

        val game = Game(Names(TEST_NAME), cards)
        val firstPlayer = game.players.first()
        // hit
        game.draw(firstPlayer)
        // bust

        assertThrows<IllegalStateException> {
            game.draw(firstPlayer) // bust
        }
    }
}
