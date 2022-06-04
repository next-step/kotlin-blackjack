package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `덱 생성 테스트`() {
        val deck = Deck(Card.createDeck())
        assertThat(deck.count()).isEqualTo(52)
    }

    @Test
    fun `덱 카드 뽑기 테스트`() {
        val deck = Deck(Card.createDeck())
        assertThat(deck.draw()).isInstanceOf(Card::class.java)
    }
}