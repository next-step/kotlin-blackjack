package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerCardsTest : StringSpec({
    "유저의 패에는 한장의 카드를 추가할 수 있다." {
        val playerCards = PlayerCards()

        playerCards.addCard(createAceCard())

        playerCards.getCards() shouldBe listOf(createAceCard())
    }

    "유저의 패는 현재 가진 패의 최대 합산을 반환한다." {
        val playerCards = PlayerCards()

        playerCards.addCard(createAceCard())
        playerCards.addCard(createBasicCard(CardNumber.FIVE, CardMark.HEART))

        playerCards.calculateCardsMaxSum() shouldBe 16
    }
})
