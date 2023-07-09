package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "덱 생성시 카드의 총 개수는 52개이다." {
        val deck = Deck.create()
        deck.cards.cards.size shouldBe 52
    }

    "덱 생성시 카드의 인덱스는 52가 넘을 수 없다." {
        shouldThrow<IllegalArgumentException> {
            val deck = Deck.create()
            deck.pop(53)
        }.message shouldBe "카드의 인덱스는 52가 넘을 수 없습니다."
    }
})
