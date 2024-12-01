package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class DeckTest : StringSpec({
    "카드를 한 장 뽑을 수 있다." {
        val deck = Deck()
        val card = deck.drawCard()
        card.shouldBeInstanceOf<Card>()
    }
})
