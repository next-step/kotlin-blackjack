package blackjack.domain

import blackjack.domain.CardDeck
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class CardDeckTest : StringSpec({

    "카드를 52개를 초과하여 deal하면 IllegalStateException이 발생한다." {
        shouldThrow<IllegalStateException> { CardDeck().deal(53) }
            .shouldHaveMessage("더 이상 카드가 없습니다.")
    }
})
