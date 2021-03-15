package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @Test
    fun startGame() {
        val blackJackGame = BlackJackGame()

        val playerA = Player("a")
        val playerB = Player("b")
        val players = listOf<Player>(playerA, playerB)
        blackJackGame.startGame(players)

        assertThat(playerA.cards.size).isEqualTo(2)
        assertThat(playerB.cards.size).isEqualTo(2)
    }

    @Test
    fun `hit`() {
        val blackJackGame = BlackJackGame()
        val playerA = Player("a")
        playerA.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TEN))

        blackJackGame.hit(playerA)

        assertThat(playerA.cards.size).isEqualTo(2)
    }

    @Test
    fun `카드가 21점 미만 일 경우만 hit 할수있다`() {
        val blackJackGame = BlackJackGame()
        val playerA = Player("a")
        playerA.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TEN))
        playerA.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.NINE))
        playerA.acceptCard(Card.of(CardSymbol.DIAMONDS, CardNumber.TWO))

        blackJackGame.hit(playerA)

        assertThat(playerA.cards.size).isEqualTo(3)
    }
}
