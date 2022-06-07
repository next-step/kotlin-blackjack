package blackjack

import blackjack.domain.AlphabetCard
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.NumberCard
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({

    describe("canDraw") {
        context("카드의 점수 합이 16이하이면 ") {
            it("true 를 리턴한다.") {
                val player = Dealer(PlayerCards(Card(4), Card('J')))
                player.canDraw() shouldBe true
            }
        }
        context("카드의 점수 합이 17이상이면 ") {
            it("false 를 리턴한다.") {
                val player = Dealer(PlayerCards(Card('Q'), Card('K')))
                player.canDraw() shouldBe false
            }
        }
    }
})

private fun PlayerCards(vararg cards: Card) = blackjack.domain.PlayerCards(cards.toList())
private fun Card(number: Int) = NumberCard(Symbol.Diamond, number)
private fun Card(char: Char) = AlphabetCard(Symbol.Diamond, char)
