package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 숫자 1 ~ 9의 계산할때 1 ~ 9 로 계산된다." {
        listOf(
            NumberShape.ACE,
            NumberShape.TWO,
            NumberShape.THREE,
            NumberShape.FOUR,
            NumberShape.FIVE,
            NumberShape.SIX,
            NumberShape.SEVEN,
            NumberShape.EIGHT,
            NumberShape.NINE
        ).forAll {
            val card = Card(it, Pattern.CLUB)
            card.getValue() shouldBe it.value
        }
    }

    "카드 K, Q , J 는 10으로 계산된다." {
        listOf(NumberShape.KING, NumberShape.QUEEN, NumberShape.JACK).forAll {
            val card = Card(it, Pattern.DIAMOND)
            card.getValue() shouldBe 10
        }
    }

    "카드 A 는 일단 1의 값을 가진다." {
        val card = Card(NumberShape.ACE, Pattern.HEART)
        card.getValue() shouldBe 1
    }
})
