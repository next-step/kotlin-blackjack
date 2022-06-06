package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ObservableTest {
    @Test
    fun `Observable은 지정한 타입의 객체를 보관한다`() {
        val observable = Observable(0)

        assertThat(observable.value).isEqualTo(0)
    }

    @Test
    fun `observe를 통해 Observer를 Observable에 등록할 수 있다`() {
        val observable = Observable(0)
        observable.value = 100
        observable.observe { int ->
            assertThat(int).isEqualTo(100)
        }
    }
}
