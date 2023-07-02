package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱에 있는 카드는 총 52장이어야 한다." {
        val deck = Deck.create()
        deck.size() shouldBe 52
    }

    "덱에서 카드 한 장을 랜덤으로 뽑을 수 있다." {
        val deck = Deck.create()
        deck.drawCard()
        deck.size() shouldBe 51
    }

    "덱에서 카드 2 장을 랜덤으로 뽑을 수 있다." {
        val deck = Deck.create()
        deck.drawCards(2)
        deck.size() shouldBe 50
    }

    "덱에서 카드 52 장을 랜덤으로 뽑을 수 있다." {
        val deck = Deck.create()
        deck.drawCards(52)
        deck.size() shouldBe 0
    }

    "비어있는 덱에서 카드를 뽑으려고 시도하면 예외가 발생한다." {
        val deck = Deck.create()
        deck.drawCards(52)

        shouldThrow<IllegalArgumentException> {
            deck.drawCard()
        }
    }

    "덱에서 카드 53장의 카드를 뽑으려고 시도하면 예외가 발생한다." {
        val deck = Deck.create()

        shouldThrow<IllegalArgumentException> {
            deck.drawCards(53)
        }
    }
})
