package blackjack

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardPoolTest {

    @DisplayName(value = "한 세트의 CardPool의 사이즈는 52장이다.")
    @Test
    fun checkCardPoolSize() {
        val cards = CardPool().cards
        Assertions.assertThat(cards.size).isEqualTo(52)
    }

    @DisplayName(value = "한세트의 CarPool은 중복 제거를 해도 같아야 한다.")
    @Test
    fun checkDuplicationCardPool() {
        val cards = CardPool().cards
        val cards2 = cards.distinct()
        Assertions.assertThat(cards2).containsAll(cards)
    }
}
