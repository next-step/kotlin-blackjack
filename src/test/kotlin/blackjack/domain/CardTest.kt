package blackjack.domain

import blackjack.domain.Card.Companion.CARDS
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `같은 카드 2번 드로우 하면 false 값이 출력`() {
        val firstDrawCard = Card.drawCard()
        val secondDrawResult = CARDS[firstDrawCard]

        assertThat(secondDrawResult).isFalse()
    }

    @Test
    fun `카드 뽑기`() {
        val card = Card.drawCard()
        println(card.toString())
    }
}
