package blackjack.domain.deck

import blackjack.domain.deck.CardPattern.CLOVER
import blackjack.domain.deck.CardPattern.DIAMOND
import blackjack.domain.deck.CardPattern.HEART
import blackjack.domain.deck.CardPattern.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
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

    "덱에서 카드를 뽑으면 카드 수가 줄어든다." - {
        listOf(
            row(1, 51),
            row(3, 49),
            row(51, 1),
            row(52, 0),
        ).forEach { (drawCount, leftCardCount) ->
            "$drawCount 장을 뽑으면 $leftCardCount 장이 남는다." {
                val deck = Deck.release()
                repeat(times = drawCount) { deck.drawCard() }

                deck.cards.shouldHaveSize(leftCardCount)
            }
        }
    }

    "덱에 남은 카드가 없으면 예외가 발생한다." {
        val deck = Deck.release()
        repeat(times = 52) { deck.drawCard() }

        val exception = shouldThrowExactly<IllegalStateException> { deck.drawCard() }
        exception.message shouldBe "덱에 남은 카드가 없습니다."
    }
})
