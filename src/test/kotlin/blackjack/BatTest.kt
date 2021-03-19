package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BatTest {
    @Test
    internal fun `배팅금액이 있다`() {
        assertThat(Bat(CardPlayer.Player("pobi"), 10_000)).isEqualTo(Bat(CardPlayer.Player("pobi"), 10_000))
    }

    @Test
    internal fun `승패에 따라 돈이 오간다`() {
        val pobi = Bat(CardPlayer.Player("pobi"), 10_000)
        val dealer = Bat(CardPlayer.Dealer())

        pobi beats dealer

        assertThat(pobi.money).isEqualTo(20_000)
        assertThat(dealer.money).isEqualTo(-10_000)
    }
}
