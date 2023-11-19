@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardsTest {
    @Test
    fun `카드 하나를 추가한다`() {
        val card = Card(Denomination.ACE, Suit.SPADE)
        val cards = Cards()

        cards.add(card)

        assertThat(cards.get()).containsExactly(card)
    }

    @Test
    fun `카드들의 점수를 계산한다`() {
        val card1 = Card(Denomination.TWO, Suit.SPADE)
        val card2 = Card(Denomination.THREE, Suit.HEART)
        val cards = Cards()

        cards.run {
            add(card1)
            add(card2)
        }

        val actual = cards.calculateScore()

        assertThat(actual).isEqualTo(5)
    }

    @ParameterizedTest
    @EnumSource(value = Denomination::class, names = ["JACK", "QUEEN", "KING"])
    fun `J, Q, K는 10으로 계산한다`(denomination: Denomination) {
        val card = Card(denomination, Suit.SPADE)
        val cards = Cards()

        cards.add(card)

        val actual = cards.calculateScore()

        assertThat(actual).isEqualTo(10)
    }

    @Test
    fun `A는 1또는 11로 계산할 수 있으며, 21을 넘지 않는 최대 값으로 계산한다`() {
        val card1 = Card(Denomination.NINE, Suit.SPADE)
        val card2 = Card(Denomination.ACE, Suit.SPADE)
        val card3 = Card(Denomination.ACE, Suit.SPADE)
        val cards = Cards()

        cards.run {
            add(card1)
            add(card2)
            add(card3)
        }

        val actual = cards.calculateScore()

        assertThat(actual).isEqualTo(21)
    }
}
