package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardBundleTest {

    @Test
    fun `카드 1벌은 52장의 카드로 이루어진다`() {
        val cardBundle = CardBundle.getBundle()
        val fullStackSize = CardSuitInfo.values().size * CardNumberInfo.values().size

        repeat(fullStackSize) {
            assertThat(cardBundle.draw()).isNotNull()
        }

        assertThat(cardBundle.draw()).isNull()
    }
}
