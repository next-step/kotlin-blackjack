package blackjack

import blackjack.domain.model.Card
import blackjack.domain.model.Cards
import blackjack.domain.model.Pattern
import blackjack.domain.model.Sign
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CardsTest: StringSpec({

    "숫자로만 이루어진 카드들은 숫자의 합이 점수이다." {
        val cards = Cards.from(setOf(
            Card.of(Pattern.CLOVER, Sign.TWO), Card.of(Pattern.CLOVER, Sign.THREE), Card.of(
                Pattern.CLOVER, Sign.SEVEN)))
        cards.sum(21).value shouldBe 12
    }

    "J, K, Q 카드는 10점으로 합산된다." {
        val cards = Cards.from(setOf(
            Card.of(Pattern.CLOVER, Sign.JACK), Card.of(Pattern.CLOVER, Sign.QUEEN), Card.of(
                Pattern.CLOVER, Sign.KING)))
        cards.sum(21).value shouldBe 30
    }

    "A 카드는 1점 또는 11점으로 합산 할 수 있다." {
        val cards1 = Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.QUEEN), Card.of(Pattern.CLOVER, Sign.KING), Card.of(Pattern.CLOVER, Sign.ACE)))
        cards1.sum(21).value shouldBe 21

        val cards2 = Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.QUEEN), Card.of(Pattern.CLOVER, Sign.ACE)))
        cards2.sum(21).value shouldBe 21
    }

    "카드들 생성시 [7클로버, 7클로버, 7클로버]를 입력할 경우 [7클로버]만 존재 해야 한다." {
        val cards = Cards.from(setOf(
            Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(
                Pattern.CLOVER, Sign.SEVEN)))
        cards.cards.size shouldBe 1
        cards.cards shouldContain Card.of(Pattern.CLOVER, Sign.SEVEN)
    }

    "카드들 생성시 [7클로버, 7스페이스, 7하트]를 입력할 경우 [7클로버, 7스페이스, 7하트]가 존재 해야 한다." {
        val cards = Cards.from(setOf(
            Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(Pattern.SPACE, Sign.SEVEN), Card.of(
                Pattern.HEART, Sign.SEVEN)))
        cards.cards.size shouldBe 3
        cards.cards shouldContainExactly setOf(Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(Pattern.SPACE, Sign.SEVEN), Card.of(Pattern.HEART, Sign.SEVEN))
    }

    "카드들 생성시 [3클로버, 9클로버, J클로버]를 입력할 경우 최소 값이 22가 나와야 한다." {
        val cards = Cards.from(setOf(
            Card.of(Pattern.CLOVER, Sign.THREE), Card.of(Pattern.CLOVER, Sign.NINE), Card.of(
                Pattern.CLOVER, Sign.JACK)))
        cards.sum(21).value shouldBe 22
    }

    "카드들 생성시 [3클로버, 9클로버, J클로버]를 입력할 경우 21에 근접하며 최대한 넘지않도록 값을 뽑을 경우 22가 나와야 한다." {
        val cards = Cards.from(setOf(
            Card.of(Pattern.CLOVER, Sign.THREE), Card.of(Pattern.CLOVER, Sign.NINE), Card.of(
                Pattern.CLOVER, Sign.JACK)))
        cards.sum(21).value shouldBe 22
    }

    "카드들 생성시 [3클로버, 9클로버, A클로버]를 입력할 경우 21에 근접하며 최대한 넘지않도록 값을 뽑을 경우 13이 나와야 한다." {
        val cards = Cards.from(setOf(
            Card.of(Pattern.CLOVER, Sign.THREE), Card.of(Pattern.CLOVER, Sign.NINE), Card.of(
                Pattern.CLOVER, Sign.ACE)))
        cards.sum(21).value shouldBe 13
    }

    "카드들 생성시 [A스페이스, A하트, A클로버, A다이아]를 입력할 경우 21에 근접하며 최대한 넘지않도록 값을 뽑을 경우 14가 나와야 한다." {
        val cards = Cards.from(setOf(
            Card.of(Pattern.SPACE, Sign.ACE), Card.of(Pattern.HEART, Sign.ACE), Card.of(Pattern.CLOVER, Sign.ACE), Card.of(
                Pattern.DIAMOND, Sign.ACE)))
        cards.sum(21).value shouldBe 14
    }
})
