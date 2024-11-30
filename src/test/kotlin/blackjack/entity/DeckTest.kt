package blackjack.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class DeckTest : DescribeSpec({
    describe("Deck은") {
        context("초기화할 때") {
            it("52장의 고유한 카드를 포함해야 한다") {
                val deck = Deck()

                deck.cards shouldHaveSize 52

                val expectedCards =
                    Suit.entries.flatMap { suit ->
                        Rank.entries.map { rank -> Card(suit, rank) }
                    }

                deck.cards.shouldContainExactlyInAnyOrder(expectedCards)
            }

            it("카드 순서가 무작위로 섞여야 한다") {
                val deck1 = Deck()
                val deck2 = Deck()

                deck1.cards shouldHaveSize 52
                deck2.cards shouldHaveSize 52

                deck1.cards shouldNotBe deck2.cards
            }

            it("중복된 카드가 없어야 한다") {
                val deck = Deck()
                deck.cards.distinct().size shouldBe 52
            }
        }

        context("카드를 분배할 때") {
            val deck = Deck()

            it("남은 카드 수가 줄어들어야 한다") {
                val initialSize = deck.cards.size
                deck.deal()
                deck.remainCardCount() shouldBe initialSize - 1
            }
        }

        context("모든 카드를 분배한 후") {
            val deck = Deck()

            it("더 이상 분배할 수 없어야 한다") {
                repeat(52) { deck.deal() }
                shouldThrow<IllegalArgumentException> { deck.deal() }
            }
        }
    }
})
