package blackJackTest.card

import domain.card.Card
import domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드리스트 정보 테스트`(){
        val spadeCard = Card(Card.Symbol.TWO,Card.Suit.SPADE)
        val heartCard = Card(Card.Symbol.THREE,Card.Suit.HEART)
        val cards = Cards(listOf(spadeCard,heartCard))

        assertThat(cards.toString()).isEqualTo("2스페이드 3하트")
    }

    @Test
    fun `카드리스트 드로우 테스트`(){
        val spadeCard = Card(Card.Symbol.TWO,Card.Suit.SPADE)
        val heartCard = Card(Card.Symbol.THREE,Card.Suit.HEART)
        val cards = Cards(listOf(spadeCard,heartCard))

        assertThat(cards.draw()).isEqualTo(spadeCard)
    }

    @Test
    fun `카드리스트 점수 테스트`(){
        val spadeCard = Card(Card.Symbol.TWO,Card.Suit.SPADE)
        val heartCard = Card(Card.Symbol.THREE,Card.Suit.HEART)
        val cards = Cards(listOf(spadeCard,heartCard))

        assertThat(cards.score()).isEqualTo(5)
    }

    @Test
    fun `카드리스트 점수 테스트 - ACE가 11점을 넘은경우`(){
        val spadeCard = Card(Card.Symbol.ACE,Card.Suit.SPADE)
        val heartCard = Card(Card.Symbol.KING,Card.Suit.HEART)
        val clubCard = Card(Card.Symbol.QUEEN,Card.Suit.CLUB)
        val cards = Cards(listOf(spadeCard,heartCard,clubCard))

        assertThat(cards.score()).isEqualTo(21)
    }
}