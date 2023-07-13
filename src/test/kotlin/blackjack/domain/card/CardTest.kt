package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 숫자 Ace ~ 9의 계산할때 1 ~ 9 로 계산된다." {
        listOf(
            NumberShape.ACE to 1,
            NumberShape.TWO to 2,
            NumberShape.THREE to 3,
            NumberShape.FOUR to 4,
            NumberShape.FIVE to 5,
            NumberShape.SIX to 6,
            NumberShape.SEVEN to 7,
            NumberShape.EIGHT to 8,
            NumberShape.NINE to 9
        ).forAll {
            val card = Card(it.first, Pattern.CLUB)
            card.getValue() shouldBe it.second
        }
    }

    "카드 K, Q , J 는 10으로 계산된다." {
        listOf(NumberShape.KING, NumberShape.QUEEN, NumberShape.JACK).forAll {
            val card = Card(it, Pattern.DIAMOND)
            card.getValue() shouldBe 10
        }
    }
})
