package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ResultTest {
    @Test
    fun `승리 결과`() {
        Result.WIN.value shouldBe "승리"
    }

    @Test
    fun `패배 결과`() {
        Result.LOSE.value shouldBe "패배"
    }
}
