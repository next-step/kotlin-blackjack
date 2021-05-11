package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `findOneByStatusIsPlaying를 호출하면 플레잉 중인 유저 한명이 리턴되어야 한다`() {
        val players = Players(
            setOf(
                Player("오", setOf(Card(CardSuite.DIAMOND, CardNumber.ACE))),
                Player("길", setOf(Card(CardSuite.DIAMOND, CardNumber.QUEEN), Card(CardSuite.DIAMOND, CardNumber.KING), Card(CardSuite.DIAMOND, CardNumber.THREE))),
                Player("환", setOf(Card(CardSuite.DIAMOND, CardNumber.ACE)))
            )
        )

        assertThat(players.findOneByStatusIsPlaying().name).isEqualTo("오")
        players.findOneByStatusIsPlaying().stop()
        assertThat(players.findOneByStatusIsPlaying().name).isEqualTo("환")
    }
}
