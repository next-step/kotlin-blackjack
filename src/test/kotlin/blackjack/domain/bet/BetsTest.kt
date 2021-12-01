package blackjack.domain.bet

import blackjack.domain.player.name.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("배팅들(Bets)")
internal class BetsTest {

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["woo:0", "ji:1000", "json:10000"], delimiter = ':')
    fun `이름과 돈으로 배팅을 만든다`(nameString: String, moneyInt: Int) {
        val name = Name(nameString)
        val money = Money(moneyInt)
        val bets = Bets.of(mapOf(name to money))

        assertAll(
            { assertThat(bets).isNotNull },
            { assertThat(bets).isExactlyInstanceOf(Bets::class.java) },
        )
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["woo:0", "ji:1000", "json:10000"], delimiter = ':')
    fun `이름으로 배팅 금액을 찾는다`(nameString: String, moneyInt: Int) {
        val name = Name(nameString)
        val money = Money(moneyInt)
        val bets = Bets.of(mapOf(name to money))

        assertThat(bets.betMoney(name)).isEqualTo(money)
    }
}
