package blackjack

import blackjack.domain.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private lateinit var deck: Deck

class DeckTest {
    @BeforeEach
    fun `set up`() {
        deck = Deck()
    }

    @Test
    fun `덱 사이즈가 52가 맞는지 확인`() {
        assertThat(deck.size).isEqualTo(52)
    }

    @Test
    fun `덱이 카드 주면 카드가 하나 없어 져야 함`() {
        deck.giveCard(deck.shuffled())
        assertThat(deck.shuffled().size).isEqualTo(51)
    }

    @Test
    fun `덱이 비어있으면 null을 반환한다`() {
        // 52회 동안 카드를 뽑는다.
        repeat(52) { deck.giveCard(deck.shuffled()) }
        val card53th = deck.giveCard(deck.shuffled())
        assertThat(card53th).isEqualTo(null)
    }
}
