package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드의 숫자는 2~9, 또는 K,Q,J(10) A가 올 수 있음`() {

        shouldNotThrow<IllegalStateException> {
            Card(CardNumber.CARD_JACK, CardType.CLOVER)
            Card(CardNumber.CARD_TWO, CardType.SPADE)
            Card(CardNumber.CARD_ACE, CardType.DIAMOND)
            Card(CardNumber.CARD_KING, CardType.HEART)
        }

    }
}
