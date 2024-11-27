package blackjack

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class DeckTest : DescribeSpec({
    describe("Deck 클래스는") {

        it("초기화 시 52장의 고유한 카드를 포함해야 한다") {
            val deck = Deck()

            deck.cards shouldHaveSize 52

            val expectedCards =
                Suit.entries.flatMap { suit ->
                    Rank.entries.map { rank -> Card(suit, rank) }
                }

            deck.cards.shouldContainExactlyInAnyOrder(expectedCards)
        }

        it("초기화 시 카드 순서가 무작위로 섞여야 한다") {
            val deck1 = Deck()
            val deck2 = Deck()

            deck1.cards shouldHaveSize 52
            deck2.cards shouldHaveSize 52

            // 두 덱의 순서가 다를 가능성이 높음을 확인
            deck1.cards shouldNotBe deck2.cards
        }

        it("초기화 시 중복된 카드가 없어야 한다") {
            val deck = Deck()
            deck.cards.distinct().size shouldBe 52
        }
    }
})
