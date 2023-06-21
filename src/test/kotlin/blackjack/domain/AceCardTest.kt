package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AceCardTest : FunSpec({
    test("ACE 카드는 1과 11중 21에 더 가까운 값을 반환한다.") {
        val actual = AceCard(symbol = SymbolType.DIAMOND)

        actual.score(20) shouldBe 1
        actual.score(10) shouldBe 11
    }

    test("ACE 카드는 공통적으로 a라는 이름을 가진다.") {
         val actual = listOf(
             AceCard(symbol = SymbolType.DIAMOND),
             AceCard(symbol = SymbolType.HEART),
             AceCard(symbol = SymbolType.CLOVER)
         )

        actual.forEach {
            it.name() shouldBe AceCard.ACE_NAME
        }
    }
})
