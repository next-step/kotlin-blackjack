package blackjack.view.output.converter

import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class SuitConverterTest {
    @ParameterizedTest
    @EnumSource(Suit::class)
    fun `SuitConverter는 Suit를 출력을 위한 문자열로 변환해 반환한다`(input: Suit) {
        val expected = when (input) {
            Suit.SPADES -> "스페이드"
            Suit.HEARTS -> "하트"
            Suit.DIAMONDS -> "다이아몬드"
            Suit.CLUBS -> "클로버"
        }

        assertThat(SuitConverter.convert(input)).isEqualTo(expected)
    }
}
