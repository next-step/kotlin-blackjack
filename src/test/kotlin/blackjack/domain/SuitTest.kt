package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SuitTest {
    @Test
    fun `Suit는 4가지 종류의 무늬(스페이드, 다이아몬드, 하트, 클로버)를 갖는다`() {
        // given, when
        val suitSet = Suit.getSuitSet()

        // then
        assertThat(suitSet.size).isEqualTo(4)
        assertThat(suitSet["스페이드"]).isEqualTo(Suit.SPADE)
        assertThat(suitSet["다이아몬드"]).isEqualTo(Suit.DIAMOND)
        assertThat(suitSet["하트"]).isEqualTo(Suit.HEART)
        assertThat(suitSet["클로버"]).isEqualTo(Suit.CLUB)
    }
}
