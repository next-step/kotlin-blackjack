package blackjack.domain.card

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "Deck의 카드가 중복 없이, 52장 생성된다" {
        val deck = Deck.create()
        val set = generateSequence { deck.getNextCard() }
            .take(Deck.TOTAL_CARD_SIZE)
            .toMutableSet()

        set.size shouldBe Deck.TOTAL_CARD_SIZE
    }

    "Deck에서 52장을 초과하여 카드를 뽑을 경우 예외가 발생한다" {
        shouldThrow<RuntimeException> {
            val deck = Deck.create()
            generateSequence { deck.getNextCard() }
                .take(Deck.TOTAL_CARD_SIZE + 1)
                .toMutableSet()
        }
    }
})
