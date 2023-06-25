package blackjack.utils

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class StringUtilsTest {

    @Test
    fun `입력 받은 참가자명을 , 기준으로 구분한다`() {
        val input = "player1 , player2"
        val actual = StringUtils.replaceWhiteSpaceAndSplitByComma(input)

        actual[0] shouldBe "player1"
        actual[1] shouldBe "player2"
    }
}
