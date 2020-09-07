package blackjack

import blackjack.model.BlackJack
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `블랙잭 첫번째 턴 카드 뽑기`() {
        val blackJack = BlackJack(Players(listOf(Player("a"), Player("b"))), Dealer())
        val a = blackJack.players[1]
        assertThat(a.score()).isEqualTo(0)
        blackJack.firstTurn()
        assertThat(a.score() > 0).isEqualTo(true)
    }

    @Test
    fun `블랙잭 레이스`() {
        val blackJack = BlackJack(Players(listOf(Player("a"))), Dealer())
        val a = blackJack.players[0]
        assertThat(a.score()).isEqualTo(0)
        blackJack.race(a)
        assertThat(a.score()).isNotEqualTo(0)
    }
}
