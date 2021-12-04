package blackjack.domain.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CreditTest {

    @ParameterizedTest
    @ValueSource(ints = [-100, 0, 1000])
    fun `금액을 만들 수 있다`(value: Int) {
        assertThat(Credit.from(value)).isNotNull
    }

    @Test
    fun `금액을 감소하면 줄어든 금액을 리턴한다`() {
        val givenCredit = Credit.from(1000)

        val actual = givenCredit.minus(Credit.from(500))

        assertThat(actual).isEqualTo(Credit.from(500))
    }

    @Test
    fun `블랙잭 금액을 받으면 자신의 1배 반을 리턴한다`() {
        val givenCredit = Credit.from(1000)

        val actual = givenCredit.receiveBlackJackCredit()

        assertThat(actual).isEqualTo(Credit.from(1500))
    }

    @Test
    fun `금액 받기시에 주어진 금액만큼 추가되어 리턴한다`() {
        val givenCredit = Credit.from(1000)

        val actual = givenCredit.receiveCredit()

        assertThat(actual).isEqualTo(Credit.from(2000))
    }

    @Test
    fun `주어진 금액의 블랙잭에 해당하는 금액만큼 차감하여 리턴한다`() {
        val givenCredit = Credit.from(5000)
        val blackJackCredit = Credit.from(1000)

        val actual = givenCredit.subtractBlackJack(blackJackCredit)

        assertThat(actual).isEqualTo(Credit.from(3500))
    }
}
