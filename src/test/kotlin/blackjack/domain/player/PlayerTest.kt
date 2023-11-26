package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : DescribeSpec({
    describe("Player()") {
        context("플레이어 이름이 주어지면") {
            val name = PlayerName("홍길동")
            it("플레이어 생성") {
                val result = Player(name, { Action.HIT })

                result.name shouldBe name
            }
        }
    }

    describe("addCard") {
        context("플레이어게 카드를 주면") {
            val player = Player(PlayerName("홍길동"), { Action.HIT })
            val card = Card(Suit.CLUB, Rank.ACE)

            player.addCard(card)

            it("플레이가 소유한 카드에 카드가 추가") {
                player.hand shouldBe Hand(mutableListOf(card))
            }
        }

        context("플레이어가 다른 카드를 갖고 있던 경우") {
            val oldCard = Card(Suit.DIAMOND, Rank.EIGHT)
            val player = Player(PlayerName("홍길동"), { Action.HIT }, Hand(mutableListOf(oldCard)))
            val newCard = Card(Suit.CLUB, Rank.ACE)

            player.addCard(newCard)

            it("플레이가 소유한 카드에 카드가 추가") {
                player.hand shouldBe Hand(mutableListOf(oldCard, newCard))
            }
        }
    }

    describe("isBust") {
        context("21을 넘었을 때") {
            val player = Player(
                PlayerName("kim"), { Action.HIT },
                Hand(
                    mutableListOf(
                        Card(Suit.DIAMOND, Rank.THREE), Card(Suit.HEART, Rank.TEN), Card(Suit.HEART, Rank.TEN)
                    )
                )
            )
            it("true 반환") {
                player.isBust shouldBe true
            }
        }

        context("21을 넘지 않았을 때") {
            val player = Player(
                PlayerName("kim"), { Action.HIT },
                Hand(
                    mutableListOf(
                        Card(Suit.DIAMOND, Rank.ACE), Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.ACE)
                    )
                )
            )
            it("false 반환") {
                player.isBust shouldBe false
            }
        }
    }

    describe("score") {
        val player = Player(
            PlayerName("kim"), { Action.HIT },
            Hand(
                mutableListOf(
                    Card(Suit.DIAMOND, Rank.ACE), Card(Suit.HEART, Rank.TEN)
                )
            )
        )
        context("플레이어의 점수 조회") {
            val result = player.score
            it("플레이어 점수 반환") {
                result.cardScore shouldBe 21
            }
        }
    }

    describe("hitOrStand") {
        context("이미 점수가 최대 점수 21을 넘었다면") {
            val score30Cards = mutableListOf(
                Card(Suit.HEART, Rank.QUEEN),
                Card(Suit.HEART, Rank.QUEEN),
                Card(Suit.HEART, Rank.QUEEN),
            )
            it("HIT을 받아도 STAND가 반환") {
                val player = Player(PlayerName("kim"), { Action.HIT }, Hand(score30Cards))

                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
            it("STAND를 받으면 STAND가 반환") {
                val player = Player(PlayerName("kim"), { Action.STAND }, Hand(score30Cards))

                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
        }

        context("최대 점수 21을 넘지 않았을 때") {
            val score5Cards = mutableListOf(
                Card(Suit.HEART, Rank.TWO),
                Card(Suit.HEART, Rank.THREE),
            )
            it("HIT을 받으면 HIT이 반한") {
                val player = Player(PlayerName("kim"), { Action.HIT }, Hand(score5Cards))

                val result = player.hitOrStand()

                result shouldBe Action.HIT
            }
            it("STAND를 받으면 STAND가 반환") {
                val player = Player(PlayerName("kim"), { Action.STAND }, Hand(score5Cards))

                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
        }
    }
})
