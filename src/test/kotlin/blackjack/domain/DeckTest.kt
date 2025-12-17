package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContain

class DeckTest : BehaviorSpec({

    Given("덱을 생성한다.") {
        val deck = Deck()

        Then("카드 수가 52장이다.") {
            deck.cards shouldHaveSize 52
        }

        When("pop을 했을 때") {
            val card = deck.pop()

            Then("한장을 꺼내오고 덱에서 그 카드를 제거한다.") {
                deck.cards shouldNotContain card
                deck.cards shouldHaveSize 51
            }
        }

        When("pop을 2번 했을 때") {
            val card = deck.pop()

            Then("2장을 꺼내오고 덱에서 그 카드 2장을 제거한다.") {
                deck.cards shouldNotContain card
                deck.cards shouldHaveSize 50
            }
        }
    }
})
