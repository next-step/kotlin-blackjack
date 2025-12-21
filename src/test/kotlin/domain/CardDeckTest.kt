package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import model.CardDeck

class CardDeckTest : FreeSpec({

    "카드 댁 초기화시 52장이 세팅된다." {
        val cardDeck = CardDeck()
        repeat(52) {
            cardDeck.drawCard()
        }

        val exception = shouldThrow<IllegalStateException> { cardDeck.drawCard() }

        exception.message shouldBe "덱에 카드가 없습니다"
    }
})
