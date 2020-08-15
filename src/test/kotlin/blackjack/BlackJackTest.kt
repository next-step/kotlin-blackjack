package blackjack

import blackjack.model.BlackJack
import blackjack.model.Player
import blackjack.model.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {

    @Test
    fun `블랙잭 첫번째 턴 카드 뽑기`() {
        val blackJack = BlackJack(Players(listOf(Player("a"), Player("b"))))
        val a = blackJack.players.findPlayer(0)
        val b = blackJack.players.findPlayer(1)
        assertThat(a.cards.score() == 0).isEqualTo(true)
        assertThat(b.cards.score() == 0).isEqualTo(true)
        blackJack.firstTurn()
        assertThat(a.cards.score() != 0).isEqualTo(true)
        assertThat(b.cards.score() != 0).isEqualTo(true)
    }

    @Test
    fun `블랙잭 레이스`() {
        val blackJack = BlackJack(Players(listOf(Player("a"), Player("b"))))
        val a = blackJack.players.findPlayer(0)
        assertThat(a.cards.score() == 0).isEqualTo(true)
        blackJack.race(a)
        assertThat(a.cards.score() != 0).isEqualTo(true)
    }

    @Test
    fun `블랙잭 점수 테스트`() {
        val blackJack = BlackJack(Players(listOf(Player("a"), Player("b"))))
        val a = blackJack.players.findPlayer(0)
        blackJack.firstTurn()
        val score = a.score()
        blackJack.race(a)
        assertThat(a.score() > score).isEqualTo(true)
    }
}
