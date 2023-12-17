package game.blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "게임 덱은 총 52장이다." {
        val deck = Deck()

        deck.size shouldBe 52
    }

    "게임 덱의 각 카드는 서로 중복되지 않으면서 52장이다." {
        val deck = Deck()
        val allCards = List(52) { deck.drawOnce() }

        allCards.toSet().size shouldBe 52
    }

    "게임 시작 시 총 2장의 카드를 받는다." {
        val deck = Deck()
        val initialCards = deck.initialDraw()

        initialCards.size shouldBe 2
    }

    "게임 카드를 한장 뽑으면 덱의 총 카드 개수가 1개 감소한다." {
        val deck = Deck()
        val originSize = deck.size
        deck.drawOnce()

        originSize - 1 shouldBe deck.size
    }

    "게임 덱의 카드가 모두 소진됐을 때, 카드를 뽑으면 예외가 발생한다." {
        val deck = Deck()
        List(52) { deck.drawOnce() }

        shouldThrow<IllegalStateException> { deck.drawOnce() }
    }
})
