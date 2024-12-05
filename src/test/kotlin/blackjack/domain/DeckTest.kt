package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DeckTest : BehaviorSpec({
    Given("`Card`를 가진다") {
        val cards =
            listOf(
                Card(CardRank.ACE, Suit.SPADE),
                Card(CardRank.TWO, Suit.SPADE),
            )

        When("Deck을 생성하면") {
            val deck = Deck(cards)

            Then("카드를 가진다") {
                deck.size() shouldBe 2
            }
        }
    }

    Given("`Card`를 하나씩 꺼낼 수 있다") {
        val cards =
            listOf(
                Card(CardRank.ACE, Suit.SPADE),
                Card(CardRank.TWO, Suit.SPADE),
            )
        val deck = Deck(cards = cards, shuffleIndex = ArrayDeque(listOf(0, 1)))

        When("카드를 하나 꺼내면") {
            val card = deck.pop()

            Then("카드를 반환한다") {
                card shouldBe Card(CardRank.ACE, Suit.SPADE)
            }

            Then("카드를 제거한다") {
                deck.size() shouldBe 1
            }
        }
    }

    Given("`Card`를 꺼낼때 카드가 없으면 예외 발생 한다") {
        val deck = Deck(listOf())

        When("카드를 꺼내면") {
            Then("예외가 발생한다") {
                runCatching { deck.pop() }.isFailure shouldBe true
            }
        }
    }

    Given("`Card`를 갯수만큼 꺼낼 수 있다") {
        val deck =
            Deck(
                listOf(
                    Card(CardRank.ACE, Suit.SPADE),
                    Card(CardRank.TWO, Suit.SPADE),
                ),
            )

        When("카드를 2개 꺼내면") {
            val popDeck = deck.popCards(2)

            Then("카드를 반환한다") {
                popDeck.size() shouldBe 2
            }

            Then("카드를 제거한다") {
                deck.size() shouldBe 0
            }
        }
    }
})
