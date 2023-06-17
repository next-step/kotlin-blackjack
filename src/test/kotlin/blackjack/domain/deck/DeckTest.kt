package blackjack.domain.deck

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.throwable.shouldHaveMessage

class DeckTest : StringSpec({

    "덱 생성 후 드로우를 할 수 있다." {
        val deck = Deck()

        deck.draw() shouldNotBe null

        val expect = 2

        deck.multiDraw(count = expect) shouldHaveSize expect
    }

    "덱이 비어있는 경우 드로우를 요청하면 덱이 비어있다는 에러를 반환한다." {
        val deck = Deck(cards = ArrayDeque())

        val exception = shouldThrow<IllegalStateException> {
            deck.draw()
        }

        exception shouldHaveMessage "덱이 비어있습니다."
    }
})
