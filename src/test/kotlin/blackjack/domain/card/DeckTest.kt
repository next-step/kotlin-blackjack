package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("덱(Deck)")
internal class DeckTest {

    @Test
    fun `초기화된 덱을 구성할 수 있다`() {
        val deck = Deck.initialize { it }

        assertAll(
            { assertThat(deck).isNotNull },
            { assertThat(deck).isExactlyInstanceOf(Deck::class.java) }
        )
    }
}
