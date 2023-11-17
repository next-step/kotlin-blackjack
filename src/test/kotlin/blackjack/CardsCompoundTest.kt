package blackjack

import blackjack.CardsCompound.Companion.BUSTED
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsCompoundTest {
    @Test
    fun `CardsCompound에 숫자 추가시 관련 조합을 맞춰준다`() {
        val cardsCompound = CardsCompound.get()
        cardsCompound.addNumber(CardNumber.KING)
        assertThat(cardsCompound.bestNumber).isEqualTo(10)
        cardsCompound.addNumber(CardNumber.FIVE)
        assertThat(cardsCompound.bestNumber).isEqualTo(15)
        cardsCompound.addNumber(CardNumber.FOUR)
        assertThat(cardsCompound.bestNumber).isEqualTo(19)
        cardsCompound.addNumber(CardNumber.THREE)
        assertThat(cardsCompound.bestNumber).isEqualTo(BUSTED)
    }

    @Test
    fun `CardsCompound에 에이스 추가 시 1과 11을 모두 고려한다`() {
        val cardsCompound = CardsCompound.get()
        cardsCompound.addNumber(CardNumber.KING)

        cardsCompound.addNumber(CardNumber.ACE)
        assertThat(cardsCompound.bestNumber).isEqualTo(21)

        cardsCompound.addNumber(CardNumber.ACE)
        assertThat(cardsCompound.bestNumber).isEqualTo(12)
    }
}
