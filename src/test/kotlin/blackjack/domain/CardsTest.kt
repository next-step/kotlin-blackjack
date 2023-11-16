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
    fun `useAceAs11이 false면 A는 1로 계산한다`() {
        val card = Card(Denomination.ACE, Suit.SPADE)
        val cards = Cards()

        cards.add(card)

        val actual = cards.calculateScore(false)

        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `useAceAs11이 true A는 1로 계산한다`() {
        val card = Card(Denomination.ACE, Suit.SPADE)
        val cards = Cards()

        cards.add(card)

        val actual = cards.calculateScore(true)

        assertThat(actual).isEqualTo(11)
    }
}
