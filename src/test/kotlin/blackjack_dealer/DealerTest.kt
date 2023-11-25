package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import blackjack_dealer.entity.toGamerCards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    fun removeCustomCard(cards: List<Card>): CardDeque {
        var createCardDeque = CardNumber.values().flatMap { number ->
            CardShape.values().map { shape ->
                Card(
                    cardNumber = number,
                    cardShape = shape
                )
            }
        }.shuffled()

        cards.forEach { removeCard -> createCardDeque = createCardDeque.filterNot { removeCard == it } }

        return CardDeque(ArrayDeque(createCardDeque))
    }
    Given("딜러가 생성되면") {
        val customCards =
            mutableListOf(Card(CardNumber.J, CardShape.HEART), Card(CardNumber.J, CardShape.CLOVER)).toGamerCards()

        val dealer = Dealer.newInstance(cards = customCards)
        Then("이름은 딜러이다") {
            val expected = "딜러"
            dealer.getGamerName() shouldBe expected
        }

        When("카드 두장을 받고 받은 두장의 합계가 16 이하면") {
            val customCardsLessThanSixteen =
                mutableListOf(Card(CardNumber.J, CardShape.HEART), Card(CardNumber.FIVE, CardShape.CLOVER)).toGamerCards()
            val cardDequeLessThanSixteen = removeCustomCard(customCardsLessThanSixteen)

            val lessThanScoreExpected = 16
            val cardCountExpected = 3
            val dealerLessThanSixteen = Dealer.newInstance(customCardsLessThanSixteen)

            Then("1장을 더 받는다 (16이하인지도 체크)") {
                dealerLessThanSixteen.getCurrentCards().getCurrentScore() shouldBeLessThanOrEqual lessThanScoreExpected

                dealerLessThanSixteen.drawCard(cardDequeLessThanSixteen)
                dealerLessThanSixteen.getCurrentCards().count() shouldBe cardCountExpected
            }
        }

        When("카드 두장을 받고 받은 두장의 합계가 17 이상이면 추가로 받을 수 없다") {
            val customCardsGreaterThanSixteen =
                mutableListOf(Card(CardNumber.J, CardShape.HEART), Card(CardNumber.SEVEN, CardShape.CLOVER)).toGamerCards()
            removeCustomCard(customCardsGreaterThanSixteen)

            val moreThanScoreExpected = 17
            val cardCountExpected = 2
            val dealerGreaterThanSixteen = Dealer.newInstance(customCardsGreaterThanSixteen)

            Then("추가로 받을 수 없다 (17 이상인지도 체크)") {
                dealerGreaterThanSixteen.getCurrentCards()
                    .getCurrentScore() shouldBeGreaterThanOrEqual moreThanScoreExpected
                dealerGreaterThanSixteen.getCurrentCards().count() shouldBe cardCountExpected
            }
        }
    }
})
