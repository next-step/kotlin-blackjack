package blackjack.domain.gamer

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BlackJackGamerTest {

    @Test
    fun `BlackJackGamer는 money를 가진다`() {
        // given
        val gamerName = "name"
        val money = 0
        val blackJackGamer = BlackJackGamer(gamerName, money)

        // then
        Assertions.assertThat(blackJackGamer.money).isEqualTo(money)
    }
}
