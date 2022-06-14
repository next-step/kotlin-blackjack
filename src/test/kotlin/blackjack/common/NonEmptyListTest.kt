package blackjack.common

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class NonEmptyListTest {
    @Test
    fun `비어있는 리스트일 경우 IllegalArgumentException 이 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy { NonEmptyList<Any>(emptyList()) }
        assertThatIllegalArgumentException().isThrownBy { emptyList<Any>().toNonEmptyList() }
    }

    @Test
    fun `하나 이상의 element 가 있을 경우 익셉션이 발생하지 않는다`() {
        assertThatNoException().isThrownBy { NonEmptyList(listOf(1)) }
        assertThatNoException().isThrownBy { listOf(1).toNonEmptyList() }
    }
}
