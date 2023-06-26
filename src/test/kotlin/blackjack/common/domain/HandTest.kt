package blackjack.common.domain

import blackjack.common.domain.Hand
import blackjack.common.domain.PokerCard
import blackjack.common.domain.PokerSymbol
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    "핸드의 카드를 심볼과 값들 더한 형식으로 리턴한다." {
        val ace = PokerCard(PokerSymbol.HEARTS, 11, "A")
        val king = PokerCard(PokerSymbol.CLUBS, 10, "K")
        val queen = PokerCard(PokerSymbol.DIAMONDS, 10, "Q")
        val jack = PokerCard(PokerSymbol.SPADES, 10, "J")
        val seven = PokerCard(PokerSymbol.CLUBS, 7, "7")
        listOf(
            ace to "A하트",
            king to "K클로버",
            queen to "Q다이아몬드",
            jack to "J스페이드",
            seven to "7클로버"
        ).forAll { (input, expected) ->
            val hand = Hand()
            hand.addNewCard(input)
            hand.toRepresent() shouldBe expected
        }
    }

    "핸드의 카드의 총합이 21 이하면 true를 리턴한다." {
        val ace = PokerCard(PokerSymbol.HEARTS, 11, "A")
        val king = PokerCard(PokerSymbol.CLUBS, 10, "K")
        val queen = PokerCard(PokerSymbol.DIAMONDS, 10, "Q")
        val jack = PokerCard(PokerSymbol.SPADES, 10, "J")
        val seven = PokerCard(PokerSymbol.CLUBS, 7, "7")
        listOf(
            listOf(ace, queen) to false,
            listOf(king, ace, jack) to false,
            listOf(seven, seven, seven, king) to false,
            listOf(jack) to true,
            listOf(seven, seven) to true
        ).forAll { (input, expected) ->
            val hand = Hand()
            input.forEach { hand.addNewCard(it) }
            hand.isNotBlackJackOrNotBust() shouldBe expected
        }
    }
})
