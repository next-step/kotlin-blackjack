package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe

class OwnCardsTest : FreeSpec({

    "최초의 2개의 카드를 발급 받는다" {
        OwnCards(Draw.FIRST_DRAW_COUNT).cards.size shouldBe 2
    }

    "보유한 카드의 합을 반환한다" {
        listOf(
            OwnCards().apply {
                this.addCard(Card(CardNumber.ACE, Pattern.SPADE))
                this.addCard(Card(CardNumber.EIGHT, Pattern.CLOVER))
            } to 19,
            OwnCards().apply {
                this.addCard(Card(CardNumber.EIGHT, Pattern.CLOVER))
                this.addCard(Card(CardNumber.EIGHT, Pattern.DIAMOND))
                this.addCard(Card(CardNumber.ACE, Pattern.SPADE))
            } to 17,
            OwnCards().apply {
                this.addCard(Card(CardNumber.EIGHT, Pattern.CLOVER))
                this.addCard(Card(CardNumber.ACE, Pattern.SPADE))
            } to 19,
            OwnCards().apply {
                this.addCard(Card(CardNumber.ACE, Pattern.CLOVER))
                this.addCard(Card(CardNumber.ACE, Pattern.DIAMOND))
                this.addCard(Card(CardNumber.ACE, Pattern.SPADE))
                this.addCard(Card(CardNumber.ACE, Pattern.HEART))
            } to 14
        ).forEach { (ownCards: OwnCards, sum: Int) ->
            ownCards.sumCardNumber() shouldBe sum
        }
    }


    "보유한 카드의 정보를 반환한다" {
        val ownCards = OwnCards(setOf(Card(CardNumber.ACE, Pattern.CLOVER)).toMutableSet())
        ownCards.cards.map { it.cardNumber.display + it.pattern.display } shouldBe listOf("A클로버")
    }

    "중복되어 카드가 발급되지 않는다" {
        val ownCards =
            OwnCards(setOf(Card(CardNumber.ACE, Pattern.CLOVER), Card(CardNumber.ACE, Pattern.CLOVER)).toMutableSet())
        ownCards.cards.size shouldBe 1
    }

    "카드가 추가 됨을 확인한다" {
        val card = Card(CardNumber.ACE, Pattern.CLOVER)
        val ownCards = OwnCards(setOf(card).toMutableSet())
        ownCards.addCard()
        ownCards.cards.size shouldBe 2
    }
})
