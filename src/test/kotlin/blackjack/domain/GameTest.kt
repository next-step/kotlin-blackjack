package blackjack.domain

import blackjack.fixture.PlayFixture.TEST_NAME
import blackjack.view.CardSuite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameTest {

    @Test
    fun `모든 플레이어가 카드를 받지 않는 상태가 되면 게임이 종료된다`() {
        val cards = PlayerCards(
            setOf(
                Card(CardSuite.DIAMOND, CardNumber.TWO),
                Card(CardSuite.DIAMOND, CardNumber.THREE)
            )
        )

        val firstPlayer = Player("오길환", cards)
        val secondPlayer = Dealer(cards)
        val game = Game(Participants(setOf(firstPlayer)), secondPlayer)


        firstPlayer.draw(Card(CardSuite.HEART, CardNumber.SIX))
        firstPlayer.draw(Card(CardSuite.HEART, CardNumber.QUEEN))
        firstPlayer.draw(Card(CardSuite.HEART, CardNumber.KING))
        secondPlayer.stop()

        assertThat(game.state).isEqualTo(GameStates.END)
    }

    @Test
    fun `게임에서는 카드를 한장을 뽑아서 플레이어에게 줄 수 있다`() {
        val cards = PlayerCards(
            setOf(
                Card(CardSuite.DIAMOND, CardNumber.ACE),
                Card(CardSuite.HEART, CardNumber.SIX),
                Card(CardSuite.SPADE, CardNumber.EIGHT)
            )
        )

        val player = Player(TEST_NAME, Card(CardSuite.CLOVER, CardNumber.FIVE), Card(CardSuite.CLOVER, CardNumber.TWO))

        val game = Game(Participants(setOf(player)), Dealer(cards))
        val firstPlayer = game.participants.first()

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

        val playerCards = PlayerCards(
            setOf(
                Card(CardSuite.CLOVER, CardNumber.FIVE),
                Card(CardSuite.CLOVER, CardNumber.TWO))
        )

        val player = Player(TEST_NAME, playerCards)

        val game = Game(Participants(setOf(player)), Dealer(playerCards), cards)
        val firstPlayer = game.participants.first()

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

        val playerCards = PlayerCards(
            setOf(
                Card(CardSuite.CLOVER, CardNumber.FIVE),
                Card(CardSuite.CLOVER, CardNumber.TWO))
        )

        val player = Player(TEST_NAME, playerCards)

        val game = Game(Participants(setOf(player)), Dealer(playerCards), cards)
        val dealer = game.participants.first()

        assertThat(dealer.cards).hasSize(3)
    }
}
