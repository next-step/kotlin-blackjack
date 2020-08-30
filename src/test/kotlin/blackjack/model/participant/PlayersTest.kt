package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardScore
import blackjack.model.card.Cards
import blackjack.model.card.Deck
import blackjack.model.card.Suit
import blackjack.model.profit.Profits
import blackjack.model.participant.Player
import blackjack.model.participant.PlayerInfo
import blackjack.model.participant.Players
import blackjack.model.state.finished.BlackJack
import blackjack.model.state.finished.Bust
import blackjack.model.state.finished.Stay
import blackjack.model.state.running.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayersTest {
    private lateinit var SIX_CLUB: Card
    private lateinit var TEN_CULB: Card
    private lateinit var TEN_HEART: Card
    private lateinit var firstPlayer: Player
    private lateinit var secondPlayer: Player
    private lateinit var players: Players

    @BeforeEach
    fun setUp() {
        SIX_CLUB = Card(CardScore.SIX, Suit.CLUB)
        TEN_CULB = Card(CardScore.TEN, Suit.CLUB)
        TEN_HEART = Card(CardScore.TEN, Suit.HEART)

        firstPlayer = Player(PlayerInfo("mark"))
        secondPlayer = Player(PlayerInfo("harry"))
        players = Players(listOf(firstPlayer, secondPlayer))
    }

    @Test
    fun `플레이어 리스트 생성`() {
        // given
        val playerNames = "mark, harry"
        val expected = players

        // when
        val actual = Players(playerNames) { 0 }

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `기본 카드 2장씩 받기`() {
        // when
        players = players.takeDefaultCards { Deck.defaultDeck().provideCards() }
        firstPlayer = players.players[0]
        secondPlayer = players.players[1]

        // then
        assertThat(firstPlayer.hasTwoCards()).isTrue()
        assertThat(secondPlayer.hasTwoCards()).isTrue()
    }

    @DisplayName("플레이어들 수익: 1000, -1000, 0, 1500, -1000 // 딜러 점수: 16")
    @Test
    fun `플레이어들 수익`() {
        // given
        val expected: List<Int> = listOf(1000, -1000, 0, 1500, -1000)

        val cardsWithScore15 = Cards(Card(CardScore.TEN, Suit.CLUB), Card(CardScore.FIVE, Suit.CLUB))
        val cardsWithScore16 = Cards(Card(CardScore.TEN, Suit.CLUB), Card(CardScore.SIX, Suit.CLUB))
        val cardsWithScore18 = Cards(Card(CardScore.TEN, Suit.CLUB), Card(CardScore.EIGHT, Suit.CLUB))
        val cardsWithScore21 = Cards(Card(CardScore.TEN, Suit.CLUB), Card(CardScore.ACE, Suit.CLUB))
        val cardsWithScore22: Cards = cardsWithScore21 + Card(CardScore.ACE, Suit.CLUB)

        val players = listOf(
            Player(PlayerInfo("winner", 1000), Stay(cardsWithScore18)),
            Player(PlayerInfo("loser", 1000), Stay(cardsWithScore15)),
            Player(PlayerInfo("drawer", 1000), Stay(cardsWithScore16)),
            Player(PlayerInfo("blackjackWinner", 1000), BlackJack(cardsWithScore21)),
            Player(PlayerInfo("bustLoser", 1000), Bust(cardsWithScore22))
        )

        // when
        val profits: Profits = Players(players).getProfits(Hit(cardsWithScore16))
        val actual: List<Int> = profits.profits.map { it.profit }

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
