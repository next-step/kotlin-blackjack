package blackjack.domain.card

import blackjack.domain.card.Rank.ACE
import blackjack.fixtures.createCard
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DeckTest : BehaviorSpec({
    Given("덱에 카드가 존재하는 경우") {
        val deck = Deck(mutableListOf(createCard("A")))

        When("드로우하면") {
            Then("카드를 반환한다") {
                deck.draw().rank shouldBe ACE
            }
        }
    }
    Given("덱에 카드가 없는 경우") {
        val deck = Deck(mutableListOf())

        When("드로우하면") {
            Then("예외 발생한다") {
                shouldThrow<IllegalStateException> {
                    deck.draw()
                }
            }
        }
    }
})
