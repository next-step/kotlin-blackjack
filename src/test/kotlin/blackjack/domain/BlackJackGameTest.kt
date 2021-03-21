package blackjack.domain

import blackjack.domain.player.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @Test
    fun startGame() {
        val blackJackGame = BlackJackGame()

        val customerA = Customer("a")
        val customerB = Customer("b")
        val customers = listOf<Player>(customerA, customerB)
        blackJackGame.startGame(customers)

        assertThat(customerA.cards.size).isEqualTo(2)
        assertThat(customerB.cards.size).isEqualTo(2)
    }

    @Test
    fun `hit`() {
        val blackJackGame = BlackJackGame()
        val playerA = Customer("a")
        playerA.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TEN))

        blackJackGame.hit(playerA)

        assertThat(playerA.cards.size).isEqualTo(2)
    }

    @Test
    fun `카드가 21점 미만 일 경우만 hit 할수있다`() {
        val blackJackGame = BlackJackGame()
        val playerA = Customer("a")
        playerA.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TEN))
        playerA.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.NINE))
        playerA.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TWO))

        blackJackGame.hit(playerA)

        assertThat(playerA.cards.size).isEqualTo(3)
    }
}
