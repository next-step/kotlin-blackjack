package blackjack.domain.card

import io.kotest.matchers.collections.shouldNotContainDuplicates
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 덱은 52장의 카드로 이루어져 있다`() {
        val cardDeck = CardDeck()
        val numberOfCardsInDeck = 52

        assertThatNoException().isThrownBy {
            repeat(numberOfCardsInDeck) {
                cardDeck.drawCard()
            }
        }

        assertThatIllegalArgumentException()
            .isThrownBy { cardDeck.drawCard() }
    }

    @Test
    fun `카드 덱에는 중복되는 카드가 없다`() {
        val cardDeck = CardDeck()
        val numberOfCardsInDeck = 52

        val drawnCards = (1..numberOfCardsInDeck).map { cardDeck.drawCard() }

        assertThatNoException().isThrownBy { drawnCards.shouldNotContainDuplicates() }
    }
}
