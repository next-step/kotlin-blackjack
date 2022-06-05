package blackjack.domain.card

import blackjack.domain.card.type.Ace
import blackjack.domain.card.type.Suit
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec

class CardTest : StringSpec({
    "카드 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Card(Ace(), Suit.CLUB) }
    }
})
