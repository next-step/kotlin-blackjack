package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    context("플레이어는 카드가 21점을 초과하지 않으면 카드를 더 뽑을 수 있다.") {
        withData(
            Cards(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.TWO),
            ) to true,
            Cards(
                Card(Suit.SPADE, Denomination.TEN),
                Card(Suit.SPADE, Denomination.JACK),
            ) to true,
            Cards(
                Card(Suit.SPADE, Denomination.JACK),
                Card(Suit.SPADE, Denomination.NINE),
                Card(Suit.SPADE, Denomination.TWO),
            ) to false,
            Cards(
                Card(Suit.SPADE, Denomination.JACK),
                Card(Suit.SPADE, Denomination.QUEEN),
                Card(Suit.SPADE, Denomination.KING),
            ) to false,
        ) { (cards, expected) ->
            cards.canDrawMoreCard() shouldBe expected
        }
    }

    context("ACE를 11로 계산해서 21점이 되면 카드를 더 뽑을 수 없다.") {
        val cards = Cards(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.TEN),
        )
        cards.canDrawMoreCard() shouldBe false
    }
})
