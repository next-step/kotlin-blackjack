package next.step.blackjack.domain.player

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class PlayerNameTest : DescribeSpec({

    describe("PlayerName 생성") {
        context("공백이 들어가면 예외 발생") {
            withData(
                nameFn = { "PlayerName.of(\"${it}\")" },
                "",
                " ",
                "    "
            ) { name ->
                assertThrows<IllegalArgumentException> { PlayerName.of(name) }
            }
        }
    }

    describe("PlayerName method") {
        context("name() 호출하면") {
            it("문자열로 이름 제공") {
                PlayerName.of("test").name() shouldBe "test"
            }
        }
    }

})
