package blackjack.domain

import blackjack.domain.card.Denomination
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DenominationTest {
    @Test
    fun `2부터 10까지는 숫자 그대로 따라간다`() {
        Denomination.TWO.score shouldBe 2
        Denomination.THREE.score shouldBe 3
        Denomination.FOUR.score shouldBe 4
        Denomination.FIVE.score shouldBe 5
        Denomination.SIX.score shouldBe 6
        Denomination.SEVEN.score shouldBe 7
        Denomination.EIGHT.score shouldBe 8
        Denomination.NINE.score shouldBe 9
        Denomination.TEN.score shouldBe 10
    }

    @Test
    fun `Jack, King, Queen은 10으로 취급한다`() {
        Denomination.KING.score shouldBe 10
        Denomination.QUEEN.score shouldBe 10
        Denomination.JACK.score shouldBe 10
    }

    @Test
    fun `Ace는 1로 취급한다`() {
        Denomination.ACE.score shouldBe 1
    }
}
