package blackjack.domain.dsl

import blackjack.domain.FaceType
import blackjack.domain.NumberCard
import blackjack.domain.SymbolType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize

class DeckBuilderTest : FunSpec({
    test("덱을 DSL로 구성할 수 있다.") {
        val deck = buildDeck {
            aceCards(SymbolType.DIAMOND, SymbolType.HEART)
            numberCards {
                2 until 10 from SymbolType.DIAMOND
            }

            faceCards {
                SymbolType.DIAMOND to FaceType.JACK and FaceType.QUEEN and FaceType.KING
            }
        }


        val actual = deck.toList()

        actual shouldHaveSize 13
        NumberCard(symbol = SymbolType.DIAMOND, number = 2) shouldBeIn actual
    }
})
