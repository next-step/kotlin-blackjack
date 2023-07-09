package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "카드 뭉치의 크기는 초기에 52장이다." {
        val deck = Deck.makeDeck()
        deck.cards.size shouldBe 52
    }

    "카드의 네가지 문양으로 13장의 카드를 다 가지고 있어야한다." {
        val deck = Deck.makeDeck()
        Pattern.values().forAll { pattern ->
            NumberShape.values().forAll { number ->
                deck.cards shouldContain Card(number, pattern)
            }
        }
    }
})
