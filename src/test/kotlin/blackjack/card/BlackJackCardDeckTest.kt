package blackjack.card

import blackjack.card.deck.BlackJackCardDeck
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

internal class BlackJackCardDeckTest : StringSpec({
    "총 52장의 카드를 뽑을 수 있다" {
        val sut = BlackJackCardDeck()
        shouldNotThrowAny {
            repeat(52) {
                sut.castCard()
            }
        }
    }

    "총 53 번째 뽑기시에 예외가 발생한다" {
        val sut = BlackJackCardDeck()
        shouldThrow<BlackJackCardDeck.CardDeckEmptyException> {
            repeat(53) {
                sut.castCard()
            }
        }
    }
})