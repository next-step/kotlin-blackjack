package blackjack

import blackjack.domain.ExceptionTypes
import blackjack.domain.card.CardDeck
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `52회 초과의 카드 요청시 예외가 발생한다`() {
        val expectedMessage = ExceptionTypes.EMPTY_SHUFFLED_CARD_DECK
        CardDeck.resetShuffledCardDeck()
        repeat(52) { CardDeck.getNextCard() }

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { CardDeck.getNextCard() }
            .withMessage(expectedMessage)
    }

    @Test
    fun `52회 이하의 카드를 요청 할시 예외가 발생하지 않는다`() {
        CardDeck.resetShuffledCardDeck()
        repeat(52) {
            Assertions.assertThatNoException().isThrownBy { CardDeck.getNextCard() }
        }
    }
}
