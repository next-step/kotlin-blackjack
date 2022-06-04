package blackjack

import blackjack.domain.AlphabetCard
import blackjack.domain.Card
import blackjack.domain.NumberCard
import blackjack.domain.PlayerCards
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerCardsTest : DescribeSpec({

    describe("score") {
        context("2,3,4 번의 카드가 있다면") {
            it("9점을 리턴한다.") {
                val playerCards = PlayerCards(Card(2), Card(3), Card(4))
                playerCards.score shouldBe 9
            }
        }
        context("A,J 번의 카드가 있다면") {
            it("21점을 리턴한다.") {
                val playerCards = PlayerCards(Card('A'), Card('J'))
                playerCards.score shouldBe 21
            }
        }
        context("A,A 번의 카드가 있다면") {
            it("12점을 리턴한다.") {
                val playerCards = PlayerCards(AlphabetCard(Symbol.Diamond, 'A'), AlphabetCard(Symbol.Heart, 'A'))
                playerCards.score shouldBe 12
            }
        }
    }
})

private fun PlayerCards(vararg cards: Card) = PlayerCards(cards.toList())
private fun Card(number: Int) = NumberCard(Symbol.Diamond, number)
private fun Card(char: Char) = AlphabetCard(Symbol.Diamond, char)
