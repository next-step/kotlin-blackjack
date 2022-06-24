package blackjack.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardSymbolTest {

    @Test
    fun `카드심볼은 총 13개다`() {
        assertThat(CardSymbol.values().size).isEqualTo(13)
    }
}
