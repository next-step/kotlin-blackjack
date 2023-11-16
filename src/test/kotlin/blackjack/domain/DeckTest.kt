@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱 하나에는 서로 다른 카드 52장이 들어있다`() {
        val deck = Deck()
        val actual = mutableSetOf<Card>()

        repeat(52) {
            actual.add(deck.draw())
        }

        assertThat(actual).hasSize(52)
    }

    @Test
    fun `덱에 있는 카드를 다 쓰면 52장의 카드를 새로 충전한다`() {
        val deck = Deck()
        val actualSet = mutableSetOf<Card>()
        val actualList = mutableListOf<Card>()

        repeat(53) {
            val card = deck.draw()
            actualSet.add(card)
            actualList.add(card)
        }

        assertThat(actualSet).hasSize(52)
        assertThat(actualList).hasSize(53)
    }
}
