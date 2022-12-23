package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OwnCardsTest : StringSpec({

    "최초의 2개의 카드를 발급 받는다" {
        OwnCards().cards.size shouldBe 2
    }

    "보유한 카드의 합을 반환한다" {
        val ownCards = OwnCards(
            listOf(
                Card(CardNumber.ACE, Pattern.CLOVER),
                Card(CardNumber.EIGHT, Pattern.CLOVER)
            ).toMutableList()
        )
        ownCards.sumCardNumber() shouldBe 19
    }

    "보유한 카드의 정보를 반환한다" {
        val ownCards = OwnCards(listOf(Card(CardNumber.ACE, Pattern.CLOVER)).toMutableList())
        ownCards.getCardInfos() shouldBe listOf("A클로버")
    }

    "카드가 추가 됨을 확인한다" {
        val ownCards = OwnCards(listOf(Card(CardNumber.ACE, Pattern.CLOVER)).toMutableList())
        ownCards.addCard(true)
        ownCards.cards.size shouldBe 2
    }

    "카드가 추가 되지 않음을 확인한다" {
        val ownCards = OwnCards(listOf(Card(CardNumber.ACE, Pattern.CLOVER)).toMutableList())
        ownCards.addCard(false)
        ownCards.cards.size shouldBe 1
    }
})
