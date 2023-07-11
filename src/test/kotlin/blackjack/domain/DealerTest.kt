package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.card.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "17점이상의 카드를 가진 딜러는 카드를 받지 않는다" {
        val cardsUnder17 = Cards(mutableListOf(Card(CardNumber.SIX, CardSymbol.DIAMOND), Card(CardNumber.JACK, CardSymbol.DIAMOND)))
        val cardsScore17 = Cards(mutableListOf(Card(CardNumber.SEVEN, CardSymbol.HEART), Card(CardNumber.JACK, CardSymbol.HEART)))
        val cardsOver17 = Cards(mutableListOf(Card(CardNumber.EIGHT, CardSymbol.SPADE), Card(CardNumber.JACK, CardSymbol.SPADE)))

        val firstDealer = Dealer("cardsUnder17", cardsUnder17)
        val scoreOfFirstDealer = firstDealer.getScore()

        val secondDealer = Dealer("cardsScore17", cardsScore17)
        val scoreOfSecondDealer = secondDealer.getScore()

        val thirdDealer = Dealer("cardsOver17", cardsOver17)
        val scoreOfThirdDealer = thirdDealer.getScore()

        val additionalCard = Card(CardNumber.ACE, CardSymbol.CLOVER)

        // when
        firstDealer.addCard(additionalCard)
        secondDealer.addCard(additionalCard)
        thirdDealer.addCard(additionalCard)

        // then
        firstDealer.getScore() shouldBeGreaterThan scoreOfFirstDealer
        secondDealer.getScore() shouldBe scoreOfSecondDealer
        thirdDealer.getScore() shouldBe scoreOfThirdDealer
    }
})
