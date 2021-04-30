package blackjack.domain

import blackjack.fixture.PlayFixture.TEST_NAME
import blackjack.view.CardSuite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
            setOf(
                Card(CardSuite.DIAMOND, CardNumber.ACE),
                Card(CardSuite.HEART, CardNumber.SIX),
                Card(CardSuite.SPADE, CardNumber.EIGHT)
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
            setOf(
                Card(CardSuite.DIAMOND, CardNumber.TWO),
                Card(CardSuite.DIAMOND, CardNumber.THREE),
                Card(CardSuite.DIAMOND, CardNumber.SIX)
            )
        )

        val game = Game(Names(TEST_NAME), cards)
        val firstPlayer = game.players.first()

        game.draw(firstPlayer)

        assertThrows<IllegalStateException> {
            game.draw(firstPlayer)
        }
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야한다`() {
        val cards = GameCards(
            setOf(
                Card(CardSuite.DIAMOND, CardNumber.TWO),
                Card(CardSuite.DIAMOND, CardNumber.THREE)
            )
        )

        val game = Game(Names(TEST_NAME), cards)
        val dealer = game.players.first()

        assertThat(dealer.cards).hasSize(3)
    }
}
