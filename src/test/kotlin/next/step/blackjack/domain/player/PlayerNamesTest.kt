package next.step.blackjack.domain.player

import io.kotest.core.spec.style.DescribeSpec
import org.junit.jupiter.api.assertThrows

class PlayerNamesTest : DescribeSpec({

    describe("PlayerNames 생성") {
        context("빈 셋으로 생성하면") {
            it("예외 발생") {
                assertThrows<IllegalArgumentException> { PlayerNames.of(emptySet()) }
            }
        }
    }

})
