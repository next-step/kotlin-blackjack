package blackjack

import io.kotest.matchers.collections.shouldContainExactly
import org.junit.jupiter.api.Test

internal class ShapeTest {
    @Test
    fun `모양은 스페이드, 다이아몬드, 하트, 클로버로 구성된다`() {
        Shape.values().map { it.name } shouldContainExactly listOf("SPADE", "DIAMOND", "CLUB", "HEART")
    }
}
