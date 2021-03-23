package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {

    val dealerCards = Cards(
        arrayListOf(
            Card(Number.FIVE to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND),
            Card(Number.ACE to Pattern.DIAMOND)
        )
    )
    val jongCards = Cards(
        arrayListOf(
            Card(Number.ACE to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND)
        )
    )
    val kimCards = Cards(
        arrayListOf(
            Card(Number.KING to Pattern.CLOVER),
            Card(Number.KING to Pattern.DIAMOND)
        )
    )


    @Test
    fun `딜러가 21이 초과하지 않는 경우 21과 가까운 사람이 승리`() {
        val participants = listOf<GameParticipants>(
            Dealer(cards = dealerCards),
            Player("Jong", jongCards),
            Player("Kim", kimCards)
        )
        val gmaeParticipants = Participants(participants)
        assertThat(gmaeParticipants.getWinners()).contains(Player("Jong", jongCards))
    }

    @Test
    fun `딜러가 21이 초과하는 경우 다른 플레이어가 승리`() {
        val dealerOver21Cards = Cards(
            arrayListOf(
                Card(Number.KING to Pattern.CLOVER),
                Card(Number.KING to Pattern.DIAMOND),
                Card(Number.TWO to Pattern.DIAMOND)
            )
        )
        val participants = listOf<GameParticipants>(
            Dealer(cards = dealerOver21Cards),
            Player("Jong", jongCards),
            Player("Kim", kimCards)
        )
        val gameParticipants = Participants(participants)
        assertThat(gameParticipants.getWinners()).containsExactlyInAnyOrder(
            Player("Jong", jongCards),
            Player("Kim", kimCards)
        )
    }


}