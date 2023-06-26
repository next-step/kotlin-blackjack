package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun initiateCardListTest() {
        val cards = Cards()
        assertThat(cards.getCards().count()).isEqualTo(52)
    }

    @Test
    fun takeCardTest(){
        val cards = Cards()
        cards.takeCards(2)
        assertThat(cards.getCards().count()).isEqualTo(50)
    }
}
