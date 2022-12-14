package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

class NameTest {
    @EmptySource
    @ParameterizedTest
    fun `빈 값은 이름이 될 수 없다`(name: String) {
        shouldThrow<IllegalArgumentException> { Name(name) }
    }
}
