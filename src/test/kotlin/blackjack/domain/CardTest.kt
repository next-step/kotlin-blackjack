package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `같은 카드 2번 드로우 하면 false 값이 출력`() {
        val firstDrawCard = CardDeck.drawCard()
        val secondDrawResult = CardDeck.CARDS[firstDrawCard]

        assertThat(secondDrawResult).isFalse()
    }

    @Test
    fun `카드 뽑기`() {
        val card = CardDeck.drawCard()
        println(card.toString())
    }
}
