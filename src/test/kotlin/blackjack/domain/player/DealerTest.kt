package blackjack.domain.player

import blackjack.domain.card.cards
import blackjack.domain.common.Money
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({

    describe("isEnd") {
        context("딜러는 카드 합계가 17 이상이면") {
            it("플레이를 종료한다") {
                val dealer = Dealer(
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 7 }
                    }
                )

                dealer.isEnd() shouldBe true
            }
        }

        context("딜러는 카드 합계가 16 아하이면") {
            it("플레이를 종료하지 않는다") {
                val dealer = Dealer(
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 6 }
                    }
                )

                dealer.isEnd() shouldBe false
            }
        }
    }

    describe("play") {
        context("플레이 중인 참가자의 경우") {
            it("딜러는 플레이어에게 카드를 나누어줄 수 있다") {
                val dealer = Dealer()
                val player = Player("yohan")

                dealer.play(player)

                player.cards.cards.size shouldBe 1
            }
        }

        context("카드를 추가할 수 있는 참가자가 아니라면") {
            it("카드를 지급하지 않는다") {
                val dealer = Dealer()
                val target = Player("yohan", playerStatus = PlayerStatus.STAY)

                dealer.play(target)

                target.cards.cards.size shouldBe 0
            }
        }
    }

    describe("addBaseCard") {
        it("카드를 초기 숫자 만큼 추가한다") {
            val dealer = Dealer()

            dealer.addBaseCards(2)

            dealer.cards.cards.size shouldBe 2
        }
    }

    describe("addCard") {
        context("처음에 받은 2장의 합계가 16이하이면") {
            it("카드를 추가한다") {
                val dealer = Dealer(
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 6 }
                    }
                )

                dealer.addCard()

                dealer.cards.cards.size shouldBe 3
            }
        }

        context("딜러는 처음에 받은 2장의 합계가 17이상이면") {
            it("카드를 추가할 수 없다") {
                val dealer = Dealer(
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 7 }
                    }
                )

                dealer.addCard()

                dealer.cards.cards.size shouldBe 2
            }
        }
    }

    describe("match") {
        context("딜러가 21을 초과할 때") {
            val dealer = Dealer(
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to "J" }
                    card { "다이아몬드" to 2 }
                }

            )

            it("남아 있던 플레이어의 가지고 있는 패에 상관 없이 패배한다") {
                val player = Player("yohan")

                dealer.match(player) shouldBe Match.LOSE
            }

            it("이미 21을 초과한 플레이어들에게 승리한다") {
                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to "J" }
                        card { "다이아몬드" to 2 }
                    }
                )

                dealer.match(player) shouldBe Match.WIN
            }
        }

        context("딜러가 블랙잭일 때") {
            val dealer = Dealer(
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to "A" }
                }
            )

            it("플레이어도 블랙잭이면 무승부다") {
                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to "A" }
                    }
                )

                dealer.match(player) shouldBe Match.DRAW
            }

            it("플레이어가 블래잭이 아니면 승리한다") {
                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "J" }
                        card { "다이아몬드" to 9 }
                        card { "다이아몬드" to 2 }
                    }
                )

                dealer.match(player) shouldBe Match.WIN
            }
        }

        context("딜러가 BUST, BLACKJACK 이 아닐 때 ") {
            it("플레이어도 버스트나 블랙잭이 아니면서 스코어가 높으면 승리한다") {
                val dealer = Dealer(
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to "J" }
                    }
                )

                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 9 }
                    }
                )

                dealer.match(player) shouldBe Match.WIN
            }

            it("플레이어가 블랙잭이면 패배한다") {
                val dealer = Dealer(
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 9 }
                        card { "다이아몬드" to 2 }
                    }
                )

                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to "A" }
                    }
                )

                dealer.match(player) shouldBe Match.LOSE
            }

            it("플레이어가 버스트이면 승리한다") {
                val dealer = Dealer(
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to "J" }
                    }
                )

                val player = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 9 }
                        card { "다이아몬드" to 9 }
                    }
                )

                dealer.match(player) shouldBe Match.WIN
            }
        }
    }

    describe("profit") {
        context("참가자들이 주어지면") {
            it("딜러의 배팅 결과를 알 수 있다") {
                val dealer = Dealer(
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to "J" }
                    }
                )

                val yohan = Player(
                    name = "yohan",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to "A" }
                    },
                    playerStatus = PlayerStatus.STAY,
                    batting = Money.of(1000)
                )
                val pang = Player(
                    name = "pang",
                    cards = cards {
                        card { "다이아몬드" to "Q" }
                        card { "다이아몬드" to 9 }
                    },
                    playerStatus = PlayerStatus.STAY,
                    batting = Money.of(1000)
                )

                val profit =
                    dealer.profit(Players(listOf(yohan, pang)))

                assertSoftly {
                    profit shouldBe Money.of(-500)
                }
            }
        }
    }
})
