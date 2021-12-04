package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CardsTest {

    @Test
    fun `카드 묶음을 생성할 수 있다`() {
        val card1 = Card(Suit.CLUB, Denomination.TWO)
        val card2 = Card(Suit.HEART, Denomination.SEVEN)
        val cards = listOf(card1, card2)

        assertThat(Cards.from(cards)).isNotNull
    }

    @Test
    fun `중복된 카드가 존재하면 예외를 던진다`() {
        val card1 = Card(Suit.CLUB, Denomination.TWO)
        val card2 = Card(Suit.CLUB, Denomination.TWO)

        val cards = listOf(card1, card2)

        assertThrows<IllegalArgumentException> { Cards.from(cards) }
    }

    @Test
    fun `Ace의 갯수가 있다면 true를 리턴한다`() {
        val card1 = Card(Suit.HEART, Denomination.ACE)
        val card2 = Card(Suit.CLUB, Denomination.ACE)
        val cards = Cards.from(listOf(card1, card2))

        assertThat(cards.hasAce()).isTrue
    }

    @Test
    fun `포인트 최대 총 포인트 합을 리턴한다`() {
        val card1 = Card(Suit.HEART, Denomination.QUEEN)
        val card2 = Card(Suit.CLUB, Denomination.JACK)
        val card3 = Card(Suit.CLUB, Denomination.SEVEN)
        val cards = Cards.from(listOf(card1, card2, card3))

        assertThat(cards.getHighestPoint()).isEqualTo(27)
    }

    @Test
    fun `ACE가 있을때 포인트 최대 총 포인트 합을 리턴한다`() {
        val card1 = Card(Suit.HEART, Denomination.ACE)
        val card2 = Card(Suit.CLUB, Denomination.THREE)
        val card3 = Card(Suit.DIAMOND, Denomination.ACE)
        val cards = Cards.from(listOf(card1, card2, card3))

        assertThat(cards.getHighestPoint()).isEqualTo(15)
    }

    @Test
    fun `초기 카드가 2장이면서 합이 21이면 블랙잭이다`() {
        val card1 = Card(Suit.HEART, Denomination.ACE)
        val card2 = Card(Suit.CLUB, Denomination.KING)
        val cards = Cards.from(listOf(card1, card2))

        assertThat(cards.isBlackJack()).isTrue
    }
}
