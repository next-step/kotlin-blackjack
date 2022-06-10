package blackjack.domain.card

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

class CardDeckTest : StringSpec({
    "카드 덱을 생성할수 있다." {
        shouldNotThrow<Throwable> {
            setupCardDeck {
                spade()
                diamond()
                heart()
                club()
            }
        }
    }

    "카드 문양이 4개가 아닌 경우 Exception을 던진다." {
        shouldThrow<IllegalArgumentException> {
            setupCardDeck {
                spade()
                diamond()
                heart()
                heart()
            }
        }
    }

    "카드 덱에서 카드를 뽑을수 있다." {
        cardDeck().draw() should instanceOf(Card::class)
    }

    "카드 덱에서 카드뽑는 경우 카드 덱의 남은 카드가 감소한다." {
        val cardDeck = cardDeck()
        val cardDeckSize = cardDeck.cards.size

        cardDeck.draw()

        cardDeck.cards.size shouldBe cardDeckSize - 1
    }

    "모든 카드를 소진한뒤 카드를 뽑으면 Exception을 던진다." {
        val cardDeck = cardDeck()
        val cardDeckSize = cardDeck.cards.size

        repeat(cardDeckSize) { cardDeck.draw() }

        shouldThrow<IllegalArgumentException> { cardDeck.draw() }
    }
}) {
    companion object {
        fun cardDeck(): CardDeck = setupCardDeck {
            spade()
            diamond()
            heart()
            club()
        }

        fun sortedCardDeck(): CardDeck {
            val cards = cardDeck().cards.sortedBy { card -> card.denomination }
            return CardDeck(cards)
        }
    }
}
