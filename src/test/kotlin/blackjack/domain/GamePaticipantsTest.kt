package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamePaticipantsTest {

    private val dealerCards = Cards(
        arrayListOf(
            Card(Number.FIVE to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND),
            Card(Number.ACE to Pattern.DIAMOND)
        )
    )
    private val jongCards = Cards(
        arrayListOf(
            Card(Number.ACE to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND)
        )
    )
    private val kimCards = Cards(
        arrayListOf(
            Card(Number.KING to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND)
        )
    )

    private val participants = Participants(
        listOf<GameParticipants>(
            Dealer(cards = dealerCards),
            Player("Jong", jongCards),
            Player("Kim", kimCards)
        )
    )

    @Test
    fun `게임의 승패를 확인`() {
        val jong = Player("Jong", jongCards)
        val winners = participants.getWinners()
        assertThat(jong.isWinner(winners)).isEqualTo("승")
    }

    @Test
    fun `21과의 차이가 얼마나 되는지 확인`() {
        val kim = Player("Kim", kimCards)
        assertThat(kim.getDistance()).isEqualTo(1)
    }
}