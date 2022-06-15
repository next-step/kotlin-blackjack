package blackjack

import blackjack.domain.BetResultDiscriminator
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.PlayerCards
import blackjack.domain.Players
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BetResultDiscriminatorTest : DescribeSpec({
    describe("discrimination") {
        forAll(
            row(20000, -20000),
            row(-20000, 20000),
            row(0, 0)
        ) { playerBetMoney, dealerExpectedMoney ->
            context("플레이어가 총 수익이 20000원을 인 경우") {
                it("딜러는 -20000원을 갇는다.") {
                    val betMoney = Money(playerBetMoney)
                    val expected = Money(dealerExpectedMoney)

                    val dealerCards = PlayerCards(CardNumber.Num2)
                    val playerCards = PlayerCards(CardNumber.Num3)

                    val dealer = Dealer(dealerCards)
                    val player = Player(name = "", betMoney = betMoney, playerCards = playerCards)

                    val results = BetResultDiscriminator.discrimination(dealer, Players(player))

                    val dealerResult = results[0]

                    dealerResult.earnMoney shouldBe expected
                }
            }
        }
    }
})

private fun PlayerCards(vararg cards: CardNumber) = PlayerCards(cards.toList().map { Card(it) })
private fun Players(vararg player: Player) = Players(player.toList())
private fun Card(number: CardNumber) = Card(Symbol.Diamond, number)
