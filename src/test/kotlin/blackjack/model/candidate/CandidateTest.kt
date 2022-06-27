package blackjack.model.candidate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("참가자 테스트")
class CandidateTest {

    @Test
    fun `참가자 생성시 가지고 있는 카드 개수는 0개`() {
        // given, when, then
        assertAll(
            "card size of initial candidate test",
            { assertThat(Player.from("aiden", 1).cardSize).isEqualTo(0) },
            { assertThat(Dealer().cardSize).isEqualTo(0) }
        )
    }
}
