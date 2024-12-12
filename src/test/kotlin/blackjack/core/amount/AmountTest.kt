package blackjack.core.amount

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class AmountTest {
    @Test
    fun `음수가 전달되었을 때 Class 생성 오류를 테스트`() {
        shouldThrow<IllegalArgumentException> { Amount(-1) }
    }
}
