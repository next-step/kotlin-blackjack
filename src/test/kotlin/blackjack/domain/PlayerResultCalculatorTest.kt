package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerResultCalculatorTest {

    private val calculator = PlayerResultCalculator()

    @Test
    fun `{given} 딜러 = Bust 이면 {when & then} 플레이어 승리`() {
        val result = calculator.calculate(dealerScore = 22, playerScore = 20)
        assertEquals(PlayerResult.WIN, result)
    }

    @Test
    fun `{given} 플레이어 = Bust 이면 {when & then} 딜러 승리`() {
        val result = calculator.calculate(dealerScore = 20, playerScore = 22)
        assertEquals(PlayerResult.LOSE, result)
    }

    @Test
    fun `{given} 딜러, 플레이어 둘 다 Bust 아닐때 {when} 플레이어 점수 높으면 {then} 플레이어가 승리`() {
        val result = calculator.calculate(dealerScore = 18, playerScore = 19)
        assertEquals(PlayerResult.WIN, result)
    }

    @Test
    fun `{given}딜러, 플레이어 둘 다 Bust 아닐때 {when} 딜러 점수 높으면 {then} 딜러가 승리`() {
        val result = calculator.calculate(dealerScore = 19, playerScore = 18)
        assertEquals(PlayerResult.LOSE, result)
    }
}