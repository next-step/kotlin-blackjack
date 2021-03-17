package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class CardDeckTest {
    @DisplayName("카드덱 생성")
    @Test
    fun initCard() {
        assertDoesNotThrow { CardDeck.cards }.also { assertThat(it.size).isEqualTo(52) }
    }
}
