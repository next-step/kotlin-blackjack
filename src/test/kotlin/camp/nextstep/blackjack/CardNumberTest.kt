package camp.nextstep.blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardNumberTest {

    @DisplayName("카드 숫자는 모두 13개이다.")
    @Test
    fun countOfCardNumberTest() {
        assertThat(CardNumber.CARD_NUMBERS).isEqualTo(13)
    }

    @DisplayName("카드 숫자는 에이스, 2~10, 잭, 퀸, 킹 중 하나이다.")
    @ParameterizedTest(name = "카드 숫자 {0} 테스트")
    @CsvSource(
        delimiter = ',',
        value = [
            "ACE",
            "TWO", "THREE", "FOUR", "FIVE",
            "SIX", "SEVEN", "EIGHT", "NINE",
            "TEN", "JACK", "QUEEN", "KING",
        ]
    )
    fun cardNumberTwoToTenTest(number: String) {
        assertThat(CardNumber.valueOf(number)).isNotNull
    }
}
