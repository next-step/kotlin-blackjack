package blackjack.model.candidate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("참가자 컬렉션 테스트")
class CandidatesTest {

    @Test
    fun `참가자가 1명 미만이면 예외 발생`() {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { Candidates(listOf()) }
        assertThat(exception.message).isEqualTo("참가자는 1명 이상이어야 합니다.")
    }

    @Test
    fun `다음 참가자 찾는 기능이 정상 동작`() {
        // given
        val player1 = Player.from("aiden1", 1)
        val player2 = Player.from("aiden2", 1)
        val player3 = Player.from("aiden3", 1)

        val candidates = Candidates(listOf(player1, player2, player3))

        // when, then
        assertAll(
            "find next candidate test",
            { assertThat(candidates.findNext(player1)).isEqualTo(player2) },
            { assertThat(candidates.findNext(player2)).isEqualTo(player3) },
            { assertThat(candidates.findNext(player3)).isEqualTo(player1) }
        )
    }
}
