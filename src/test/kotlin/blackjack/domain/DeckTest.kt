package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱에 있는 카드는 총 52장이어야 한다." {
        val deck = Deck.create()
        deck.size() shouldBe 52
    }

    "덱에서 52장 이하의 카드를 랜덤으로 뽑을 수 있다." {
        val deck = Deck.create()
        deck.drawCards(52)
        deck.size() shouldBe 0
    }

    "덱에서 카드를 52장보다 많이 뽑으면 예외가 발생한다." {
        val deck = Deck.create()

        shouldThrow<IllegalArgumentException> {
            deck.drawCards(53)
        }
    }

    "비어있는 덱에서 카드를 뽑으면 예외가 발생한다." {
        val deck = Deck.create()
        deck.drawCards(52)

        shouldThrow<IllegalStateException> {
            deck.drawCard()
        }
    }
})
