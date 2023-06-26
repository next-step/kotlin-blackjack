package blackjack.service

import blackjack.common.domain.PokerSymbol
import blackjack.common.service.DeckManager
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

internal class DeckManagerTest : StringSpec({
    "덱에서 열장을 뽑는다." {
        val deckManager = DeckManager()
        val cards = List(10) { deckManager.draw() }
        val pokerSymbols = PokerSymbol.values().toList()

        cards.shouldNotBeNull()
        cards.forEach {
            it.symbol shouldBeOneOf pokerSymbols
            it.value shouldBeInRange (1..11)
        }
    }

    "덱이 소진되면 에러를 내보낸다." {
        val deckManager = DeckManager()
        val cards = List(52) { deckManager.draw() }
        val pokerSymbols = PokerSymbol.values().toList()

        cards.shouldNotBeNull()
        cards.forEach {
            it.symbol shouldBeOneOf pokerSymbols
            it.value shouldBeInRange (1..11)
        }
        shouldThrow<IllegalStateException> {
            deckManager.draw()
        }.message shouldBe "모든 덱이 소진되었습니다."
    }
})
