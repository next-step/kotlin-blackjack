package blackJack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayersTest {

    private lateinit var cardDeck: Cards
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        cardDeck = CardDeck.createShuffledDeck()
        dealer = Dealer(cardDeck)
    }

    @Test
    fun `Player name List 입력하면 자동으로 Players 와 초기 카드가 생성된다`() {
        val names = listOf("pobi", "jason")
        val players = Players.createPlayers(names, dealer)
        Assertions.assertThat(players.players[0].name).isEqualTo("pobi")
        Assertions.assertThat(players.players.size).isEqualTo(2)
    }
}
