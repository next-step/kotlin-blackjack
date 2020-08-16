package blackjack

import blackjack.model.BlackJack
import blackjack.model.CardDeck
import blackjack.model.Player
import blackjack.model.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    private val deck = CardDeck()
    private val shuffledDeck = CardDeck().apply {
        this.shuffle()
    }

    @Test
    fun `블랙잭 첫번째 턴 카드 뽑기`() {
        val blackJack = BlackJack(Players(listOf(Player("a"), Player("b"))), shuffledDeck)
        val a = blackJack.players[0]
        val b = blackJack.players[1]
        assertThat(a.score()).isEqualTo(0)
        assertThat(b.score()).isEqualTo(0)
        blackJack.firstTurn()
        assertThat(a.score() > 0).isEqualTo(true)
        assertThat(b.score() > 0).isEqualTo(true)
    }

    @Test
    fun `블랙잭 레이스`() {
        val blackJack = BlackJack(Players(listOf(Player("a"))), shuffledDeck)
        val a = blackJack.players[0]
        assertThat(a.score() == 0).isEqualTo(true)
        blackJack.race(a)
        assertThat(a.score() != 0).isEqualTo(true)
    }
}
