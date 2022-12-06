package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.throwable.shouldHaveMessage

class CardDeckTest : StringSpec({

    "뽑을 카드가 없다면 예외가 발생한다." {
        val message = shouldThrow<IllegalStateException> {
            CardDeck.empty().draw()
        }

        message shouldHaveMessage "뽑을 카드가 없습니다."
    }

    "카드를 뽑으면 총 카드 수가 1 만큼 감소한다." {
        val cardDeck = CardDeck.shuffle()
        val initCount = cardDeck.cards.size

        cardDeck.draw()

        cardDeck.cards shouldHaveSize initCount.dec()
    }

    "카드를 얻으면 총 카드 숫가 1 만큼 증가된다." {
        val cardDeck = CardDeck.empty()
        val initCount = cardDeck.cards.size

        val card = Card(Suit.CLUB, Number.ACE)
        cardDeck.add(card)

        cardDeck.cards shouldHaveSize initCount.inc()
    }
})
