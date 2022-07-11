package game.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 컬렉션")
internal class CardsTest {

    @Test
    fun `빈 생성자 초기 점수는 0점`() {
        val cards = Cards()
        assertThat(cards.score()).isEqualTo(Score(0))
    }

    @Test
    fun `세컨더리 생성자로 생성시 점수`() {
        val cards = Cards(mutableListOf(Card(Suit.SPADE, Denomination.KING)))
        assertThat(cards.score()).isEqualTo(Score(10))
    }

    @Test
    fun `버스트가 아닌 경우`() {
        val cards = Cards(mutableListOf(Card(Suit.SPADE, Denomination.KING)))
        assertThat(cards.isBust()).isFalse
    }

    @Test
    fun `버스트인 경우`() {
        val cards = Cards(mutableListOf(Card(Suit.SPADE, Denomination.KING)))
        cards.add(Card(Suit.SPADE, Denomination.TEN))
        cards.add(Card(Suit.SPADE, Denomination.JACK))
        assertThat(cards.isBust()).isTrue
    }

    @Test
    fun `단순 합 계산`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.SPADE, Denomination.TEN),
                Card(Suit.SPADE, Denomination.TWO),
                Card(Suit.SPADE, Denomination.JACK),
            )
        )
        val score = cards.score()
        assertThat(score).isEqualTo(Score(22))
    }

    @Test
    fun `에이스가 1이어도 11이어도 21이 안되는 경우는 11로 계산`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.TWO),
                Card(Suit.HEART, Denomination.TWO),
            )
        )
        val score = cards.score()
        assertThat(score).isEqualTo(Score(15))
    }

    @Test
    fun `에이스가 1이고 합이 21`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.JACK),
                Card(Suit.SPADE, Denomination.QUEEN),
            )
        )
        val score = cards.score()
        assertThat(score).isEqualTo(Score(21))
    }

    @Test
    fun `에이스가 11이고 합이 21`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.FOUR),
                Card(Suit.SPADE, Denomination.SIX),
            )
        )
        val score = cards.score()
        assertThat(score).isEqualTo(Score(21))
    }

    @Test
    fun `블랙잭`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.KING),
            )
        )
        val score = cards.score()
        assertThat(score).isEqualTo(Score(21))
    }

    @Test
    fun `에이스만 2개면 12`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE),
            )
        )
        val score = cards.score()
        assertThat(score).isEqualTo(Score(12))
    }

    @Test
    fun `에이스만 3개면 13`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE),
            )
        )
        val score = cards.score()
        assertThat(score).isEqualTo(Score(13))
    }

    @Test
    fun `에이스만 4개면 14`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE),
            )
        )
        val score = cards.score()
        assertThat(score).isEqualTo(Score(14))
    }
}
