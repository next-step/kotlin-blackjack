package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.State
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `Players 객체는 플레이어의 리스트를 받아서 생성`() {
        val players = Players(
            listOf(
                Participant("p1", DefaultScoringRule()),
                Participant("p2", DefaultScoringRule()),
                Dealer(DefaultScoringRule())
            )
        )

        players.size shouldBe 3
    }

    @Test
    fun `Players에 플레이어들이 모두 턴이 종료되었는지 판단할 수 있다`() {
        val deck = Deck()
        val p1 = Participant("p1", DefaultScoringRule())
        val p2 = Participant("p2", DefaultScoringRule())
        val players = Players(listOf(p1, p2))
        p1.beginGame(deck)
        p2.beginGame(deck)
        p1.nextTurn(State.STAY, deck)
        p2.nextTurn(State.STAY, deck)

        players.size shouldBe 2
        players.isAllFinished() shouldBe true
    }
}