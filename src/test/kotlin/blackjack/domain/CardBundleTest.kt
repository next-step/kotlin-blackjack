package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CardBundleTest {
    @Test
    fun `카드 1벌은 52장의 카드로 이루어지며, 소진시 예외를 던진다`() {
        val cardBundle = CardBundle.getBundle()
        val fullStackSize = CardSuitInfo.values().size * CardNumberInfo.values().size

        repeat(fullStackSize) {
            assertThat(cardBundle.draw()).isNotNull()
        }

        assertThatThrownBy { cardBundle.draw() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("카드가 부족합니다.")
    }

    @Test
    fun `카드를 1장 뽑는다`() {
        assertThat(CardBundle.getBundle().draw()).isInstanceOf(Card::class.java)
    }
}
