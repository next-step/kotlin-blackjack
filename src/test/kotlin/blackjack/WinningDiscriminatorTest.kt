package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.DealerRule
import blackjack.domain.Player
import blackjack.domain.PlayerCards
import blackjack.domain.Players
import blackjack.domain.Symbol
import blackjack.domain.WinningDiscriminator
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class WinningDiscriminatorTest : DescribeSpec({
    describe("discrimination") {
        context("딜러의 카드 점수 합이 21을 초과할 경우") {
            it("21을 초과한 플레이어는 전부 승리한다.") {
                val dealer = Dealer(PlayerCards(CardNumber.Num8, CardNumber.Num9, CardNumber.Num10))
                val player1 = Player("name", PlayerCards(CardNumber.Num4))
                val player2 = Player("name", PlayerCards(CardNumber.Num4))

                val results = WinningDiscriminator.discrimination(dealer, Players(player1, player2))

                val dealerResult = results[0]
                val player1Result = results[1]
                val player2Result = results[2]

                dealerResult.loseCount shouldBe 2

                player1Result.winCount shouldBe 1
                player2Result.winCount shouldBe 1
            }
        }
        context("딜러의 카드 점수 합이 17인 경우") {
            it("17보다 높은 플레이어는 우승, 낮을 플레이어는 패배한다.") {
                val dealer = Dealer(PlayerCards(CardNumber.Num7, CardNumber.Num10))
                val player1 = Player("name", PlayerCards(CardNumber.Num8, CardNumber.Num10))
                val player2 = Player("name", PlayerCards(CardNumber.Num6, CardNumber.Num10))

                val results = WinningDiscriminator.discrimination(dealer, Players(player1, player2))

                val dealerResult = results[0]
                val player1Result = results[1]
                val player2Result = results[2]

                dealerResult.winCount shouldBe 1
                dealerResult.loseCount shouldBe 1

                player1Result.winCount shouldBe 1
                player2Result.loseCount shouldBe 1
            }
        }
        context("딜러의 점수에 상관없이") {
            it("21을 초과한 플레이어는 패배한다.") {
                val dealer = Dealer(PlayerCards(CardNumber.Num7, CardNumber.Num9, CardNumber.Num10))
                val player = Player("name", PlayerCards(CardNumber.Num8, CardNumber.Num9, CardNumber.Num10))

                val results = WinningDiscriminator.discrimination(dealer, Players(player))

                val dealerResult = results[0]
                val playerResult = results[1]

                dealerResult.winCount shouldBe 1
                playerResult.loseCount shouldBe 1
            }
        }

        context("딜러의 카드 점수 합이 17인 경우 ") {
            it("점수 합이 17인 플레이어는 무승부다.") {
                val dealer = Dealer(PlayerCards(CardNumber.Num7, CardNumber.Num10))
                val player1 = Player("name", PlayerCards(CardNumber.Num7, CardNumber.Num10))

                val results = WinningDiscriminator.discrimination(dealer, Players(player1))

                val dealerResult = results[0]
                val player1Result = results[1]

                dealerResult.drawCount shouldBe 1

                player1Result.drawCount shouldBe 1
            }
        }
    }
})

private fun PlayerCards(vararg cards: CardNumber) = PlayerCards(cards.toList().map { Card(it) })
private fun Players(vararg player: Player) = Players(player.toList())
private fun Dealer(card: PlayerCards) = Player("딜러", card, DealerRule)
private fun Card(number: CardNumber) = Card(Symbol.Diamond, number)
