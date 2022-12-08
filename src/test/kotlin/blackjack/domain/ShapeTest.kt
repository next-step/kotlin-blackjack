package blackjack.domain

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ShapeTest {
    @Test
    fun `모양은 스페이드, 다이아몬드, 하트, 클로버로 구성된다`() {
        Shape.values().map { it.name } shouldContainExactly listOf("SPADE", "DIAMOND", "CLUB", "HEART")
    }

    @Test
    fun `모양은 한글로 변환 가능하다`() {
        Shape.SPADE.toString() shouldBe "스페이드"
        Shape.DIAMOND.toString() shouldBe "다이아몬드"
        Shape.CLUB.toString() shouldBe "클로버"
        Shape.HEART.toString() shouldBe "하트"
    }
}
