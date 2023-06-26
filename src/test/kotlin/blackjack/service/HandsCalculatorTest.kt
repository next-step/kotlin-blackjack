package blackjack.service

import blackjack.domain.PokerCard
import blackjack.domain.PokerSymbol
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class HandsCalculatorTest : StringSpec({
    "손패에서 최적의 값을 계산한다." {
        val king = PokerCard(PokerSymbol.CLUBS, 10, "K")
        val ace = PokerCard(PokerSymbol.CLUBS, 11, "A")
        val queen = PokerCard(PokerSymbol.CLUBS, 10, "Q")
        val five = PokerCard(PokerSymbol.CLUBS, 5, "5")
        val seven = PokerCard(PokerSymbol.CLUBS, 7, "7")
        listOf(
            listOf(king, king, ace) to 21,
            listOf(queen, five) to 15,
            listOf(ace, ace, ace) to 13,
            listOf(seven, queen, ace, ace, ace, ace) to 21
        ).forAll { (input, expected) ->
            val optimalValue = HandsCalculator.calculateOptimalValue(input)
            optimalValue shouldBe expected
        }
    }
})
