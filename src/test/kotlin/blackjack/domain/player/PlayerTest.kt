package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.result.game.VictoryStatus
import blackjack.mock.card
import blackjack.mock.hand
import blackjack.mock.player
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PlayerTest : DescribeSpec({
    describe("Player()") {
        context("플레이어 이름이 주어지면") {
            val name = PlayerName("홍길동")
            it("플레이어가 생성된다") {
                val result = Player(name, { Action.HIT })

                result.name shouldBe name
            }
        }
    }

    describe("addCard") {
        context("플레이어게 카드를 주면") {
            val player = player()
            val card = Card(Suit.CLUB, Rank.ACE)

            player.addCard(card)

            it("플레이가 소유한 카드에 해당 카드가 추가된다") {
                player.hand.cards shouldBe listOf(card)
            }
        }

        context("플레이어가 다른 카드를 갖고 있던 경우") {
            val oldCard = Card(Suit.DIAMOND, Rank.EIGHT)
            val newCard = Card(Suit.CLUB, Rank.ACE)
            val player = player(hand = hand(oldCard))

            player.addCard(newCard)

            it("플레이가 소유한 카드에 해당 카드가 추가") {
                player.hand shouldBe Hand(mutableListOf(oldCard, newCard))
            }
        }

        context("플레이어가 버스트라면") {
            val over21Cards = hand(card(Rank.TEN), card(Rank.TEN), card(Rank.TEN))
            val player = player(hand = over21Cards)

            it("카드를 추가할 수 없다") {
                shouldThrowExactly<IllegalArgumentException> {
                    player.addCard(Card(Suit.CLUB, Rank.TEN))
                }
            }
        }

        context("플레이어가 21점이라면") {
            val score21Cards = hand(card(Rank.TEN), card(Rank.ACE))
            val player = player(hand = score21Cards)

            it("카드를 추가할 수 없다") {
                shouldThrowExactly<IllegalArgumentException> {
                    player.addCard(Card(Suit.CLUB, Rank.TEN))
                }
            }
        }
    }

    describe("isGreaterOrEqualToMaxScore") {
        context("21을 넘었을 때") {
            val player = player(hand = hand(card(Rank.THREE), card(Rank.TEN), card(Rank.TEN)))
            it("true가 반환된다") {
                player.isGreaterOrEqualToMaxScore shouldBe true
            }
        }

        context("21점일 때") {
            val player = player(hand = hand(card(Rank.ACE), card(Rank.TEN)))
            it("true가 반환된다") {
                player.isGreaterOrEqualToMaxScore shouldBe true
            }
        }

        context("21을 넘지 않았을 때") {
            val player = player(hand = hand(card(Rank.ACE), card(Rank.ACE), card(Rank.ACE)))

            it("false가 반환된다") {
                player.isGreaterOrEqualToMaxScore shouldBe false
            }
        }
    }

    describe("score") {
        context("플레이어가 21점의 카드를 갖고 있었다면") {
            val player = player(hand = hand(card(Rank.ACE), card(Rank.TEN)))

            val result = player.score

            it("플레이어 점수는 21점이다") {
                result.cardScore shouldBe 21
            }
        }
    }

    describe("hitOrStand") {
        context("이미 점수가 최대 점수 21을 넘었다면") {
            val score30Cards = hand(card(Rank.QUEEN), card(Rank.QUEEN), card(Rank.QUEEN))
            it("HIT을 받아도 STAND가 반환된다") {
                val player = player(action = Action.HIT, hand = score30Cards)

                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
            it("STAND를 받으면 STAND가 반환된다") {
                val player = player(action = Action.STAND, hand = score30Cards)

                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
        }

        context("이미 점수가 최대 점수 21점 이라면") {
            val score21Cards = hand(card(Rank.QUEEN), card(Rank.ACE))
            it("HIT을 받아도 STAND가 반환된다") {
                val player = player(action = Action.HIT, hand = score21Cards)

                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
            it("STAND를 받으면 STAND가 반환된다") {
                val player = player(action = Action.STAND, hand = score21Cards)

                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
        }

        context("최대 점수 21을 넘지 않았을 때") {
            val score5Cards = hand(card(Rank.TWO), card(Rank.THREE))

            it("HIT을 받으면 HIT이 반한된다") {
                val player = player(action = Action.HIT, hand = score5Cards)

                val result = player.hitOrStand()

                result shouldBe Action.HIT
            }
            it("STAND를 받으면 STAND가 반환된다") {
                val player = player(action = Action.STAND, hand = score5Cards)

                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
        }
    }

    describe("equals") {
        context("두 플레이어의 이름이 다르면") {
            val action = { _: Player -> Action.HIT }
            val hand = hand()

            val player1 = Player(PlayerName("kim"), action, hand)
            val player2 = Player(PlayerName("lee"), action, hand)

            it("다른 플레이어로 취급한다") {
                player1 shouldNotBe player2
            }
        }
    }

    context("두 플레이어의 이름이 같다면") {
        val action = { _: Player -> Action.HIT }

        val player1 = Player(PlayerName("kim"), action, hand(card(Rank.ACE)))
        val player2 = Player(PlayerName("kim"), action, hand(card(Rank.TEN)))

        it("같은 플레이어로 취급한다") {
            player1 shouldBe player2
        }
    }

    describe("isBlackJack") {
        context("플레이어가 블랙잭이면(카드가 2장이며, 해당 카드 합이 21)") {
            val player = player(hand = hand(card(Rank.TEN), card(Rank.ACE)))
            it("true가 반환된다") {
                player.isBlackJack shouldBe true
            }
        }

        context("플레이어 카드가 2장이지만 21점이 아니면") {
            val player = player(hand = hand(card(Rank.TEN), card(Rank.TWO)))
            it("false가 반환된다") {
                player.isBlackJack shouldBe false
            }
        }

        context("플레이어 카드 합이 21이지만 카드 수가 2장 초과면") {
            val player = player(hand = hand(card(Rank.ACE), card(Rank.TEN), card(Rank.TEN)))
            it("false가 반환된다") {
                player.isBlackJack shouldBe false
            }
        }
    }

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
                player.judgeVictory(dealer) shouldBe VictoryStatus.LOSS
            }
        }
        context("플레이어: 버스트 이고 딜러도 버스트라면") {
            val player = player(hand = score22Cards)
            val dealer = Dealer(player = DealerPlayer(hand = score22Cards))

            it("LOSS다") {
                player.judgeVictory(dealer) shouldBe VictoryStatus.LOSS
            }
        }
        context("플레이어(5) < 딜러(10) 로 플레이어가 딜러보다 점수가 낮다면") {
            val player = player(hand = score5Cards)
            val dealer = Dealer(player = DealerPlayer(score10Cards))

            it("LOSS다") {
                player.judgeVictory(dealer) shouldBe VictoryStatus.LOSS
            }
        }
        context("플레이어(21, 블랙잭) > 딜러(5) 로 플레이어가 블랙잭으로 딜러보다 점수가 높다면") {
            val player = player(hand = blackJackCards)
            val dealer = Dealer(player = DealerPlayer(score5Cards))

            it("WIN이다") {
                player.judgeVictory(dealer) shouldBe VictoryStatus.WIN
            }
        }
        context("플레이어(10) > 딜러(5) 로 플레이어가 딜러보다 점수가 높다면") {
            val player = player(hand = score10Cards)
            val dealer = Dealer(player = DealerPlayer(score5Cards))

            it("WIN이다") {
                player.judgeVictory(dealer) shouldBe VictoryStatus.WIN
            }
        }

        context("플레이어(10)이고 딜러가 버스트라면") {
            val player = player(hand = score10Cards)
            val dealer = Dealer(player = DealerPlayer(score22Cards))

            it("WIN이다") {
                player.judgeVictory(dealer) shouldBe VictoryStatus.WIN
            }
        }

        context("플레이어(10) == 딜러(10)로 점수가 같다면") {
            val player = player(hand = score10Cards)
            val dealer = Dealer(player = DealerPlayer(score10Cards))

            it("PUSH다") {
                player.judgeVictory(dealer) shouldBe VictoryStatus.PUSH
            }
        }

        context("플레이어(블랙잭) == 딜러(블랙잭)로 점수가 같다면") {
            val player = player(hand = blackJackCards)
            val dealer = Dealer(player = DealerPlayer(blackJackCards))

            it("PUSH다") {
                player.judgeVictory(dealer) shouldBe VictoryStatus.PUSH
            }
        }

        context("플레이어(21) == 딜러(블랙잭)로 점수가 같다면") {
            val player = player(hand = score21NotBlackJackCards)
            val dealer = Dealer(player = DealerPlayer(blackJackCards))

            it("PUSH다") {
                player.judgeVictory(dealer) shouldBe VictoryStatus.PUSH
            }
        }
    }
})
