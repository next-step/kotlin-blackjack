package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({

    describe("canDraw") {
        context("카드의 점수 합이 16이하이면 ") {
            it("true 를 리턴한다.") {
                val player = Dealer(PlayerCards(CardNumber.Num4, CardNumber.Jack))
                player.canDraw() shouldBe true
            }
        }
        context("카드의 점수 합이 17이상이면 ") {
            it("false 를 리턴한다.") {
                val player = Dealer(PlayerCards(CardNumber.Queen, CardNumber.King))
                player.canDraw() shouldBe false
            }
        }
    }
})

private fun PlayerCards(vararg cards: CardNumber) = blackjack.domain.PlayerCards(cards.toList().map { Card(it) })
private fun Players(vararg player: Player) = Players(player.toList())
private fun Card(number: CardNumber) = Card(Symbol.Diamond, number)
