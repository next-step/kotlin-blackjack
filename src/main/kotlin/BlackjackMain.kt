import card.CardPack
import card.deck.GameDeck
import player.PlayerFactory
import player.PlayerGroup
import view.InputView
import view.OutputView

fun main() {

    val playerNames = InputView.inputPlayerName()
    val playerGroup = PlayerGroup(PlayerFactory.createPlayerList(playerNames))
    val gameDeck = GameDeck(CardPack.cards.toMutableList())

    val game = BlackjackGame(cardDeck = gameDeck, playerGroup = playerGroup)

    OutputView.showGameStart(game.playerGroup)

    game.start()

    OutputView.showGameEnd(game.playerGroup)
}
