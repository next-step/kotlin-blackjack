package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardSuitTest {
    @ParameterizedTest
    @EnumSource(CardSuit::class)
    fun `카드 슈트는 클로버, 스페이드, 다이아몬드, 하트로 이루어져 있다`(suit: CardSuit) {
        val expectedSuitTexts = listOf(CardSuit.CLOVER, CardSuit.SPADE, CardSuit.DIAMOND, CardSuit.HEART)

        assertThat(suit).isIn(expectedSuitTexts)
    }
}
