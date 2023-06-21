package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GamerTest : FunSpec({

    test("현재 점수가 21점 미만이라면 카드를 추가할 수 있다.") {
        val targetCard = NumberCard(symbol = SymbolType.DIAMOND, number = 5)
        val gamer = Gamer(
            name = "이한솔",
            deck = Deck(
                mutableSetOf(
                    AceCard(SymbolType.DIAMOND),
                    NumberCard(symbol = SymbolType.DIAMOND, number = 9)
                )
            )
        )

        gamer.isAddable() shouldBe true

        gamer.addCard(targetCard)
        gamer.score() shouldBe 25
    }

    test("현재 점수가 21점 이상이라면 카드를 추가할 수 없다.") {
        val targetCard = NumberCard(symbol = SymbolType.DIAMOND, number = 5)
        val gamer = Gamer(
            name = "이한솔",
            deck = Deck(
                mutableSetOf(
                    AceCard(SymbolType.DIAMOND),
                    FaceCard(symbol = SymbolType.DIAMOND, faceType = FaceType.JACK)
                )
            )
        )

        gamer.isAddable() shouldBe false
        shouldThrow<IllegalStateException> {
            gamer.addCard(targetCard)
        }
    }
})
