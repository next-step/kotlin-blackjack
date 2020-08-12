package blackjack

import blackjack.model.BlackJack
import blackjack.model.Player
import blackjack.model.WIN_SCORE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {

    @Test
    fun `블랙잭 첫번째 턴`() {
        val blackJack = BlackJack(listOf(Player("a"), Player("b")))
        val a = blackJack.players.first()
        val b = blackJack.players.last()
        assertThat(a.cards.isEmpty()).isEqualTo(true)
        assertThat(b.cards.isEmpty()).isEqualTo(true)
        blackJack.firstTurn()
        assertThat(a.cards.size == 2).isEqualTo(true)
        assertThat(a.cards.isEmpty()).isEqualTo(false)
        assertThat(b.cards.size == 2).isEqualTo(true)
        assertThat(b.cards.isEmpty()).isEqualTo(false)
    }

    @Test
    fun `블랙잭 레이스`() {
        val blackJack = BlackJack(listOf(Player("a"), Player("b")))
        val a = blackJack.players.first()
        assertThat(a.cards.isEmpty()).isEqualTo(true)
        blackJack.race(a)
        assertThat(a.cards.isEmpty()).isEqualTo(false)
    }

    @Test
    fun `블랙잭 점수 테스트`() {
        val blackJack = BlackJack(listOf(Player("a"), Player("b")))
        val a = blackJack.players.first()
        blackJack.firstTurn()
        val score = a.countingPoint()
        blackJack.race(a)
        val lastCard = a.cards.last()
        val sum = score + lastCard.value.value1
        assertThat(sum == WIN_SCORE || sum == a.countingPoint()).isEqualTo(true)
    }
}
