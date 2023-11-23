package blackjack.model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CardValueTest {

    @Test
    fun `A가 11 이어야 하는 경우 테스트`() {
        val cardValue = CardValue.A
        val score = cardValue.getScore()
        score shouldBe 11
    }
    @Test
    fun `A가 1이어야 하는 경우 테스트`() {
        val cardValue = CardValue.A
        val score = cardValue.getScore(13)
        score shouldBe 1
    }
    @Test
    fun `K 테스트`() {
        val cardValue = CardValue.K
        val score = cardValue.getScore()
        score shouldBe 10
    }
    @Test
    fun `3 테스트`() {
        val cardValue = CardValue.THREE
        val score = cardValue.getScore()
        score shouldBe 3
    }
}
