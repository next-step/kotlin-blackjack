package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어 이름 파서 테스트")
class PlayerNameParserTest {
    @Test
    fun `플레이어 이름을 구별자로 구분하여 파싱한다`() {
        // given
        val input = "pobi,crong,honux"
        val expected = listOf("pobi", "crong", "honux")

        // when
        val actual = PlayerNameParser.parse(input)

        // then
        actual shouldBe expected
    }
}
