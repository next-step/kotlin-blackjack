package blackjack.domain

import org.junit.jupiter.api.Test

class ParticipantsTest {

    val player = listOf<GameParticipants>(Dealer(),Player("Jong"), Player("Kim"))

    @Test
    fun `딜러가 21이 초과하는 경우 다른 플레이어 승리`() {
        player.forEach {
            it.drawCard()
            println("${it.name} : ${it.cards.calculateMyCards()}")
        }
        val participants = Participants(player)
        println("최저 차이 : ${participants.getMinimumDistance()}")
        println("딜러가 21이 넘는 : ${participants.isDealerCardsOver21()}")
        println("승자 : ${participants.getWinners().map { it.name } }}")
    }



}