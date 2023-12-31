package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
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
                result.value shouldBe 21
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
})
