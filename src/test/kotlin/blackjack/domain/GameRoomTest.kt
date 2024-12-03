package blackjack.domain

import org.junit.jupiter.api.Test

class GameRoomTest {

    @Test
    fun `GameRoon을 만들면 각자 카드가 두장씩 생긴다`() {
        val players = listOf(
            "a",
            "b",
            "c",
        ).map { Player(name = it, bettingMoney = BettingMoney(1000)) }
        val gameRoom = GameRoom(players = players)

        gameRoom.participants.forEach {
            assert(it.cards.size == 2)
        }
    }
}
