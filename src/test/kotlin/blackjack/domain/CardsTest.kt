package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardsTest : BehaviorSpec({

    Given("1장이 들어있는 Cards가 있다") {
        val card = Card(Suit.SPADE, CardNumber.of(CardNumber.MIN_CARD_NUMBER))
        val cards = Cards(listOf(card))

        When("한장을 꺼내면") {
            Then("카드가 꺼내진다") {
                cards.drawCard() shouldBe card
                cards.size shouldBe 0
            }
        }

        When("한장을 더 꺼내면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalStateException> { cards.drawCard() }
            }
        }

        When("한장을 추가하면") {
            cards.addCard(card)
            Then("카드가 한장 늘어난다") {
                cards.size shouldBe 1
            }
        }
    }

    Given("2장의 카드를 가진 플레이어 패가 있다") {
        val cardList = listOf(Card(Suit.SPADE, CardNumber.JACK), Card(Suit.SPADE, CardNumber.ACE))
        val cards = Cards(cardList)
        When("점수를 계산하면") {
            Then("점수가 정상적으로 반환된다") {
                cards.score() shouldBe 21
            }
        }
    }

    Given("카드가 들어 있는 플레이어 패가 있다") {
        val cards = Cards(
            listOf(
                Card(Suit.SPADE, CardNumber.ACE), // 이때는 11
                Card(Suit.SPADE, CardNumber.NINE),
                Card(Suit.HEART, CardNumber.ACE), // 이때는 1
            ),
        )
        When("해당덱의 점수를 구하면") {
            Then("합이 반환된다") {
                cards.score() shouldBe 21
            }
        }
    }
})
