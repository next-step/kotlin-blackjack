package blackjack.model.candidate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("참가자 테스트")
class CandidateTest {

    @Test
    fun `참가자 생성시 가지고 있는 카드 개수는 0개`() {
        // given, when, then
        assertThat(Candidate.from("aiden").cardSize).isEqualTo(0)
    }
}
