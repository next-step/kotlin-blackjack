package blackjack.domain.model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `덧셈이 가능하다`() {
        var money = Money(10000)
        money += Money(2000)
        money.money shouldBe 12000
    }

    @Test
    fun `뺄셈이 가능하다`() {
        var money = Money(10000)
        money -= Money(2000)
        money.money shouldBe 8000
    }
}
