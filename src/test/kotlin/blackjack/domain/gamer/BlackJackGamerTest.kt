package blackjack.domain.gamer

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BlackJackGamerTest {

    @Test
    fun `BlackJackGamer는 money를 가진다`() {
        // given
        val money = 0
        val blackJackGamer = BlackJackGamer("name", money)

        // then
        Assertions.assertThat(blackJackGamer.money).isEqualTo(money)
    }

    @Test
    fun `외부에서 돈을 입력받아서 가지고 있는 돈에 더한다`() {
        // given
        val money = 0
        val blackJackGamer = BlackJackGamer("name", money)

        // when
        val addMoney = 10000
        blackJackGamer.takeMoney(addMoney)

        // then
        Assertions.assertThat(blackJackGamer.money).isEqualTo(money + addMoney)
    }
}
