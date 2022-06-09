package blackjack.domain.player

import blackjack.domain.card.Ace
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Jack
import blackjack.domain.card.NumberCard
import blackjack.domain.card.Queen
import blackjack.domain.card.Suit
import blackjack.domain.score.Match
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({

    describe("isEnd") {
        context("딜러는 카드 합계가 17 이상이면") {
            it("플레이를 종료한다") {
                val dealer = Dealer(
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(7)),
                        )
                    )
                )

                dealer.isEnd shouldBe true
            }
        }

        context("딜러는 카드 합계가 16 아하이면") {
            it("플레이를 종료하지 않는다") {
                val dealer = Dealer(
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(6)),
                        )
                    )
                )

                dealer.isEnd shouldBe false
            }
        }
    }

    describe("giveCard") {
        context("플레이 중인 참가자의 경우") {
            it("딜러는 플레이어에게 카드를 나누어줄 수 있다") {
                val dealer = Dealer()
                val player = Player("yohan")

                dealer.giveCard(player)

                player.cards.size shouldBe 1
            }
        }

        context("플레이가 종료된 참가자의 경우") {
            it("IllegalArgumentException 이 발생한다") {
                val dealer = Dealer()
                val player = Player("yohan", playerStatus = PlayerStatus.STAY)

                shouldThrow<IllegalArgumentException> {
                    dealer.giveCard(player)
                }
            }
        }
    }

    describe("addCard") {
        context("처음에 받은 2장의 합계가 16이하이면") {
            it("카드를 추가한다") {
                val dealer = Dealer(
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(5)),
                        )
                    )
                )

                dealer.addCard()

                dealer.cards.size shouldBe 3
            }
        }

        context("딜러는 처음에 받은 2장의 합계가 17이상이면") {
            it("카드를 추가할 수 없다") {
                val dealer = Dealer(
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(7)),
                        )
                    )
                )

                shouldThrow<IllegalStateException> {
                    dealer.addCard()
                }
            }
        }
    }

    describe("match") {
        context("딜러가 21을 초과할 때") {
            val dealer = Dealer(
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, Jack()),
                        Card(Suit.DIAMOND, NumberCard(2)),
                    )
                )
            )

            it("남아 있던 플레이어의 가지고 있는 패에 상관 없이 패배한다") {
                val player = Player("yohan")

                dealer.match(player) shouldBe Match.LOSE
            }

            it("이미 21을 초과한 플레이어들에게 승리한다") {
                val player = Player(
                    name = "yohan",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, Jack()),
                            Card(Suit.DIAMOND, NumberCard(2)),
                        )
                    )
                )

                dealer.match(player) shouldBe Match.WIN
            }
        }

        context("딜러가 블랙잭일 때") {
            val dealer = Dealer(
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, Ace()),
                    )
                )
            )

            it("플레이어도 블랙잭이면 무승부다") {
                val player = Player(
                    name = "yohan",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, Ace()),
                        )
                    )
                )

                dealer.match(player) shouldBe Match.DRAW
            }

            it("플레이어가 블래잭이 아니면 승리한다") {
                val player = Player(
                    name = "yohan",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Jack()),
                            Card(Suit.DIAMOND, NumberCard(9)),
                            Card(Suit.DIAMOND, NumberCard(2)),
                        )
                    )
                )

                dealer.match(player) shouldBe Match.WIN
            }
        }

        context("딜러가 BUST, BLACKJACK 이 아닐 때 ") {
            it("플레이어도 버스트나 블랙잭이 아니면서 스코어가 높으면 승리한다") {
                val dealer = Dealer(
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, Jack()),
                        )
                    )
                )

                val player = Player(
                    name = "yohan",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(9)),
                        )
                    )
                )

                dealer.match(player) shouldBe Match.WIN
            }

            it("플레이어가 블랙잭이면 패배한다") {
                val dealer = Dealer(
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(9)),
                            Card(Suit.DIAMOND, NumberCard(2)),
                        )
                    )
                )

                val player = Player(
                    name = "yohan",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, Ace()),
                        )
                    )
                )

                dealer.match(player) shouldBe Match.LOSE
            }

            it("플레이어가 버스트이면 승리한다") {
                val dealer = Dealer(
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, Jack()),
                        )
                    )
                )

                val player = Player(
                    name = "yohan",
                    cards = Cards(
                        listOf(
                            Card(Suit.DIAMOND, Queen()),
                            Card(Suit.DIAMOND, NumberCard(9)),
                            Card(Suit.DIAMOND, NumberCard(9)),
                        )
                    )
                )

                dealer.match(player) shouldBe Match.WIN
            }
        }
    }
})
