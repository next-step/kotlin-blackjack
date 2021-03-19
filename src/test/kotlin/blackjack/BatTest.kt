package blackjack

import org.junit.jupiter.api.Test

class BatTest {
    @Test
    internal fun `배팅금액이 있다`() {
        assertThat(Bat(Player("pobi"), 10_000)).isEqualTo(Bat(Player("pobi"), 10_000))
    }
}
