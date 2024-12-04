package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "카드를 생성한다." {
        val deck = Deck()
        deck.cards.size shouldBe 52
    }

    "카드를 한 장 뽑는다." {
        val deck = Deck()
        val drawnCards = deck.drawCards(1)
        drawnCards.size shouldBe 1
    }

    "카드를 여러 장 뽑는다." {
        val deck = Deck()
        val drawnCards = deck.drawCards(5)
        drawnCards.size shouldBe 5
    }

    "카드를 뽑을 때 남은 카드 수보다 많은 수를 요청하면 예외를 던진다." {
        val deck = Deck()
        shouldThrow<IllegalArgumentException> {
            deck.drawCards(53)
        }
    }

    "카드를 뽑을 때 0 이하의 수를 요청하면 예외를 던진다." {
        val deck = Deck()
        shouldThrow<IllegalArgumentException> {
            deck.drawCards(0)
        }
        shouldThrow<IllegalArgumentException> {
            deck.drawCards(-1)
        }
    }
})
