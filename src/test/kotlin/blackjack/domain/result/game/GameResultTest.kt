package blackjack.domain.result.game

import blackjack.domain.Dealer
import blackjack.domain.card.Rank
import blackjack.domain.player.DealerPlayer
import blackjack.domain.player.Players
import blackjack.mock.card
import blackjack.mock.hand
import blackjack.mock.player
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameResultTest : DescribeSpec({
    describe("GameResult of (dealer, players)") {
        val score25Cards =
            hand(card(Rank.TEN), card(Rank.TEN), card(Rank.FIVE))
        val score22Cards =
            hand(card(Rank.TEN), card(Rank.TEN), card(Rank.TWO))
        val score21Cards =
            hand(card(Rank.TEN), card(Rank.ACE))
        val score10Cards = hand(card(Rank.TEN))
        val score5Cards = hand(card(Rank.FIVE))

        context("dealer(21) > player1(10) > player2(5)") {
            val dealer = Dealer(player = DealerPlayer(score21Cards))
            val player1 = player(hand = score10Cards)
            val player2 = player(hand = score5Cards)

            val result = GameResult.of(Players(listOf(player1, player2)), dealer)

            it("player1 : LOSS") {
                result.playersResult.first().status shouldBe VictoryStatus.LOSS
            }
            it("player2 : LOSS") {
                result.playersResult.last().status shouldBe VictoryStatus.LOSS
            }
            it("dealer : (WIN, WIN)") {
                result.dealerResults.status.value shouldBe listOf(VictoryStatus.WIN, VictoryStatus.WIN)
            }
        }

        context("player1(21) > player2(10) > dealer(5)") {
            val dealer = Dealer(player = DealerPlayer(score5Cards))
            val player1 = player(hand = score21Cards)
            val player2 = player(hand = score10Cards)

            val result = GameResult.of(Players(listOf(player1, player2)), dealer)

            it("player1 : WIN") {
                result.playersResult.first().status shouldBe VictoryStatus.WIN
            }

            it("player2 : WIN") {
                result.playersResult.last().status shouldBe VictoryStatus.WIN
            }

            it("dealer : (LOSS, LOSS)") {
                result.dealerResults.status.value shouldBe listOf(VictoryStatus.LOSS, VictoryStatus.LOSS)
            }
        }

        context("player1(21) > dealer(10) > player2(5)") {
            val dealer = Dealer(player = DealerPlayer(score10Cards))
            val player1 = player(hand = score21Cards)
            val player2 = player(hand = score5Cards)

            val result = GameResult.of(Players(listOf(player1, player2)), dealer)

            it("player1 : WIN") {
                result.playersResult.first().status shouldBe VictoryStatus.WIN
            }

            it("player2 : LOSS") {
                result.playersResult.last().status shouldBe VictoryStatus.LOSS
            }

            it("dealer : (LOSS, WIN)") {
                result.dealerResults.status.value shouldBe listOf(VictoryStatus.LOSS, VictoryStatus.WIN)
            }
        }

        context("dealer(5) > player1(22, BUST) == player2(22, BUST)") {
            val dealer = Dealer(player = DealerPlayer(score5Cards))
            val player1 = player(hand = score22Cards)
            val player2 = player(hand = score22Cards)

            val result = GameResult.of(Players(listOf(player1, player2)), dealer)

            it("player1 : LOSS") {
                result.playersResult.first().status shouldBe VictoryStatus.LOSS
            }

            it("player2 : LOSS") {
                result.playersResult.last().status shouldBe VictoryStatus.LOSS
            }

            it("dealer : (WIN, WIN)") {
                result.dealerResults.status.value shouldBe listOf(VictoryStatus.WIN, VictoryStatus.WIN)
            }
        }

        context("player1(5) == player2(5) > dealer(22, BUST)") {
            val dealer = Dealer(player = DealerPlayer(score22Cards))
            val player1 = player(hand = score5Cards)
            val player2 = player(hand = score5Cards)

            val result = GameResult.of(Players(listOf(player1, player2)), dealer)

            it("player1 : WIN") {
                result.playersResult.first().status shouldBe VictoryStatus.WIN
            }

            it("player2 : WIN") {
                result.playersResult.last().status shouldBe VictoryStatus.WIN
            }

            it("dealer : (LOSS, LOSS)") {
                result.dealerResults.status.value shouldBe listOf(VictoryStatus.LOSS, VictoryStatus.LOSS)
            }
        }

        context("player1(22, BUST) == player2(25, BUST) == dealer(22, BUST)") {
            val dealer = Dealer(player = DealerPlayer(score22Cards))
            val player1 = player(hand = score22Cards)
            val player2 = player(hand = score25Cards)

            val result = GameResult.of(Players(listOf(player1, player2)), dealer)

            it("player1 : LOSS") {
                result.playersResult.first().status shouldBe VictoryStatus.LOSS
            }

            it("player2 : LOSS") {
                result.playersResult.last().status shouldBe VictoryStatus.LOSS
            }

            it("dealer : (WIN, WIN)") {
                result.dealerResults.status.value shouldBe listOf(VictoryStatus.WIN, VictoryStatus.WIN)
            }
        }
    }
})
