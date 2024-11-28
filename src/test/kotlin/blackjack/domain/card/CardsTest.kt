package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "카드들은 숫자의 합을 반환할 수 있다." {
        val cards = Cards(
            mutableListOf(
                Card(shape = CardShape.Heart, number = CardNumber.Ace),
                Card(shape = CardShape.Heart, number = CardNumber.Two),
            )
        )

        cards.sum() shouldBe 3
    }

    "카드들은 새로운 카드를 추가할 수 있다." {
        val cards = Cards()
        val card = Card(shape = CardShape.Heart, number = CardNumber.Ace)
        cards.add(card)

        cards.getCards().size shouldBe 1
        cards.getCards()[0] shouldBe card
    }
})
