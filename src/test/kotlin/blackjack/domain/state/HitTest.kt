package blackjack.domain.state

import blackjack.domain.Deck
import blackjack.domain.SPADES_TEN
import blackjack.domain.SPADES_TWO
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class HitTest {

    @Test
    internal fun `카드를 두 장 이상 보유한 상태가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val initialDeck = Deck(listOf(SPADES_TWO))
            Hit(initialDeck)
        }
    }

    @Test
    internal fun `히트 상태에서 카드를 한장 받았을때 21점 이하면 히트 상태가 된다`() {
        val initialDeck = Deck(listOf(SPADES_TWO, SPADES_TEN))
        val hit = Hit(initialDeck)
        val actual = hit.draw(SPADES_TWO)
        actual.shouldBeInstanceOf<Hit>()
    }

    @Test
    internal fun `히트 상태에서 카드를 한장 받았을때 21점이 초과되면 버스트 상태가 된다`() {
        val initialDeck = Deck(listOf(SPADES_TWO, SPADES_TEN))
        val hit = Hit(initialDeck)
        val actual = hit.draw(SPADES_TEN)
        actual.shouldBeInstanceOf<Burst>()
    }

    @Test
    internal fun `히트 상태에서 스테이를 선언하면 스테이 상태가 된다`() {
        val initialDeck = Deck(listOf(SPADES_TWO, SPADES_TEN))
        val hit = Hit(initialDeck)
        val actual = hit.stay()
        actual.shouldBeInstanceOf<Stay>()
    }
}
