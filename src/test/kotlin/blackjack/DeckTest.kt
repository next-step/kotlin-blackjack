package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class DeckTest : StringSpec({
    "한장의 카드를 반환한다." {
        val deck = Deck()

        val singleCard = deck.getSingleCard()

        singleCard.shouldBeInstanceOf<Card>()
    }
})
