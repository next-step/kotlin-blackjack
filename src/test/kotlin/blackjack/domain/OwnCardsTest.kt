package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OwnCardsTest : StringSpec({

    "최초의 2개의 카드를 발급 받는다" {
        OwnCards().cards.size shouldBe 2
    }

    "보유한 카드의 합을 반환한다" {
        val ownCards =
            OwnCards(setOf(Card(CardNumber.ACE, Pattern.CLOVER), Card(CardNumber.EIGHT, Pattern.CLOVER)).toMutableSet())
        ownCards.sumCardNumber() shouldBe 19
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
        val ownCards = OwnCards(setOf(Card(CardNumber.ACE, Pattern.CLOVER)).toMutableSet())
        ownCards.addCard(true)
        ownCards.cards.size shouldBe 2
    }

    "카드가 추가 되지 않음을 확인한다" {
        val ownCards = OwnCards(setOf(Card(CardNumber.ACE, Pattern.CLOVER)).toMutableSet())
        ownCards.addCard(false)
        ownCards.cards.size shouldBe 1
    }
})
