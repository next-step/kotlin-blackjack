import blackjack.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SuitTest {
    @Test
    fun `-모양 별로 4종류의 슈트가 있다(하트, 스페이드, 클로버, 다이아)`() {
        val size = Suit.values().size
        assertThat(size).isEqualTo(4)
    }
}