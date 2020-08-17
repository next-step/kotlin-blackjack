package blackjack.model

import blackjack.model.player.Dealer
import blackjack.model.player.Gamer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MoneyTest {
    private lateinit var dealer: Dealer
    private lateinit var gamer: Gamer

    @BeforeEach
    fun beforeTest() {
        dealer = Dealer()
        gamer = Gamer("jejun")
    }

    @DisplayName(value = "금액 betting 후 각 참여자의 Money")
    @Test
    fun betMoney() {
        val betMoney = Money(500)

        dealer.plus(betMoney)
        gamer.minus(betMoney)

        Assertions.assertThat(dealer.money).isEqualTo(Money(500))
        Assertions.assertThat(gamer.money).isEqualTo(Money(-500))
    }
}
