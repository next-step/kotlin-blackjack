package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by Jaesungchi on 2022.06.10..
 */
class CardTest {
    @Test
    internal fun `2 하트 카드를 출력시 2하트로 출력된다`() {
        assertThat(Card(CardType.HEART, CardNumber.TWO).toString()).isEqualTo("2하트")
    }
}
