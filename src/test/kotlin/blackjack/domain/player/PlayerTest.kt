package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.card.Ace
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Jack
import blackjack.domain.card.NumberCard
import blackjack.domain.card.Queen
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PlayerTest : DescribeSpec({

    describe("constructor") {
        context("이름이 주어지면") {
            it("게임 참가자를 생성한다") {
                Player("요한") shouldNotBe null
            }
        }
    }

    describe("initHandOut") {
        context("참가자가 카드를 받지 않은 초기 상태에 카드 목록이 주어지면") {
            it("카드를 추가할 수 있다") {
                val player = Player("yohan")
                val diamondQueen = Card(Suit.DIAMOND, Queen())
                val diamondNine = Card(Suit.DIAMOND, NumberCard(9))

                player.initHandOut(listOf(diamondQueen, diamondNine))

                player.cards shouldContainExactly listOf(diamondQueen, diamondNine)
            }
        }

        context("참가자 카드를 받지 않은 초기 상태가 아닐 때 카드를 추가하면") {
            it("IllegalStateException 이 발생한다") {
                val player = Player(name = "yohan", playerStatus = PlayerStatus.READY)

                shouldThrow<IllegalStateException> {
                    player.initHandOut(listOf(Card(Suit.DIAMOND, NumberCard(9))))
                }
            }
        }
    }

    describe("hit, stay") {
        context("READY 상태인 경우") {
            it("카드를 추가하기로 할 수 있다") {
                val player = Player(name = "요한", playerStatus = PlayerStatus.READY)
                player.hit()
            }

            it("카드를 추가하지 않기로 할 수 있다") {
                val player = Player(name = "요한", playerStatus = PlayerStatus.READY)
                player.stay()
            }
        }

        context("READY 상태가 아닌 경우") {
            val players = PlayerStatus.values()
                .filterNot { it == PlayerStatus.READY }
                .map { Player(name = "요한", playerStatus = it) }

            it("카드를 추가하기로 하면 IllegalStateException 이 발생한다") {
                players.forAll {
                    shouldThrow<IllegalStateException> {
                        it.hit()
                    }
                }
            }

            it("카드를 추가하지 않기로 하면 IllegalStateException 이 발생한다") {
                players.forAll {
                    shouldThrow<IllegalStateException> {
                        it.stay()
                    }
                }
            }
        }

        context("카드들의 합이 21을 넘을 때") {
            val player = Player(
                name = "요한",
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(10)),
                        Card(Suit.DIAMOND, NumberCard(2)),
                    )
                ),
                playerStatus = PlayerStatus.READY
            )

            it("카드를 추가하기로 하면 IllegalStateException 이 발생한다") {
                shouldThrow<IllegalStateException> {
                    player.hit()
                }
            }

            it("카드를 추가히지 않기로 하면 IllegalStateException 이 발생한다") {
                shouldThrow<IllegalStateException> {
                    player.stay()
                }
            }
        }
    }

    describe("handOut") {
        context("가지고 있는 카드들의 합이 21을 넘지 않으면") {
            it("카드를 추가할 수 있다") {
                val player = Player(
                    name = "요한",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(9)),
                            Card(Suit.DIAMOND, NumberCard(2)),
                        )
                    ),
                    playerStatus = PlayerStatus.HIT
                )

                player.handOut(Card(Suit.DIAMOND, NumberCard(10)))

                player.cards shouldBe
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(9)),
                        Card(Suit.DIAMOND, NumberCard(2)),
                        Card(Suit.DIAMOND, NumberCard(10)),
                    )
            }
        }

        context("카드들의 합이 21을 넘으면") {
            it("IllegalStateException 이 발생한다") {
                val player = Player(
                    name = "요한",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(10)),
                            Card(Suit.DIAMOND, NumberCard(2)),
                        )
                    ),
                    playerStatus = PlayerStatus.HIT
                )

                shouldThrow<IllegalStateException> {
                    player.handOut(Card(Suit.DIAMOND, NumberCard(2)))
                }
            }
        }

        context("카드를 받지 않기로 한 경우") {
            it("IllegalStateException 이 발생한다") {
                val player = Player(
                    name = "요한",
                    playerStatus = PlayerStatus.STAY
                )

                shouldThrow<IllegalStateException> {
                    player.handOut(Card(Suit.DIAMOND, NumberCard(2)))
                }
            }
        }
    }

    describe("hittable") {
        context("카드들의 합이 21을 초과하지 않고 HIT 상태라면") {
            it("카드를 추가할 수 있다") {
                val player = Player(
                    name = "요한",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(9)),
                            Card(Suit.DIAMOND, NumberCard(2)),
                        )
                    ),
                    playerStatus = PlayerStatus.HIT
                )

                player.hittable shouldBe true
            }

            context("카드들의 합이 21을 초과하면") {
                it("카드를 추가할 수 없다") {
                    val player = Player(
                        name = "요한",
                        cards = Cards(
                            listOf(
                                Card(Suit.DIAMOND, Queen()),
                                Card(Suit.DIAMOND, NumberCard(9)),
                                Card(Suit.DIAMOND, NumberCard(3)),
                            )
                        ),
                        playerStatus = PlayerStatus.READY
                    )

                    player.hittable shouldBe false
                }
            }

            context("카드를 추가하지 않기로 하였다면") {
                it("카드를 추가할 수 없다") {
                    val player = Player(
                        name = "요한",
                        cards = Cards.empty(),
                        playerStatus = PlayerStatus.STAY
                    )

                    player.hittable shouldBe false
                }
            }
        }
    }

    describe("score") {
        it("카드들의 점수의 합을 구할 수 있다") {
            val player = Player(
                name = "요한",
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Ace()),
                        Card(Suit.DIAMOND, Jack()),
                        Card(Suit.DIAMOND, NumberCard(5)),
                    )
                )
            )

            player.score shouldBe Score(16)
        }
    }
})
