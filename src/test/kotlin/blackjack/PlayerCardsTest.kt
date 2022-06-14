package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.PlayerCards
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerCardsTest : DescribeSpec({

    describe("score") {
        listOf(
            listOf(CardNumber.Num2, CardNumber.Num3, CardNumber.Num4) to 9,
            listOf(CardNumber.Ace, CardNumber.Jack) to 21,
            listOf(CardNumber.Ace, CardNumber.Ace) to 12
        ).forAll { (cards, expected) ->
            context("$cards 번의 카드가 있다면") {
                it("$expected 점을 리턴한다.") {
                    val playerCards = PlayerCards(cards)
                    playerCards.score.value shouldBe expected
                }
            }
        }
    }
})

private fun PlayerCards(cards: List<CardNumber>) = PlayerCards(cards.map { Card(it) })
private fun Card(number: CardNumber) = Card(Symbol.Diamond, number)
