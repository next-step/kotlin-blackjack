package blackjack.business.drawConditionStrategy

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class UserInputBasedDrawConditionTest {

    @Test
    fun `y가 입력되면 ture`() {
        // given
        val userInputBasedDrawCondition = UserInputBasedDrawCondition()

        // when
        val result = userInputBasedDrawCondition.shouldDraw("pobi") { "y" }

        // then
        result shouldBe true
    }

    @Test
    fun `n이 입력되면 false`() {
        // given
        val userInputBasedDrawCondition = UserInputBasedDrawCondition()

        // when
        val result = userInputBasedDrawCondition.shouldDraw("pobi") { "n" }

        // then
        result shouldBe false
    }
}
