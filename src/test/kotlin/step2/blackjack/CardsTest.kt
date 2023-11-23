package step2.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import step2.blackjack.domain.model.*

class CardsTest: StringSpec({
    "카드들 생성시 [7클로버, 7클로버, 7클로버]를 입력할 경우 [7클로버]만 존재 해야 한다." {
        val cards = Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(Pattern.CLOVER, Sign.SEVEN)))
        cards.cards.size shouldBe 1
        cards.cards.contains(Card.of(Pattern.CLOVER, Sign.SEVEN)) shouldBe true
    }

    "카드들 생성시 [3클로버, 9클로버, J클로버]를 입력할 경우 최소 값이 22가 나와야 한다." {
        val cards = Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.THREE), Card.of(Pattern.CLOVER, Sign.NINE), Card.of(Pattern.CLOVER, Sign.JACK)))
        cards.min().toInt() shouldBe 22
    }

    "카드들 생성시 [3클로버, 9클로버, J클로버]를 입력할 경우 21에 근접하며 최대한 넘지않도록 값을 뽑을 경우 22가 나와야 한다." {
        val cards = Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.THREE), Card.of(Pattern.CLOVER, Sign.NINE), Card.of(Pattern.CLOVER, Sign.JACK)))
        cards.maxTarget(21).toInt() shouldBe 22
    }

    "카드들 생성시 [3클로버, 9클로버, A클로버]를 입력할 경우 21에 근접하며 최대한 넘지않도록 값을 뽑을 경우 13이 나와야 한다." {
        val cards = Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.THREE), Card.of(Pattern.CLOVER, Sign.NINE), Card.of(Pattern.CLOVER, Sign.ACE)))
        cards.maxTarget(21).toInt() shouldBe 13
    }

    "카드들 생성시 [A스페이스, A하트, A클로버, A다이아]를 입력할 경우 21에 근접하며 최대한 넘지않도록 값을 뽑을 경우 14가 나와야 한다." {
        val cards = Cards.from(setOf(Card.of(Pattern.SPACE, Sign.ACE), Card.of(Pattern.HEART, Sign.ACE), Card.of(Pattern.CLOVER, Sign.ACE), Card.of(Pattern.DIAMOND, Sign.ACE)))
        cards.maxTarget(21).toInt() shouldBe 14
    }
})
