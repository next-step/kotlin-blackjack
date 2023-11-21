package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.CardNumber
import blackjack_dealer.entity.CardShape
import blackjack_dealer.entity.toGamerCards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    Given("딜러가 생성되면") {
        val cardDeque = CardDeque.create()
        cardDeque.cardDeque.removeIf { it == Card(CardNumber.J, CardShape.HEART) }
        cardDeque.cardDeque.removeIf { it == Card(CardNumber.FIVE, CardShape.CLOVER) }

        val customCards =
            listOf(Card(CardNumber.J, CardShape.HEART), Card(CardNumber.J, CardShape.CLOVER)).toGamerCards()

        val dealer = Dealer.newInstance(cards = customCards)
        Then("이름은 딜러이다") {
            val expected = "딜러"
            dealer.getDealerName() shouldBe expected
        }

        When("카드 두장을 받고 받은 두장의 합계가 16 이하면") {
            val cardDequeLessThanSixteen = CardDeque.create()
            cardDequeLessThanSixteen.cardDeque.removeIf { it == Card(CardNumber.J, CardShape.HEART) }
            cardDequeLessThanSixteen.cardDeque.removeIf { it == Card(CardNumber.FIVE, CardShape.CLOVER) }

            val customCardsLessThanSixteen =
                listOf(Card(CardNumber.J, CardShape.HEART), Card(CardNumber.FIVE, CardShape.CLOVER)).toGamerCards()
            val lessThanScoreExpected = 16
            val cardCountExpected = 3
            val dealerLessThanSixteen = Dealer.newInstance(customCardsLessThanSixteen)

            Then("1장을 더 받는다 (16이하인지도 체크)") {
                dealerLessThanSixteen.getCurrentCards().getCurrentScore() shouldBeLessThanOrEqual lessThanScoreExpected

                dealerLessThanSixteen.getOneMoreCardIfHit(cardDequeLessThanSixteen)
                dealerLessThanSixteen.getCurrentCards().count() shouldBe cardCountExpected
            }
        }

        When("카드 두장을 받고 받은 두장의 합계가 17 이상이면 추가로 받을 수 없다") {
            val cardDequeGreaterThanSixteen = CardDeque.create()
            cardDequeGreaterThanSixteen.cardDeque.removeIf { it == Card(CardNumber.J, CardShape.HEART) }
            cardDequeGreaterThanSixteen.cardDeque.removeIf { it == Card(CardNumber.SEVEN, CardShape.CLOVER) }

            val customCardsGreaterThanSixteen =
                listOf(Card(CardNumber.J, CardShape.HEART), Card(CardNumber.SEVEN, CardShape.CLOVER)).toGamerCards()
            val moreThanScoreExpected = 17
            val cardCountExpected = 2
            val dealerGreaterThanSixteen = Dealer.newInstance(customCardsGreaterThanSixteen)

            Then("추가로 받을 수 없다 (17 이상인지도 체크)") {
                dealerGreaterThanSixteen.getCurrentCards().getCurrentScore() shouldBeGreaterThanOrEqual moreThanScoreExpected
                dealerGreaterThanSixteen.getCurrentCards().count() shouldBe cardCountExpected
            }
        }
    }

})
