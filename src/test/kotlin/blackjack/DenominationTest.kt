package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DenominationTest {

    @Test
    fun `Denomination 점수 출력`(){
        val sample = Denomination.ACE
        assertThat(Denomination.getScore(sample)).isEqualTo(1)
    }
    @Test
    fun `Denomination symbol print`(){
        val sample = Denomination.QUEEN
        assertThat(Denomination.getSymbol(sample)).isEqualTo("QUEEN")
    }

    @Test
    fun `Denomination Ace Calculator`(){
        assertThat(Denomination.AceCalculator(13)).isEqualTo(1)
        assertThat(Denomination.AceCalculator(9)).isEqualTo(11)
    }

}