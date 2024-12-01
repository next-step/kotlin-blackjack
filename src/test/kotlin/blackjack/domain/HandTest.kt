package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class HandTest : BehaviorSpec({
    fun createCard(cards: List<String>): List<Card> {
        return cards.map {
            Card(it, Suit.SPADE)
        }
    }

    Given("`Card`를 추가할 수 있다") {
        listOf(
            createCard(listOf("A", "A")),
            createCard(listOf("2", "3")),
            createCard(listOf("9", "Q")),
        ).forEach { cards ->
            When("카드 ${cards.joinToString { "${it.rank} ${it.suit.displayName}" }} 일때") {
                val hand = Hand()
                hand.add(cards)

                Then("카드가 추가되어야 됨") {
                    hand.totalCards shouldContainAll cards.toList()
                }
            }
        }
    }

    Given("`Score`를 계산한다") {
        listOf(
            Hand(createCard(listOf("A", "A")).toMutableList()) to 12,
            Hand(createCard(listOf("K", "A", "A")).toMutableList()) to 22,
            Hand(createCard(listOf("A", "K")).toMutableList()) to 21,
            Hand(createCard(listOf("2", "3")).toMutableList()) to 5,
            Hand(createCard(listOf("9", "Q")).toMutableList()) to 19,
        ).forEach { (cards, expected) ->
            When("카드 ${cards.totalCards.joinToString { "${it.rank} ${it.suit.displayName}" }} 일때") {
                Then("점수는 $expected 이어야 함") {
                    cards.score shouldBe expected
                }
            }
        }
    }

    Given("가지고 있는 `Card`를 알 수 있다") {
        val cards = createCard(listOf("A", "K"))
        val hand = Hand(cards.toMutableList())

        hand.totalCards shouldBe cards
    }

    Given("`Bust` 상태를 알 수 있다") {
        listOf(
            Hand(createCard(listOf("A", "A", "K")).toMutableList()) to true,
            Hand(createCard(listOf("2", "3")).toMutableList()) to false,
            Hand(createCard(listOf("9", "Q")).toMutableList()) to false,
        ).forEach { (cards, expected) ->
            When("카드 ${cards.totalCards.joinToString { "${it.rank} ${it.suit.displayName}" }} 일때") {
                Then("Bust 상태는 $expected 이어야 함") {
                    cards.isBust shouldBe expected
                }
            }
        }
    }
})
