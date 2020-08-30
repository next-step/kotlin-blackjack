package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class SuitTest {

    @ParameterizedTest
    @EnumSource(value = Suit::class)
    fun `모양 이름`(suit: Suit) {
        // given
        val expected: List<String> = listOf("하트", "클로버", "스페이드", "다이아몬드")

        // when
        val actual = Suit.values().map(Suit::shapeName)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
