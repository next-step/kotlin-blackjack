package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `플레이어는 베팅 금액을 설정할 수 있다`() {
        val player = Player.from("철수")
        val bettingMoney = Money(1000)

        player.betting(bettingMoney)

        player.bettingMoney shouldBe bettingMoney
    }

    @Test
    fun `evenMoney 를 알 수 있다`() {
        val money = Money(100)

        val actual = money.evenMoney()

        actual shouldBe Money(150)
    }

    @Test
    fun `금액을 더 할 수 있다`() {
        val money = Money(100)

        val actual = money + Money(50)

        actual shouldBe Money(150)
    }

    @Test
    fun `음수로 변환 가능하다`() {
        val money = Money(100)

        val actual = money.toNegative()

        actual shouldBe Money(-100)
    }
}