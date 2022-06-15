package blackjack.domain.player

import blackjack.domain.card.card
import blackjack.domain.card.cards
import blackjack.domain.common.Money
import blackjack.domain.score.Match
import blackjack.domain.score.Score
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
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

    describe("changeStatus") {
        context("STAY 또는 BUST가 아닌 경우") {
            it("카드를 추가하기로 할 수 있다") {
                val player = Player(name = "요한")
                player.changeStatus(PlayerStatus.HIT)
            }

            it("카드를 추가하지 않기로 할 수 있다") {
                val player = Player(name = "요한")
                player.changeStatus(PlayerStatus.STAY)
            }
        }

        context("BUST 인 경우") {
            val player = Player(
                name = "요한",
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to 10 }
                    card { "다이아몬드" to 2 }
                }
            )

            it("카드를 추가하기로 하면 IllegalStateException 이 발생한다") {
                shouldThrow<IllegalStateException> {
                    player.changeStatus(PlayerStatus.HIT)
                }
            }

            it("카드를 추가히지 않기로 하면 IllegalStateException 이 발생한다") {
                shouldThrow<IllegalStateException> {
                    player.changeStatus(PlayerStatus.STAY)
                }
            }
        }
    }

    describe("addCard") {
        context("STAY 또는 BUST 가 아닌 경우") {
            it("카드를 추가할 수 있다") {
                val player = Player(
                    name = "요한",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 9 }
                        card { "다이아몬드" to 2 }
                    }
                )

                player.addCard(card { "다이아몬드" to 10 })

                player.cards.cards shouldBe
                    listOf(
                        card { "다이아몬드" to "Q" },
                        card { "다이아몬드" to 9 },
                        card { "다이아몬드" to 2 },
                        card { "다이아몬드" to 10 },
                    )
            }
        }

        context("BUST 인 경우") {
            it("IllegalStateException 이 발생한다") {
                val player = Player(
                    name = "요한",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 10 }
                        card { "다이아몬드" to 2 }
                    }
                )

                shouldThrow<IllegalStateException> {
                    player.addCard(card { "다이아몬드" to 2 })
                }
            }
        }

        context("STAY 인 경우") {
            it("IllegalStateException 이 발생한다") {
                val player = Player(
                    name = "요한",
                    playerStatus = PlayerStatus.STAY
                )

                shouldThrow<IllegalStateException> {
                    player.addCard(card { "다이아몬드" to 2 })
                }
            }
        }
    }

    describe("score") {
        it("카드들의 점수의 합을 구할 수 있다") {
            val player = Player(
                name = "요한",
                cards = cards {
                    card { "다이아몬드" to "A" }
                    card { "다이아몬드" to "J" }
                    card { "다이아몬드" to 5 }
                }
            )

            player.cards.score() shouldBe Score(16)
        }
    }

    describe("isEnd") {
        it("BUST 이거나 STAY 가 아니면 참여가 종료되지 않는다") {
            val player = Player(name = "yohan")

            player.isEnd() shouldBe false
        }

        it("STAY 이면 참가자의 참여가 종료된다") {
            val player = Player(name = "yohan", playerStatus = PlayerStatus.STAY)

            player.isEnd() shouldBe true
        }

        it("BUST 이면 참가자의 참여가 종료된다") {
            val player = Player(
                name = "yohan",
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to 9 }
                    card { "다이아몬드" to 3 }
                }
            )

            player.isEnd() shouldBe true
        }
    }

    describe("profit") {
        context("게임에 이겼을 때 블랙잭이면") {
            it("배팅금액의 1.5배의 수익을 가진다") {
                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to "A" }
                    },
                    playerStatus = PlayerStatus.STAY,
                    batting = Money.of(1000)
                )

                val profit = player.profit(Match.WIN)

                profit shouldBe Money.of(1500)
            }
        }

        context("게임에 이겼을 때 블랙잭이 아니면") {
            it("배팅금액의 1배의 수익을 가진다") {
                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 9 }
                        card { "다이아몬드" to 2 }
                    },
                    playerStatus = PlayerStatus.STAY,
                    batting = Money.of(1000)
                )

                val profit = player.profit(Match.WIN)

                profit shouldBe Money.of(1000)
            }
        }

        context("게임에 비겼을 때") {
            it("배팅금액의 0배의 수익을 가진다") {
                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 9 }
                        card { "다이아몬드" to 2 }
                    },
                    playerStatus = PlayerStatus.STAY,
                    batting = Money.of(1000)
                )

                val profit = player.profit(Match.DRAW)

                profit shouldBe Money.ZERO
            }
        }

        context("게임에서 패배했을 때 ") {
            it("배팅금액의 -1배의 수익을 가진다") {
                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 9 }
                        card { "다이아몬드" to 2 }
                    },
                    playerStatus = PlayerStatus.STAY,
                    batting = Money.of(1000)
                )

                val profit = player.profit(Match.LOSE)

                profit shouldBe Money.of(-1000)
            }
        }
    }
})
