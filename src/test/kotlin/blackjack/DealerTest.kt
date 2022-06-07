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

    describe("checkWinners") {
        context("딜러의 카드 점수 합이 21을 초과할 경우") {
            it("모든 플레이어가 우승한다.") {
                val dealer = Dealer(PlayerCards(CardNumber.Num8, CardNumber.Num9, CardNumber.Num10))
                val player1 = Player("name", PlayerCards(CardNumber.Num4))
                val player2 = Player("name", PlayerCards(CardNumber.Num4))

                dealer.checkWinners(Players(player1, player2))

                player1.winningRecord.winCount shouldBe 1
                player2.winningRecord.winCount shouldBe 1
            }
        }
        context("딜러의 카드 점수 합이 17인 경우") {
            it("17보다 높은 플레이어는 우승, 낮을 플레이어는 패배한다.") {
                val dealer = Dealer(PlayerCards(CardNumber.Num7, CardNumber.Num10))
                val player1 = Player("name", PlayerCards(CardNumber.Num8, CardNumber.Num10))
                val player2 = Player("name", PlayerCards(CardNumber.Num6, CardNumber.Num10))

                dealer.checkWinners(Players(player1, player2))

                player1.winningRecord.winCount shouldBe 1
                player2.winningRecord.loseCount shouldBe 1
            }
        }
        context("딜러의 카드 점수 합이 21이하인 경우 ") {
            it("21을 초과한 플레이어는 패배한다.") {
                val dealer = Dealer(PlayerCards(CardNumber.Num7, CardNumber.Num10))
                val player1 = Player("name", PlayerCards(CardNumber.Num8, CardNumber.Num9, CardNumber.Num10))

                dealer.checkWinners(Players(player1))

                player1.winningRecord.loseCount shouldBe 1
            }
        }
    }
})

private fun PlayerCards(vararg cards: CardNumber) = blackjack.domain.PlayerCards(cards.toList().map { Card(it) })
private fun Players(vararg player: Player) = Players(player.toList())
private fun Card(number: CardNumber) = Card(Symbol.Diamond, number)
