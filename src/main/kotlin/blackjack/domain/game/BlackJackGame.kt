package blackjack.domain.game

import blackjack.domain.behavior.StartState
import blackjack.domain.card.InitPlayingCards
import blackjack.domain.deck.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJackGame(
    playerNames: List<String>,
    private val deck: Deck = Deck(),
) {

    val players: Players = Players(
        players = playerNames.map(this::createPlayer),
    )

    private fun createPlayer(name: String) = Player(
        name = name,
        state = StartState(
            playingCards = initialCard(),
        ),
    )

    private fun initialCard() = InitPlayingCards(
        cards = deck.multiDraw(
            count = InitPlayingCards.INIT_CARD_COUNT,
        ),
    )

    fun start(gameEvent: GameEvent) = players.forEach {
        playTurn(player = it, gameEvent = gameEvent)
    }

    private fun playTurn(player: Player, gameEvent: GameEvent) = player.play(gameEvent = gameEvent) {
        deck.draw()
    }
}
