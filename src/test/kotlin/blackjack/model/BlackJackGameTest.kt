package blackjack.model.game

import blackjack.model.BlackJackGame
import blackjack.model.card.Card
import blackjack.model.card.CardScore
import blackjack.model.card.Cards
import blackjack.model.card.Deck
import blackjack.model.card.ShufflingStrategy
import blackjack.model.card.Suit
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.PlayerInfo
import blackjack.model.participant.Players
import blackjack.model.state.finished.Stay
import blackjack.model.state.running.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    private lateinit var ACE_CLUB: Card
    private lateinit var TWO_CLUB: Card
    private lateinit var SIX_CLUB: Card
    private lateinit var TEN_CLUB: Card
    private lateinit var players: Players
    private lateinit var game: BlackJackGame

    @BeforeEach
    fun setUp() {
        ACE_CLUB = Card(CardScore.ACE, Suit.CLUB)
        TWO_CLUB = Card(CardScore.TWO, Suit.CLUB)
        SIX_CLUB = Card(CardScore.SIX, Suit.CLUB)
        TEN_CLUB = Card(CardScore.TEN, Suit.CLUB)

        players = Players(listOf(Player(PlayerInfo("mark"), Hit(Cards(TWO_CLUB, SIX_CLUB)))))
        game = BlackJackGame(players)
    }

    @Test
    fun `딜러가 카드 2장을 뽑는다`() {
        // when
        val dealer = game.setUpDealer()

        // given
        assertThat(dealer.hasTwoCards()).isTrue()
    }

    @Test
    fun `플레이어들이 카드 2장씩을 받는다`() {
        // when
        val players = game.setUpPlayers()

        // given
        assertThat(players.players[0].hasTwoCards()).isTrue()
    }

    @Test
    fun `플레이어가 원하면 Stay한다`() {
        // given
        val player = Player(PlayerInfo("mark"), Hit(Cards(TWO_CLUB, SIX_CLUB)))

        // when
        val stayPlayer: Player = game.playByPlayer(player, { false }, {})

        // then
        assertThat(stayPlayer.state).isInstanceOf(Stay::class.java)
        assertThat(stayPlayer.hasTwoCards()).isTrue()
        assertThat(stayPlayer.score()).isEqualTo(8)
    }

    @Test
    fun `딜러는 점수가 17이상이 되면 카드 뽑기를 멈춘다`() {
        // given
        val deck = Deck(
            setOf(
                ACE_CLUB, TWO_CLUB
            ),
            object : ShufflingStrategy {
                override fun shuffle(cards: Set<Card>): Set<Card> = cards.sortedBy { it.score }.toSet()
            }
        )
        val game = BlackJackGame(players, deck)
        val dealer = Dealer(Hit(Cards(SIX_CLUB, TEN_CLUB)))

        // when
        val dealerFinished = game.playByDealer(dealer, {})

        // then
        assertThat(dealerFinished.canDraw()).isFalse()
        assertThat(dealerFinished.state.cards).isEqualTo(Cards(SIX_CLUB, TEN_CLUB, ACE_CLUB))
        assertThat(dealerFinished.score()).isEqualTo(17)
    }
}
