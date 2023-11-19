package blackjack.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class ResultDealerTest : BehaviorSpec({
    Given("A, K, 2인 카드덱이 주어질 때") {
        val cardList =
            listOf(Card("A", CardValue.from("A")), Card("K", CardValue.from("K")), Card("2", CardValue.from("2")))
        val cardDeck = CardDeck(cardList)
        When("점수를 구하면") {
            val score = ResultDealer.getTotalScore(cardDeck)
            Then("23이다") {
                score shouldBe 23
            }
        }
    }
})
