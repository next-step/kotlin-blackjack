package blackJackTest.card

import domain.card.Card
import domain.card.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드덱 드로우 테스트`(){
        val cardDeck = CardDeck()
        assertThat(cardDeck.draw()).isInstanceOf(Card::class.java)
    }
}