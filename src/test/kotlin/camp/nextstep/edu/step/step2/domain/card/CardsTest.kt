package camp.nextstep.edu.step.step2.domain.card

import camp.nextstep.edu.step.step2.domain.card.type.CardNumbers
import camp.nextstep.edu.step.step2.domain.card.type.CardType
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertNotNull

@DisplayName("카드들은")
class CardsTest : BehaviorSpec({

    Given("카드들이 존재하고") {
        val cards = Cards(
            cards = listOf(Card(CardNumbers.ACE, CardType.CLUB))
        )

        When("카드들의 합을 계산하면") {
            val cardNumberSumResult = cards.sumCards()

            Then("카드들의 합이 반환된다.") {
                assertNotNull(cardNumberSumResult)
                cardNumberSumResult shouldBe 11
            }
        }

        When("새로운 카드가 추가되면") {
            val newCard = Card(CardNumbers.TWO, CardType.CLUB)
            cards.addCard(newCard)

            Then("새로운 카드가 추가된다.") {
                cards.getCards().size shouldBe 2
                cards.getCards()[1] shouldBe newCard
            }
        }

        When("만약 블랙잭(21)보다 카드 합이 작다면") {
            val isLessthenBlackJack = cards.isLessThanBlackJack()

            Then("True를 반환한다.") {
                isLessthenBlackJack shouldBe true
            }
        }

        When("만약 블랙잭(21)보다 카드 합이 크다면") {
            cards.addCard(Card(CardNumbers.TEN, CardType.CLUB))
            cards.addCard(Card(CardNumbers.TEN, CardType.CLUB))
            val isLessthenBlackJack = cards.isLessThanBlackJack()

            Then("False를 반환한다.") {
                isLessthenBlackJack shouldBe false
            }
        }
    }
})
