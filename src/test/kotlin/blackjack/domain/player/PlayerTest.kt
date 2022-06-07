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

    describe("hit") {
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
                    )
                )

                player.hit(Card(Suit.DIAMOND, NumberCard(10)))

                player.cards.cards shouldBe
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
                    )
                )

                shouldThrow<IllegalStateException> {
                    player.hit(Card(Suit.DIAMOND, NumberCard(2)))
                }
            }
        }

        context("카드를 받지 않기로 한 경우") {
            it("IllegalStateException 이 발생한다") {
                val player = Player(
                    name = "요한",
                    stay = true
                )

                shouldThrow<IllegalStateException> {
                    player.hit(Card(Suit.DIAMOND, NumberCard(2)))
                }
            }
        }
    }

    describe("stay") {
        it("카드들을 받지 않기로 할 수 있다") {
            val player = Player(name = "요한")

            player.stay()

            player.stay shouldBe true
        }
    }

    describe("hittable") {
        context("카드들의 합이 21을 초과하지 않고 카드를 추가하지 않기로 하지 않았다면") {
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
                    stay = false
                )

                player.hittable() shouldBe true
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
                        stay = false
                    )

                    player.hittable() shouldBe false
                }
            }

            context("카드를 추가하지 않기로 하였다면") {
                it("카드를 추가할 수 없다") {
                    val player = Player(
                        name = "요한",
                        cards = Cards.empty(),
                        stay = true
                    )

                    player.hittable() shouldBe false
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

            player.score() shouldBe Score(16)
        }
    }
})
