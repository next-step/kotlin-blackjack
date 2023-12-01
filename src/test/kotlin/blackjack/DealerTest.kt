package blackjack

import blackjack.domain.model.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "[7클로버, 7하트]를 가지고 있을 경우 카드를 더 받을 수 있다." {
        val dealer = Dealer.from(
            Name.from("딜러"),
            Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(Pattern.HEART, Sign.SEVEN)))
        )
        dealer.shouldDraw() shouldBe true
    }
    "[7클로버, J하트]를 가지고 있을 경우 카드를 더 받을 수 없다." {
        val dealer = Dealer.from(
            Name.from("딜러"),
            Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(Pattern.HEART, Sign.JACK)))
        )
        dealer.shouldDraw() shouldBe false
    }
    "[7클로버, J하트]를 가지고 있을 경우 버스트가 아니다." {
        val dealer = Dealer.from(
            Name.from("딜러"),
            Cards.from(setOf(Card.of(Pattern.CLOVER, Sign.SEVEN), Card.of(Pattern.HEART, Sign.JACK)))
        )
        dealer.isBust() shouldBe false
    }
    "[7클로버, J하트, 4스페이스]를 가지고 있을 경우 버스트다." {
        val dealer = Dealer.from(
            Name.from("딜러"),
            Cards.from(
                setOf(
                    Card.of(Pattern.CLOVER, Sign.SEVEN),
                    Card.of(Pattern.HEART, Sign.JACK),
                    Card.of(Pattern.SPACE, Sign.EIGHT)
                )
            )
        )
        dealer.isBust() shouldBe true
    }
})
