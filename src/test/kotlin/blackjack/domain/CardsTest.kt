package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CardsTest : FreeSpec({
    val cards = Cards()
    "여러개의 카드를 추가할 수 있다" {
        cards.start(listOf(spade5, heartQ))
        cards.size() shouldBe 2
    }
    "한개의 카드를 추가할 수 있다" {
        cards.add(cloverA)
        cards.size() shouldBe 3
    }
    "카드를 새로 받을 때 갖고있는 카드가 게임 가능한지(<=21) 확인할 수 있다" {
        cards.underTheBlackJack(dia7) shouldBe false
    }
})
