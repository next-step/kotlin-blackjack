package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `카드 무늬, 점수 타입을 가진다`() {
        val card = Card.of(CardSymbol.CLOVER, CardNumber.ACE)

        assertThat(card.symbol).isEqualTo(CardSymbol.CLOVER)
        assertThat(card.number).isEqualTo(CardNumber.ACE)
    }

    @Test
    fun `카드는 점수를 계산 한다`() {
        val card = Card.of(CardSymbol.CLOVER, CardNumber.ACE)

        val grades: List<Int> = card.scores()
        assertThat(grades).containsAll(listOf(1, 11))
    }
}
