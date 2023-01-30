package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러가 카드를 뽑을 수 있는지 확인한다" {
        val dealer = Dealer(OwnCards(0))
        dealer.ownCards.addCard(Card(CardNumber.KING))
        dealer.ownCards.addCard(Card(CardNumber.SIX))
        dealer.isDrawable shouldBe true
    }

    "딜러가 카드를 뽑을 수 없는지 확인한다" {
        val dealer = Dealer(OwnCards(0))
        dealer.ownCards.addCard(Card(CardNumber.KING))
        dealer.ownCards.addCard(Card(CardNumber.SEVEN))
        dealer.isDrawable shouldBe false
    }
})
