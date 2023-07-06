package blackjack.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

enum class Pattern {
    CLOVER, DIAMOND, HEART, SPADE,
}

data class Card(private val value: String, val pattern: Pattern) {

    fun getValue(): Int {
        if (value == "A") return 1
        if (value in listOf("K", "Q", "J")) return 10
        return value.toInt()
    }

    infix operator fun plus(other: Card): Int = this.getValue() + other.getValue()
}

class CardTest : StringSpec({
    "카드는 숫자 1~ 9를 가질수 있다." {
        listOf("1", "2", "3", "4", "5", "6", "7", "8", "9").forAll {
            val card = Card(it, Pattern.CLOVER)
            card.getValue() shouldBe it.toInt()
        }
    }

    "카드 K, Q , J 는 10으로 계산된다." {
        listOf("K", "Q", "J").forAll {
            val card = Card(it, Pattern.DIAMOND)
            card.getValue() shouldBe 10
        }
    }

    "카드 A 는 일단 1의 값을 가진다." {
        val card = Card("A", Pattern.HEART)
        card.getValue() shouldBe 1
    }
})
