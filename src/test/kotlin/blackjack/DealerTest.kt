package blackjack

import blackjack.domain.model.Card
import blackjack.domain.model.Cards
import blackjack.domain.model.Name
import blackjack.domain.model.Pattern
import blackjack.domain.model.Sign
import blackjack.domain.model.player.Dealer
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

fun Dealer(name: Name = Name.from("딜러"), cards: Cards = Cards.empty()): Dealer {
    return Dealer.of(name, cards)
}

class DealerTest : StringSpec({

    "[7클로버, 7하트]를 가지고 있을 경우 카드들의 점수의 합계가 16점 이하여서 카드를 더 받을 수 있다." {
        val dealer = Dealer(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.CLOVER, Sign.SEVEN),
                    Card.of(Pattern.HEART, Sign.SEVEN)
                )
            )
        )
        dealer.shouldDraw() shouldBe true
    }
    "[7클로버, J하트]를 가지고 있을 경우 카드들의 점수의 합계가 16점 초과여서 카드를 더 받을 수 없다." {
        val dealer = Dealer(
            cards = Cards.from(
                setOf(
                    Card.of(Pattern.CLOVER, Sign.SEVEN),
                    Card.of(Pattern.HEART, Sign.JACK)
                )
            )
        )
        dealer.shouldDraw() shouldBe false
    }
})
