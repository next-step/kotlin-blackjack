package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

class CardTest {

    @Test
    fun `카드의 숫자는 2~9, 또는 A,K,Q,J가 올 수 있음`() {

        shouldThrow<IllegalStateException> {
            Card("1", CardType.CLOVER)
        }
        shouldThrow<IllegalStateException> {
            Card("10", CardType.CLOVER)
        }
        Card(CardNumber.CARD_TWO.number, CardType.CLOVER)
        Card(CardNumber.CARD_ACE.number, CardType.CLOVER)
        Card(CardNumber.CARD_KING.number, CardType.CLOVER)
        Card(CardNumber.CARD_QUEEN.number, CardType.CLOVER)
        Card(CardNumber.CARD_JACK.number, CardType.CLOVER)
    }

    @Test
    fun `카드세트는 중복이 없다`() {
        shouldNotThrow<IllegalStateException> {
            BlackJackTable.blackJackCards.di
        }
    }
}
