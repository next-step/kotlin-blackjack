package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class DeckTest : StringSpec({
    "한장의 카드를 반환한다." {
        val deck = RandomDeck()

        val singleCard = deck.draw()

        singleCard.shouldBeInstanceOf<Card>()
    }
})
