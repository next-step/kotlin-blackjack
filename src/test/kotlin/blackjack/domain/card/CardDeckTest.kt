package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CardDeckTest {
    @Test
    fun `카드 덱은 기본적으로 52장의 카드로 이루어져 있다`() {
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
    fun `카드 덱이 52장의 카드로 이루어져 있지 않을 경우 IllegalArgumentException 을 반환한다`() {
        val cardsWithSizeLessThanFiftyTwo = Cards((1..51).map { Card.Jack(CardSuit.CLOVER) })
        val cardsWithSizeMoreThanFiftyTwo = Cards((1..53).map { Card.Jack(CardSuit.CLOVER) })

        assertAll(
            { assertThatIllegalArgumentException().isThrownBy { CardDeck(cardsWithSizeLessThanFiftyTwo) } },
            { assertThatIllegalArgumentException().isThrownBy { CardDeck(cardsWithSizeMoreThanFiftyTwo) } }
        )
    }

    @Test
    fun `카드 덱에 중복되는 카드가 있을 경우 IllegalArgumentException 이 발생한다`() {
        val card = Card.Jack(CardSuit.CLOVER)
        val cardsWithDuplicates = Cards((1..52).map { card })

        assertThatIllegalArgumentException().isThrownBy { CardDeck(cardsWithDuplicates) }
    }
}
