package blackjack.domain.result.game

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.DealerPlayer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameResultTest : DescribeSpec({
    describe("GameResult of (dealer, players)") {
        val score25Cards =
            mutableListOf(Card(Suit.CLUB, Rank.TEN), Card(Suit.CLUB, Rank.TEN), Card(Suit.CLUB, Rank.FIVE))
        val score22Cards =
            mutableListOf(Card(Suit.CLUB, Rank.TEN), Card(Suit.CLUB, Rank.TEN), Card(Suit.CLUB, Rank.TWO))
        val score21Cards = mutableListOf(Card(Suit.CLUB, Rank.TEN), Card(Suit.CLUB, Rank.ACE))
        val score10Cards = mutableListOf(Card(Suit.CLUB, Rank.TEN))
        val score5Cards = mutableListOf(Card(Suit.CLUB, Rank.FIVE))

        context("dealer(21) > player1(10) > player2(5)") {
            val dealer = Dealer(player = DealerPlayer(Hand((score21Cards))))
            val player1 = Player(PlayerName("kim"), { Action.HIT }, Hand(score10Cards))
            val player2 = Player(PlayerName("kim"), { Action.HIT }, Hand(score5Cards))

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
            val dealer = Dealer(player = DealerPlayer(Hand(score5Cards)))
            val player1 = Player(PlayerName("kim"), { Action.HIT }, Hand(score21Cards))
            val player2 = Player(PlayerName("kim"), { Action.HIT }, Hand(score10Cards))

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
            val dealer = Dealer(player = DealerPlayer(Hand(score10Cards)))
            val player1 = Player(PlayerName("kim"), { Action.HIT }, Hand(score21Cards))
            val player2 = Player(PlayerName("kim"), { Action.HIT }, Hand(score5Cards))

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
            val dealer = Dealer(player = DealerPlayer(Hand(score5Cards)))
            val player1 = Player(PlayerName("kim"), { Action.HIT }, Hand(score22Cards))
            val player2 = Player(PlayerName("kim"), { Action.HIT }, Hand(score22Cards))

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
            val dealer = Dealer(player = DealerPlayer(Hand(score22Cards)))
            val player1 = Player(PlayerName("kim"), { Action.HIT }, Hand(score5Cards))
            val player2 = Player(PlayerName("kim"), { Action.HIT }, Hand(score5Cards))

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
            val dealer = Dealer(player = DealerPlayer(Hand(score22Cards)))
            val player1 = Player(PlayerName("kim"), { Action.HIT }, Hand(score22Cards))
            val player2 = Player(PlayerName("kim"), { Action.HIT }, Hand(score25Cards))

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
