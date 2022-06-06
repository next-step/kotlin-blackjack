package camp.nextstep.blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardSuitTest {

    @DisplayName("카드 모양은 스페이드, 하트, 다이아몬드, 클럽 중 하나이다.")
    @ParameterizedTest(name = "카드 모양 {0} ({1}) 테스트 ")
    @CsvSource(
        delimiter = '.',
        value = [
            "SPADE.♠",
            "HEART.♥",
            "DIAMOND.⬥",
            "CLUB.♣",
        ]
    )
    fun cardSuitTest(suit: String, shape: String) {
        assertThat(CardSuit.valueOf(suit)).isNotNull
    }
}
