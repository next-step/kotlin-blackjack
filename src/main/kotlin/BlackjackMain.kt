import card.CardPack
import card.deck.CardDeck
import player.PlayerFactory
import player.PlayerGroup
import view.InputView
import view.OutputView

fun main() {

    val playerNames = InputView.inputPlayerName()
    val playerGroup = PlayerGroup(PlayerFactory.createPlayerList(playerNames))
    val cardDeck = CardDeck(CardPack.cards.toMutableList())

    val game = BlackjackGame(cardDeck = cardDeck, playerGroup = playerGroup, InputView, OutputView)

    OutputView.showGameStart(game.playerGroup)

    game.start()

    OutputView.showGameEnd(game.playerGroup)
}
