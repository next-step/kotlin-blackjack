package blackjack

import blackjack.card.Card
import blackjack.card.Deck
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "덱은 52장의 카드로 구성된다." {
        // Arrange:
        val deck = Deck()

        // Act:
        val result = deck

        // Assert:
        result.cards.size shouldBe 52
    }

    "덱이 초기에 52장이 아니면 예외가 발생한다." {
        // Arrange:
        val cards = mutableListOf<Card>()

        // Act:
        val result = shouldThrow<IllegalArgumentException> {
            Deck(cards)
        }

        // Assert:
        result.message shouldBe "카드의 수가 52장이 아닙니다."
    }

    "덱은 카드를 한 장 뽑을 수 있다." {
        // Arrange:
        val deck = Deck()

        // Act:
        deck.pick()

        // Assert:
        deck.cards.size shouldBe 51
    }

    "덱에 뽑을 카드가 없으면 예외가 발생한다." {
        // Arrange:
        val deck = Deck()
        repeat(52) {
            deck.pick()
        }

        // Act:
        val result = shouldThrow<IllegalArgumentException> {
            deck.pick()
        }

        // Assert:
        result.message shouldBe "더 이상 뽑을 카드가 없습니다."
    }
})
