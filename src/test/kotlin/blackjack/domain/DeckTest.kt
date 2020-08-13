package blackjack

import blackjack.domain.Deck
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

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

    @DisplayName("Deck이 비어있으면 IllegalStateException을 발생시킨다")
    @Test
    fun `empty deck exception`() {

        assertThatThrownBy {
            // when
            repeat(53) { deck.provideCard(deck.shuffled()) }

            // then
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Deck has no card")
    }
}
