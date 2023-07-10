package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.NumberShape
import blackjack.domain.card.Pattern
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "카드 뭉치의 크기는 초기에 48장이다." {
        val deck = Deck.makeDeck()
        deck.cards.size shouldBe 48
    }

    "카드의 네가지 문양으로 13장의 카드를 다 가지고 있어야한다." {
        val deck = Deck.makeDeck()
        Pattern.values().forAll { pattern ->
            NumberShape.values().forAll { number ->
                deck.cards shouldContain Card(number, pattern)
            }
        }
    }

    "카드 뭉치에서 카드 한장을 가져가면 그 카드는 뭉치에 남아있으면 안된다." {
        val deck = Deck.makeDeck()
        val oneCard = deck.getOneCard()
        deck.cards shouldNotContain oneCard
    }
})
