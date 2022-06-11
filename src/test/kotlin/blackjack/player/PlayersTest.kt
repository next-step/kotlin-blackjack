package blackjack.player

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class PlayersTest : AnnotationSpec() {
    @Test
    fun `players들의 이름이 ,로 합쳐진다`() {
        val sut = Players(
            listOf(
                Player("pobi"),
                Player("jason"),
            )
        )

        val result = sut.names()

        result shouldBe "pobi, jason"
    }
}
