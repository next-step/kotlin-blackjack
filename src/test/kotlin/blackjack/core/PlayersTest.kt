package blackjack.core

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `이름을 확인한다`() {
        Players(setOf(Name("test1"))).getNames() shouldBe "test1"
        Players(setOf(Name("test1"), Name("test2"))).getNames() shouldBe "test1,test2"
    }
}
