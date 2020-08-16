package blackjack

import blackjack.model.BlackJack
import blackjack.model.CardDeck
import blackjack.model.Player
import blackjack.model.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    private val blackJack = BlackJack(Players(listOf(Player("a"), Player("b"))), CardDeck())

    @Test
    fun `블랙잭 첫번째 턴 카드 뽑기`() {
        val a = blackJack.players[0]
        assertThat(a.cards.score() == 0).isEqualTo(true)
        assertThat(a.cards.score() != 0).isEqualTo(true)
    }

    @Test
    fun `블랙잭 레이스`() {
        val b = blackJack.players[1]
        assertThat(b.cards.score() == 0).isEqualTo(true)
        blackJack.race(b)
        assertThat(b.cards.score() != 0).isEqualTo(true)
    }

    @Test
    fun `블랙잭 점수 테스트`() {
        val a = blackJack.players[0]
        val score = a.score()
        blackJack.race(a)
        assertThat(a.score() > score).isEqualTo(true)
    }
}
