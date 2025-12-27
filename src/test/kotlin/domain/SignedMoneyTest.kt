package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SignedMoneyTest {
    @Test
    @DisplayName("SignedMoney: 양수/0/음수 모두 표현 가능하다")
    fun signedMoney_allowsAnyInt() {
        // given & when & then
        assertEquals(10, SignedMoney(10).amount)
        assertEquals(0, SignedMoney(0).amount)
        assertEquals(-10, SignedMoney(-10).amount)
    }

    @Test
    @DisplayName("SignedMoney.plus: 더하면 합산된다(음수 포함)")
    fun plus_addsAmounts() {
        // given & when
        val a = SignedMoney(100)
        val b = SignedMoney(-30)

        val result = a + b

        // then
        assertEquals(70, result.amount)
    }

    @Test
    @DisplayName("SignedMoney.unaryMinus: 부호가 반전된다")
    fun unaryMinus_flipsSign() {
        // given & when & then
        assertEquals(-100, (-SignedMoney(100)).amount)
        assertEquals(100, (-SignedMoney(-100)).amount)
        assertEquals(0, (-SignedMoney(0)).amount)
    }
}