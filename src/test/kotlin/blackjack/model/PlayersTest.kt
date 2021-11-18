package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayersTest {

    @Test
    fun `플레이어들을 Names로 변환할 수 있다`() {
        val names = Names.from("laco")
        val players = Gamers.players(names)
        assertThat(players.toNames()).isEqualTo(names)
    }

    @Test
    fun `플레이어들 모두에게 카드를 줄 수 있다`() {
        // given
        val names = Names.from("laco", "pobi")
        val players = Gamers.players(names)
        val cards = listOf(
            Card(Denomination.TEN, Suit.HEART),
            Card(Denomination.TEN, Suit.SPADE),
            Card(Denomination.TEN, Suit.DIAMOND),
            Card(Denomination.TEN, Suit.CLOVER),
        )

        // when
        var count = 0
        val (player1, player2) = players.receiveAll(
            count = 2,
            next = { cards.getOrNull(count++) }
        )
            .toList()
            .map { it.cards }

        // then
        assertAll(
            { assertThat(player1).isEqualTo(Cards(cards.take(2))) },
            { assertThat(player2).isEqualTo(Cards(cards.takeLast(2))) }
        )
    }
}
