package blackjack

import blackjack.domain.model.Card
import blackjack.domain.model.Cards
import blackjack.domain.model.Pattern
import blackjack.domain.model.Sign
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "카드들 생성시 [7클로버, 7클로버, 7클로버]를 입력할 경우 [7클로버]만 존재 해야 한다." {
        val cards = Cards.from(
            setOf(
                Card.of(Pattern.CLOVER, Sign.SEVEN),
                Card.of(Pattern.CLOVER, Sign.SEVEN),
                Card.of(Pattern.CLOVER, Sign.SEVEN)
            )
        )
        cards.values.size shouldBe 1
        cards.values shouldContain Card.of(Pattern.CLOVER, Sign.SEVEN)
    }

    "카드들 생성시 [7클로버, 7스페이스, 7하트]를 입력할 경우 [7클로버, 7스페이스, 7하트]가 존재 해야 한다." {
        val cards = Cards.from(
            setOf(
                Card.of(Pattern.CLOVER, Sign.SEVEN),
                Card.of(Pattern.SPACE, Sign.SEVEN),
                Card.of(Pattern.HEART, Sign.SEVEN)
            )
        )
        cards.values.size shouldBe 3
        cards.values shouldContainExactly setOf(
            Card.of(Pattern.CLOVER, Sign.SEVEN),
            Card.of(Pattern.SPACE, Sign.SEVEN),
            Card.of(Pattern.HEART, Sign.SEVEN)
        )
    }
})
