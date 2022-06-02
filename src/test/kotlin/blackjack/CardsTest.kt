package blackjack

import balckjack.Ace
import balckjack.Card
import balckjack.CardPattern
import balckjack.DoubleCount
import balckjack.Jack
import balckjack.NumberCard
import balckjack.Queen
import balckjack.SingleCount
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardsTest : DescribeSpec({

    describe("constructor") {
        context("카드 목록이 주어지면") {
            it("카드들을 생성할 수 있다") {
                val cards = listOf(
                    NumberCard(CardPattern.DIAMOND, 4),
                    Ace(CardPattern.SPADE)
                )

                Cards(cards) shouldNotBe null
            }
        }
    }

    describe("add") {
        it("카드를 추가할 수 있다") {
            val cards = Cards(
                listOf(
                    NumberCard(CardPattern.DIAMOND, 4),
                    Ace(CardPattern.SPADE)
                )
            )

            cards.add(NumberCard(CardPattern.HEART, 7))
            cards.add(Queen(CardPattern.HEART))

            cards.cards shouldContainExactly (
                listOf(
                    NumberCard(CardPattern.DIAMOND, 4),
                    Ace(CardPattern.SPADE),
                    NumberCard(CardPattern.HEART, 7),
                    Queen(CardPattern.HEART)
                )
                )
        }
    }

    describe("count") {
        context("Ace 카드가 아닌 카드들이 주어졌을 때 ") {
            it("카드들의 카드 숫자를 합산할 수 있다") {
                val cards = Cards(
                    listOf(
                        NumberCard(CardPattern.DIAMOND, 4),
                        Jack(CardPattern.SPADE),
                        NumberCard(CardPattern.HEART, 7),
                        Queen(CardPattern.HEART)
                    )
                )

                cards.count() shouldBe 31
            }
        }

        context("Ace 카드를 포함한 카드들이 주어졌을 때 ") {
            it("Ace 카드를 제외한 숫자가 11이 이상이면 1로 계산하여 더한다") {
                val cards = Cards(
                    listOf(
                        NumberCard(CardPattern.DIAMOND, 4),
                        NumberCard(CardPattern.HEART, 7),
                        Ace(CardPattern.HEART)
                    )
                )

                cards.count() shouldBe 12
            }

            it("Ace 카드를 제외한 숫자가 10이 이하이면 10으로 계산하여 더한다") {
                val cards = Cards(
                    listOf(
                        Ace(CardPattern.SPADE),
                        Queen(CardPattern.HEART)
                    )
                )

                cards.count() shouldBe 21
            }
        }

        context("Ace 카드만 여러 장 주어졌을 때") {
            it("카드들의 카드 숫자를 합산할 수 있다") {
                val cards = Cards(
                    listOf(
                        Ace(CardPattern.SPADE),
                        Ace(CardPattern.HEART),
                        Ace(CardPattern.DIAMOND),
                    )
                )

                cards.count() shouldBe 13
            }
        }
    }
})

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun count(): Int {
        return sumOfDoubleCount(sumOfSingleCount())
    }

    private fun sumOfSingleCount(): Int {
        return cards.filter { it.count() is SingleCount }
            .sumOf {
                val count = it.count() as SingleCount
                count.number
            }
    }

    private fun sumOfDoubleCount(base: Int): Int {
        var sum = base

        cards.filter { it.count() is DoubleCount }
            .forEach {
                val doubleCount = it.count() as DoubleCount
                sum = doubleCount.sum(sum, LIMIT)
            }

        return sum
    }

    companion object {
        private const val LIMIT = 21
    }
}
