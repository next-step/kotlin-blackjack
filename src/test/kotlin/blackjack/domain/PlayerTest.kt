package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PlayerTest {

    @Test
    fun `플레이어는 이름과 2장의 카드를 처음에 가진다`() {
        val deck = listOf(Card.diamond(Number.EIGHT), Card.heart(Number.TEN)).iterator()
        val player = Player("pobi", deck)

        assertThat(player.name).isEqualTo("pobi")
        assertThat(player.sumOfCards()).isEqualTo(18)
    }

    @Test
    fun `플레이어 카드를 획득할 수 있다`() {
        val deck = listOf(
            Card.diamond(Number.TWO), Card.heart(Number.THREE), Card.spade(Number.ACE)
        ).iterator()
        val player = Player("pobi", deck)

        player.obtain()

        assertThat(player.sumOfCards()).isEqualTo(16)
    }

    @Test
    fun `21 이상이면 플레이어는 카드를 획득할 수 없다`() {
        val deck = listOf(
            Card.diamond(Number.ACE), Card.heart(Number.JACK), Card.spade(Number.ACE)
        ).iterator()
        val player = Player("pobi", deck)

        assertThrows<IllegalArgumentException> {
            player.obtain()
        }
        assertThat(player.isObtainable()).isFalse
    }

    @Test
    fun `21 미만이면 플레이어는 카드를 획득할 수 있다`() {
        val deck = listOf(Card.diamond(Number.QUEEN), Card.heart(Number.JACK)).iterator()
        val player = Player("pobi", deck)

        assertThat(player.isObtainable()).isTrue()
    }
}
