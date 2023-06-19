package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeIn
import io.kotest.matchers.shouldBe

class DeckTest : FunSpec({

    test("중복되지 않은 카드를 추가할 수 있다.") {
        val deck = Deck()
        val card = NumberCard(symbol = SymbolType.DIAMOND, number = 9)
        deck.add(card = card)

        deck.score() shouldBe 9
        card shouldBeIn deck.toList()
    }

    test("중복된 카드는 추가되지 않는다.") {
        val deck = Deck()
        val card = NumberCard(symbol = SymbolType.DIAMOND, number = 9)

        deck.add(card = card)
        deck.add(card = card)

        deck.score() shouldBe 9
        card shouldBeIn deck.toList()
    }

    test("제거하고자 하는 카드가 유효하다면 카드를 제거할 수 있다.") {
        val deck = Deck()
        val card = NumberCard(symbol = SymbolType.DIAMOND, number = 9)
        deck.add(card)

        deck.remove(card)

        deck.score() shouldBe 0
        deck.toList() shouldHaveSize 0
    }

    test("유효한 갯수의 카드를 뽑을 수 있고, 덱에서 해당 카드는 사라진다.") {
        val cards = (2..5).map { NumberCard(symbol = SymbolType.DIAMOND, number = it) }
        val deck = Deck(values = cards.toMutableSet())

        val actual = deck.pick(2)

        actual shouldHaveSize 2
        deck.toList() shouldHaveSize 2

        actual shouldNotBeIn deck.toList()
    }
})
