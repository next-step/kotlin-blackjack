package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class DeckTest : BehaviorSpec({

    given("비어있는 카드 리스트가 있다") {
        val cardList = LinkedList<Card>()
        `when`("해당 리스트로 덱을 만들면") {
            then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Deck(cardList) }
            }
        }
    }

    given("비어있지 않은 카드 리스트가 있다") {
        val cardList = LinkedList(listOf(Card(Suit.SPADE, NumberCardNumber(NumberCardNumber.NUMBER_RANGE.first))))
        `when`("해당 리스트로 덱을 만들면") {
            then("생성이 된다") {
                shouldNotThrow<Throwable> { Deck(cardList) }
            }
        }
    }

    given("1장이 들어있는 덱이 있다") {
        val card = Card(Suit.SPADE, NumberCardNumber(NumberCardNumber.NUMBER_RANGE.first))
        val cardList = Deck(LinkedList(listOf(card)))

        `when`("덱에서 한장을 꺼내면") {
            then("카드가 꺼내진다") {
                cardList.getCard() shouldBe card
            }
        }

        `when`("덱에서 한장을 더 꺼내면") {
            then("에러가 던져진다") {
                shouldThrow<IllegalStateException> { cardList.getCard() }
            }
        }
    }
})
