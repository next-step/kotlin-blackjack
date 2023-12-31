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

    describe("isBlackJack") {
        context("플레이어가 블랙잭이면(카드가 2장이며, 해당 카드 합이 21)") {
            val player = player(hand = hand(card(Rank.TEN), card(Rank.ACE)))
            it("true가 반환된다") {
                BlackJackJudge.isBlackJack(player) shouldBe true
            }
        }

        context("플레이어 카드가 2장이지만 21점이 아니면") {
            val player = player(hand = hand(card(Rank.TEN), card(Rank.TWO)))
            it("false가 반환된다") {
                BlackJackJudge.isBlackJack(player) shouldBe false
            }
        }

        context("플레이어 카드 합이 21이지만 카드 수가 2장 초과면") {
            val player = player(hand = hand(card(Rank.ACE), card(Rank.TEN), card(Rank.TEN)))
            it("false가 반환된다") {
                BlackJackJudge.isBlackJack(player) shouldBe false
            }
        }
    }
})
