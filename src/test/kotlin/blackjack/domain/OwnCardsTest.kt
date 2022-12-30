package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OwnCardsTest : StringSpec({

    "최초의 2개의 카드를 발급 받는다" {
        OwnCards(Draw.FIRST_DRAW_COUNT).cards.size shouldBe 2
    }

    "보유한 카드의 합을 반환한다1" {
        val ownCards = OwnCards()
        ownCards.addCard(Card(CardNumber.ACE, Pattern.SPADE))
        ownCards.addCard(Card(CardNumber.EIGHT, Pattern.CLOVER))
        ownCards.sumCardNumber() shouldBe 19
    }

    "보유한 카드의 합을 반환한다2" {
        val ownCards = OwnCards()
        ownCards.addCard(Card(CardNumber.EIGHT, Pattern.CLOVER))
        ownCards.addCard(Card(CardNumber.EIGHT, Pattern.DIAMOND))
        ownCards.addCard(Card(CardNumber.ACE, Pattern.SPADE))
        ownCards.sumCardNumber() shouldBe 17
    }

    "보유한 카드의 합을 반환한다3" {
        val ownCards = OwnCards()
        ownCards.addCard(Card(CardNumber.EIGHT, Pattern.CLOVER))
        ownCards.addCard(Card(CardNumber.ACE, Pattern.SPADE))
        ownCards.sumCardNumber() shouldBe 19
    }

    "보유한 카드의 합을 반환한다4" {
        val ownCards = OwnCards()
        ownCards.addCard(Card(CardNumber.ACE, Pattern.CLOVER))
        ownCards.addCard(Card(CardNumber.ACE, Pattern.DIAMOND))
        ownCards.addCard(Card(CardNumber.ACE, Pattern.SPADE))
        ownCards.addCard(Card(CardNumber.ACE, Pattern.HEART))
        ownCards.sumCardNumber() shouldBe 14
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
