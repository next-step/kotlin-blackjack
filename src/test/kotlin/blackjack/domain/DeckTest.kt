package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DeckTest {
    private lateinit var deck: Deck

    @BeforeEach
    fun `set up`() {
        deck = Deck()
    }

    @DisplayName("카드 한 장을 뽑고 덱에서 제거한다")
    @Test
    fun `provide a card`() {
        // when
        deck.provideCard(deck.shuffled())

        // then
        assertTrue(deck.shuffled().size == 51)
    }

    @DisplayName("Deck이 비어있을 때(53번째 카드를 꺼낼 때) null을 반환한다")
    @Test
    fun `empty deck returns null`() {
        // given
        repeat(52) { deck.provideCard(deck.shuffled()) }

        // when
        val card53th = deck.provideCard(deck.shuffled())

        // then
        assertThat(card53th).isEqualTo(null)
    }
}
