package blackjack.domain.card

import blackjack.domain.Score
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardsTest : DescribeSpec({

    describe("constructor") {
        context("카드 목록이 주어지면") {
            it("카드들을 생성할 수 있다") {
                val cards = listOf(
                    Card(Suit.DIAMOND, NumberCard(4)),
                    Card(Suit.SPADE, Ace())
                )

                Cards(cards) shouldNotBe null
            }
        }
    }

    describe("add") {
        it("카드를 추가할 수 있다") {
            val cards = Cards(
                listOf(
                    Card(Suit.DIAMOND, NumberCard(4)),
                    Card(Suit.SPADE, Ace())
                )
            )

            cards.add(Card(Suit.HEART, NumberCard(7)))
            cards.add(Card(Suit.HEART, Queen()))

            cards.cards shouldContainExactly (
                listOf(
                    Card(Suit.DIAMOND, NumberCard(4)),
                    Card(Suit.SPADE, Ace()),
                    Card(Suit.HEART, NumberCard(7)),
                    Card(Suit.HEART, Queen())
                )
                )
        }
    }

    describe("count") {
        context("Ace 카드가 아닌 카드들이 주어졌을 때 ") {
            it("카드들의 카드 숫자를 합산할 수 있다") {
                val cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, NumberCard(4)),
                        Card(Suit.SPADE, Jack()),
                        Card(Suit.HEART, NumberCard(7)),
                        Card(Suit.HEART, Queen()),
                    )
                )

                cards.score() shouldBe Score(31)
            }
        }

        context("Ace 카드를 포함한 카드들이 주어졌을 때 ") {
            it("Ace 카드를 제외한 숫자가 11이 이상이면 1로 계산하여 더한다") {
                val cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, NumberCard(4)),
                        Card(Suit.HEART, NumberCard(7)),
                        Card(Suit.HEART, Ace())
                    )
                )

                cards.score() shouldBe Score(12)
            }

            it("Ace 카드를 제외한 숫자가 10이 이하이면 10으로 계산하여 더한다") {
                val cards = Cards(
                    listOf(
                        Card(Suit.HEART, Ace()),
                        Card(Suit.SPADE, Queen())
                    )
                )

                cards.score() shouldBe Score(21)
            }
        }

        context("Ace 카드만 여러 장 주어졌을 때") {
            it("카드들의 카드 숫자를 합산할 수 있다") {
                val cards = Cards(
                    listOf(
                        Card(Suit.HEART, Ace()),
                        Card(Suit.SPADE, Ace()),
                        Card(Suit.DIAMOND, Ace()),
                    )
                )

                cards.score() shouldBe Score(13)
            }
        }
    }
})
