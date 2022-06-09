package blackJackTest.card

import domain.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드 정보 테스트`(){
        val card = Card(Card.Symbol.KING,Card.Suit.SPADE)
        assertThat(card.toString()).isEqualTo("K스페이드")
    }

    @Test
    fun `카드 점수 테스트`(){
        val card = Card(Card.Symbol.KING,Card.Suit.SPADE)
        assertThat(card.symbol.score).isEqualTo(10)
    }

    @Test
    fun `Ace 카드 테스트`(){
        val card = Card(Card.Symbol.ACE,Card.Suit.SPADE)
        assertThat(card.symbol.isAce()).isTrue()
    }
}