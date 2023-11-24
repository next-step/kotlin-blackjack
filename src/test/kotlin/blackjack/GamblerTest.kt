package blackjack

import blackjack.domain.model.Card
import blackjack.domain.model.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import blackjack.domain.model.Gambler
import blackjack.domain.model.Name
import blackjack.domain.model.Pattern
import blackjack.domain.model.Sign

class GamblerTest: StringSpec({
    "갬블러 생성시 카드를 가지고 있지 말아야 한다." {
        val gambler = Gambler.from(Name.from("aaa"))
        gambler.cards.cards.size shouldBe 0
    }

    "카드들의 점수 합이 21점 이상이면 겜블러는 더이상 카드를 뽑을 수 없다." {
        val gambler = Gambler.from(Name.from("aaa"), Cards.from(setOf(Card.of(Pattern.SPACE, Sign.SEVEN), Card.of(Pattern.HEART, Sign.SEVEN), Card.of(Pattern.CLOVER, Sign.SEVEN))))
        gambler.shouldDraw(21) shouldBe false
    }

    "카드들의 점수 합이 1 ~ 20점 이내면 겜블러는 카드를 뽑을 수 있다." {
        Sign.values().forEach {
            val gambler = Gambler.from(Name.from("aaa"), Cards.from(setOf(Card.of(Pattern.SPACE, Sign.NINE), Card.of(Pattern.HEART, it))))
            gambler.shouldDraw(21) shouldBe true
        }
    }
})
