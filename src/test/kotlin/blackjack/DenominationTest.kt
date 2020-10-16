package blackjack

import org.assertj.core.api.Assertions.assertThat

import blackjack.domain.Denomination

import org.junit.jupiter.api.Test

class DenominationTest {

    @Test
    fun `스코어 확인`() {
        val sampleCard = Denomination.FIVE
        assertThat(Denomination.getScore(sampleCard)).isEqualTo(5)
    }

    @Test
    fun `문양 확인`() {
        val sampleCard = Denomination.JACK
        assertThat(Denomination.getSymbol(sampleCard)).isEqualTo("J")
    }

    @Test
    fun `ACE 계산 확인`() {
        val maxScore = 13
        val minScore = 3
        assertThat(Denomination.calculateACE(maxScore)).isEqualTo(1)
        assertThat(Denomination.calculateACE(minScore)).isEqualTo(11)
    }
}
