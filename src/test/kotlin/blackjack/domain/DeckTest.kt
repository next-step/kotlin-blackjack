package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DeckTest : BehaviorSpec({

    Given("비어있는 카드 리스트가 있다") {
        val cardList = emptyList<Card>()
        When("해당 리스트로 덱을 만들면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Deck(cardList) }
            }
        }
    }

    Given("비어있지 않은 카드 리스트가 있다") {
        val cardList = listOf(Card(Suit.SPADE, CardNumber.of(CardNumber.MIN_CARD_NUMBER)))
        When("해당 리스트로 덱을 만들면") {
            Then("생성이 된다") {
                shouldNotThrow<Throwable> { Deck(cardList) }
            }
        }
    }

    Given("1장이 들어있는 덱이 있다") {
        val card = Card(Suit.SPADE, CardNumber.of(CardNumber.MIN_CARD_NUMBER))
        val cardList = Deck(listOf(card))

        When("덱에서 한장을 꺼내면") {
            Then("카드가 꺼내진다") {
                cardList.drawCard() shouldBe card
                cardList.size shouldBe 0
            }
        }

        When("덱에서 한장을 더 꺼내면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalStateException> { cardList.drawCard() }
            }
        }

        When("덱에 한장을 추가하면") {
            cardList.addCard(card)
            Then("카드가 한장 늘어난다") {
                cardList.size shouldBe 1
            }
        }
    }

    Given("에이스가 없는 덱이 있다") {
        val cardList = listOf(Card(Suit.SPADE, CardNumber.JACK))
        val deck = Deck(cardList)
        When("덱에서 에이스를 찾으면") {
            Then("없다고 나온다") {
                deck.hasAce() shouldBe false
            }
        }
    }

    Given("에이스가 있는 덱이 있다") {
        val cardList = listOf(Card(Suit.SPADE, CardNumber.ACE))
        val deck = Deck(cardList)
        When("덱에서 에이스를 찾으면") {
            Then("있다고 나온다") {
                deck.hasAce() shouldBe true
            }
        }
    }
})
