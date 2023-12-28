package game.blackjack.v2.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "게임 덱은 총 52장이다." {
        Deck.size shouldBe 52
    }

    "게임 시작 시 총 2장의 카드를 받는다." {
        val initialCards = Deck.initialDraw()

        initialCards.size shouldBe 2
    }

    "게임 카드를 한장 뽑으면 덱의 총 카드 개수가 1개 감소한다." {
        val originSize = Deck.size
        Deck.drawOnce()

        originSize - 1 shouldBe Deck.size
    }
})
