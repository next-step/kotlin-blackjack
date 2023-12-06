package blackjack.domain

import blackjack.domain.card.Rank
import blackjack.domain.player.DealerPlayer
import blackjack.domain.result.game.VictoryStatus
import blackjack.mock.card
import blackjack.mock.hand
import blackjack.mock.player
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BlackJackJudgeTest : DescribeSpec({
    describe("judgeVictory") {
        val score22Cards =
            hand(card(Rank.TEN), card(Rank.TEN), card(Rank.TWO))
        val blackJackCards =
            hand(card(Rank.TEN), card(Rank.ACE))
        val score21NotBlackJackCards =
            hand(card(Rank.TEN), card(Rank.FIVE), card(Rank.SIX))
        val score10Cards = hand(card(Rank.FIVE), card(Rank.FIVE))
        val score5Cards = hand(card(Rank.TWO), card(Rank.THREE))

        context("플레이어가 버스트라면") {
            val player = player(hand = score22Cards)
            val dealer = Dealer(player = DealerPlayer(score10Cards))

            it("LOSS다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.LOSS
            }
        }
        context("플레이어가 버스트 이고 딜러도 버스트라면") {
            val player = player(hand = score22Cards)
            val dealer = Dealer(player = DealerPlayer(hand = score22Cards))

            it("LOSS다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.LOSS
            }
        }
        context("플레이어(5) < 딜러(10) 로 플레이어가 딜러보다 점수가 낮다면") {
            val player = player(hand = score5Cards)
            val dealer = Dealer(player = DealerPlayer(score10Cards))

            it("LOSS다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.LOSS
            }
        }
        context("플레이어(21, 블랙잭) > 딜러(5) 로 플레이어가 블랙잭으로 딜러보다 점수가 높다면") {
            val player = player(hand = blackJackCards)
            val dealer = Dealer(player = DealerPlayer(score5Cards))

            it("WIN이다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.WIN
            }
        }
        context("플레이어(10) > 딜러(5) 로 플레이어가 딜러보다 점수가 높다면") {
            val player = player(hand = score10Cards)
            val dealer = Dealer(player = DealerPlayer(score5Cards))

            it("WIN이다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.WIN
            }
        }

        context("플레이어(10)이고 딜러가 버스트라면") {
            val player = player(hand = score10Cards)
            val dealer = Dealer(player = DealerPlayer(score22Cards))

            it("WIN이다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.WIN
            }
        }

        context("플레이어(10) == 딜러(10)로 점수가 같다면") {
            val player = player(hand = score10Cards)
            val dealer = Dealer(player = DealerPlayer(score10Cards))

            it("PUSH다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.PUSH
            }
        }

        context("플레이어(블랙잭) == 딜러(블랙잭)로 점수가 같다면") {
            val player = player(hand = blackJackCards)
            val dealer = Dealer(player = DealerPlayer(blackJackCards))

            it("PUSH다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.PUSH
            }
        }

        context("플레이어(21) == 딜러(블랙잭)로 점수가 같다면") {
            val player = player(hand = score21NotBlackJackCards)
            val dealer = Dealer(player = DealerPlayer(blackJackCards))

            it("PUSH다") {
                BlackJackJudge.judgeVictory(player, dealer) shouldBe VictoryStatus.PUSH
            }
        }
    }
})
