package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MoneyTest {

    @Test
    @DisplayName("Money.of: 0 이상이면 생성된다")
    fun of_validAmount_createsMoney() {
        // given & when
        val money0 = Money.of(0)
        val money1000 = Money.of(1000)

        // then
        assertEquals(0, money0.amount)
        assertEquals(1000, money1000.amount)
    }

    @Test
    @DisplayName("Money.of: 음수면 예외가 발생한다")
    fun of_negativeAmount_throws() {
        // given & when & then
        val ex = assertThrows(IllegalArgumentException::class.java) {
            Money.of(-1)
        }
        assertTrue(ex.message!!.contains("0 이상"))
    }

    @Test
    @DisplayName("Money.plus: 두 Money를 더하면 합계 Money가 된다")
    fun plus_addsAmounts() {
        // given & when
        val a = Money.of(1000)
        val b = Money.of(500)

        val result = a + b

        // then
        assertEquals(1500, result.amount)
    }

    @Test
    @DisplayName("Money.minus: 큰 값에서 작은 값을 빼면 정상 동작한다")
    fun minus_subtractsAmounts() {
        // given & when
        val a = Money.of(1000)
        val b = Money.of(400)

        val result = a - b

        // then
        assertEquals(600, result.amount)
    }

    @Test
    @DisplayName("Money.minus: 결과가 0이면 허용된다")
    fun minus_zeroAllowed() {
        // given & when
        val a = Money.of(1000)
        val b = Money.of(1000)

        val result = a - b

        // then
        assertEquals(0, result.amount)
    }

    @Test
    @DisplayName("Money.minus: 결과가 음수가 되면 예외가 발생한다")
    fun minus_negativeResult_throws() {
        // given & when
        val a = Money.of(500)
        val b = Money.of(1000)

        val ex = assertThrows(IllegalArgumentException::class.java) {
            a - b
        }

        // then
        assertTrue(ex.message!!.contains("0 미만"))
    }
}