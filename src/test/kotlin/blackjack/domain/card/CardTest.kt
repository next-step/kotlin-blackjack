package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 모양과 숫자 정보를 갖는다." {
        val card = Card(shape = CardShape.Heart, number = CardNumber.Ace)

        card.shape shouldBe CardShape.Heart
        card.number shouldBe CardNumber.Ace
    }
})
