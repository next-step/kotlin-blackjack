package blackjack.domain.deck

import blackjack.domain.deck.CardPattern.CLOVER
import blackjack.domain.deck.CardPattern.DIAMOND
import blackjack.domain.deck.CardPattern.HEART
import blackjack.domain.deck.CardPattern.SPADE
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class DeckTest : FreeSpec({

    "덱 생성을 하면" - {
        val deck = Deck.release()

        "$CLOVER 카드가 13장 생성된다." {
            deck.cards.count { it.pattern == CLOVER } shouldBe 13
        }

        "$HEART 카드가 13장 생성된다." {
            deck.cards.count { it.pattern == HEART } shouldBe 13
        }

        "$DIAMOND 카드가 13장 생성된다." {
            deck.cards.count { it.pattern == DIAMOND } shouldBe 13
        }

        "$SPADE 카드가 13장 생성된다." {
            deck.cards.count { it.pattern == SPADE } shouldBe 13
        }

        "총 52 장의 카드가 생성된다." {
            deck.cards shouldHaveSize 52
        }
    }
})
