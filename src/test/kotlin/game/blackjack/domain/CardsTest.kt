package game.blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 컬렉션")
internal class CardsTest {

    @Test
    fun `빈 생성자 초기 점수는 0점`() {
        val cards = Cards()
        Assertions.assertThat(cards.score()).isEqualTo(0)
    }

    @Test
    fun `세컨더리 생성자로 생성시 점수`() {
        val cards = Cards(mutableListOf(Card(Suit.SPADE, Denomination.KING)))
        Assertions.assertThat(cards.score()).isEqualTo(10)
    }

    @Test
    fun `버스트가 아닌 경우`() {
        val cards = Cards(mutableListOf(Card(Suit.SPADE, Denomination.KING)))
        Assertions.assertThat(cards.isBust()).isFalse
    }

    @Test
    fun `버스트인 경우`() {
        val cards = Cards(mutableListOf(Card(Suit.SPADE, Denomination.KING)))
        cards.add(Card(Suit.SPADE, Denomination.TEN))
        cards.add(Card(Suit.SPADE, Denomination.JACK))
        Assertions.assertThat(cards.isBust()).isTrue
    }
}
