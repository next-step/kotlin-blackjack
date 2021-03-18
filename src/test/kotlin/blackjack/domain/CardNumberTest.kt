package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardNumberTest {

    companion object {
        @JvmStatic
        fun source(): Stream<Arguments> {
            return Stream.of(
                arguments(listOf(CardNumber.ACE, CardNumber.THREE), 14),
                arguments(listOf(CardNumber.ACE, CardNumber.ACE, CardNumber.THREE), 15),
                arguments(listOf(CardNumber.ACE, CardNumber.ACE, CardNumber.ACE), 13)
            )
        }
    }

    @Test
    fun `카드번호 스코어를 계산한다`() {
        val actual = CardNumber.score(listOf(CardNumber.TWO, CardNumber.THREE))
        assertThat(actual).isEqualTo(5)
    }

    @MethodSource("source")
    @ParameterizedTest
    fun `ACE는 점수는 1 또는 11중 최대 21점 이하로 스코어 계산한다`(cardNumbers: List<CardNumber>, expect: Int) {
        val actual = CardNumber.score(cardNumbers)
        assertThat(actual).isEqualTo(expect)
    }
}
