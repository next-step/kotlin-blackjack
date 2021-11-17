package blackjack.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerCardAdditionDeciderTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20])
    fun `20이하의 sum 에 대해 를 canReceiveAdditionalCard() 를 호출하면 true 를 리턴한다`(sum: Int) {
        assertThat(PlayerCardAdditionDecider().canReceiveAdditionalCard(sum)).isTrue
    }

    @ParameterizedTest
    @ValueSource(ints = [21, 25, 30])
    fun `21이상의 sum 에 대해 를 canReceiveAdditionalCard() 를 호출하면 false 를 리턴한다`(sum: Int) {
        assertThat(PlayerCardAdditionDecider().canReceiveAdditionalCard(sum)).isFalse
    }
}
