package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun CardTest(){
        val card = Card(CardShape.HEART, CardSymbol.KING)
        assertThat(card.show()).isEqualTo("13HEART")
    }
}
