package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "카드들은 숫자의 합을 반환할 수 있다." {
        val cards =
            Cards(
                mutableListOf(
                    Card(shape = CardShape.Heart, number = CardNumber.Three),
                    Card(shape = CardShape.Heart, number = CardNumber.Two),
                ),
            )

        cards.getScore() shouldBe 5
    }

    "카드들은 새로운 카드를 추가할 수 있다." {
        val cards = Cards()
        val card = Card(shape = CardShape.Heart, number = CardNumber.Ace)
        cards.add(card)

        cards.getCards().size shouldBe 1
        cards.getCards()[0] shouldBe card
    }

    "카드들의 숫자합은 카드들에 에이스가 포함되어있으면 21을 넘지 않는 선에서 대체값(11)을 합산한 값을 반환한다." {
        val cards =
            Cards(
                mutableListOf(
                    Card(shape = CardShape.Heart, number = CardNumber.Ten),
                    Card(shape = CardShape.Spade, number = CardNumber.Ace),
                ),
            )

        cards.getScore() shouldBe 21
    }
})
