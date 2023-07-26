package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    val initCards = listOf(Card(CardShape.CLOVA, CardNumber.TWO), Card(CardShape.HEART, CardNumber.THREE))
    var edge = Player("edge", initCards)

    beforeEach {
        edge = Player("edge", initCards)
    }

    "player 생성 성공" {
        edge.name shouldBe "edge"
    }

    "player 카드 확인하기" {
        edge.cards shouldBe initCards
    }

    "player 카드 합산하기" {
        edge.cardSum() shouldBe 5
    }

    "player에 카드 추가하기" {
        val addCard = Card(CardShape.DIAMOND, CardNumber.JACK)
        edge.addCard(addCard)

        edge.cardSum() shouldBe 15
        val list = initCards.toMutableList()
        list.add(addCard)
        edge.cards shouldBe list
    }

    "합이 11보다 작으면 ace를 11로 계산하기" {
        edge.addCard(Card(CardShape.DIAMOND, CardNumber.ACE))

        edge.cardSum() shouldBe 16
    }

    "합이 11보다 크면 ace를 1로 계산하기" {
        edge.addCard(Card(CardShape.DIAMOND, CardNumber.ACE))
        edge.addCard(Card(CardShape.DIAMOND, CardNumber.JACK))

        edge.cardSum() shouldBe 16
    }
})
