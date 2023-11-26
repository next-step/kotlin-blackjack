package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.*
import kotlin.collections.ArrayList

class CardDeckTest: StringSpec({

    "카드를 뽑을 수 있다" {
        val cardDeck = ListCardDeck(mutableListOf(Card.TWO, Card.THREE))
        val card = cardDeck.drawCard()
        card shouldBe Card.THREE
    }

})