package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSymbol
import blackjack.domain.cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러가 hit 가능하다" {
        val cards = cards(Card(CardNumber.TEN, CardSymbol.CLUB), Card(CardNumber.SIX, CardSymbol.CLUB))
        val dealer = Dealer(cards)
        dealer.isEligibleToHit() shouldBe true
    }

    "딜러가 hit 불가능하다" {
        forAll(
            row(cards(Card(CardNumber.TEN, CardSymbol.CLUB), Card(CardNumber.SEVEN, CardSymbol.CLUB))),
            row(
                cards(
                    Card(CardNumber.TEN, CardSymbol.CLUB),
                    Card(CardNumber.TWO, CardSymbol.CLUB),
                    Card(CardNumber.THREE, CardSymbol.CLUB)
                )
            ),
        ) {
            val dealer = Dealer(it)
            dealer.isEligibleToHit() shouldBe false
        }
    }
})
