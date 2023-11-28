package blackjack.domain.player

import blackjack.domain.rule.DefaultScoringRule
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `Players 객체는 참가자가 없는 상태로 생성`() {
        val players = Players()

        players.size shouldBe 0
    }

    @Test
    fun `Players에 플레이어를 추가할 수 있다`() {
        val players = Players()
        players.add(Participant("p1", DefaultScoringRule()))

        players.size shouldBe 1
    }

    @Test
    fun `Players에 같은 Player를 추가하면 1개만 등록된다`() {
        val players = Players()
        val participant = Participant("p1", DefaultScoringRule())
        players.add(participant)
        players.add(participant)

        players.size shouldBe 1
    }

    @Test
    fun `Players에 같은 Player가 등록되어있는지 검사한다`() {
        val players = Players()
        val participant1 = Participant("p1", DefaultScoringRule())
        val participant2 = Participant("p2", DefaultScoringRule())
        players.add(participant1)

        players.contains(participant1) shouldBe true
        players.contains(participant2) shouldBe false
    }
}