package blackjack

import blackjack.domain.ExceptionTypes.EMPTY_SHUFFLED_CARD_DECK
import blackjack.domain.card.CardDeck
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class ShuffledCardDeckTest {
    @Test
    fun `52회 초과의 카드 요청시 예외가 발생한다`() {
        val expectedMessage = EMPTY_SHUFFLED_CARD_DECK
        val shuffledCardDeck = CardDeck.getShuffledCardDeck()
        repeat(52) { shuffledCardDeck.getNextCard() }

        assertThatIllegalArgumentException()
            .isThrownBy { shuffledCardDeck.getNextCard() }
            .withMessage(expectedMessage)
    }
    @Test
    fun `52회 이하의 카드를 요청 할시 예외가 발생하지 않는다`() {
        val shuffledCardDeck = CardDeck.getShuffledCardDeck()
        repeat(52) {
            assertThatNoException().isThrownBy { shuffledCardDeck.getNextCard() }
        }
    }
}
