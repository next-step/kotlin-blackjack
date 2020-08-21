package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck()
    }

    @Test
    fun `카드 한 장을 주고 덱에서 한 장 제거한다`() {
        // when
        deck.provideCard()

        // then
        assertThat(deck.size).isEqualTo(51)
    }

    @Test
    fun `덱이 비었을 때 새로운 덱을 사용한다`() {
        // given
        repeat(52) { deck.provideCard() }

        // when
        val card53th = deck.provideCard()

        // then
        assertThat(card53th).isNotNull
    }
}
