package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BatTest {
    @Test
    internal fun `배팅금액이 있다`() {
        assertThat(Bat(CardPlayer.Player("pobi"), 10_000)).isEqualTo(Bat(CardPlayer.Player("pobi"), 10_000))
    }

    data class Bat(private val player: CardPlayer, private val money: Int)
}
