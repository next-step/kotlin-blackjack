import blackjack.Card
import blackjack.CardShuffleStrategy
import blackjack.ContinueDeal
import blackjack.DefaultGameBlackjack
import blackjack.GameBlackjack
import blackjack.GameDealer
import blackjack.GamePlayer
import blackjack.GamePlayers
import blackjack.Message
import blackjack.PrintProxyBlackjack
import view.Input
import view.Output

fun main() {

    val cardShuffleStrategy = CardShuffleStrategy {
        Card.Symbol.values()
            .flatMap { symbol ->
                Card.Number.values().map { number ->
                    Card(symbol, number)
                }
            }
            .shuffled()
    }
    val gameDealer = GameDealer(cardShuffleStrategy)

    Output.printMessage(Message.INPUT_PLAYER_NAMES)
    val playerNames = Input.getLine()
    val gameBlackjack: GameBlackjack = PrintProxyBlackjack(DefaultGameBlackjack(gameDealer))

    val playing = gameBlackjack.initialDealing(playerNames)
    Output.printPlayersInitialDealing(playing)

    val updatedPlayers = playing.players.map {
        dealing(it, gameBlackjack)
    }

    Output.printPlayersResult(GamePlayers(updatedPlayers))
}

tailrec fun dealing(player: GamePlayer, gameBlackjack: GameBlackjack): GamePlayer {
    if (player.isBust || player.isBlackjack()) return player
    Output.printPlayerAction(player)
    return when (ContinueDeal.of(Input.getLine())) {
        ContinueDeal.YES -> dealing(gameBlackjack.continueDealing(player), gameBlackjack)
        ContinueDeal.MISS -> dealing(player, gameBlackjack)
        ContinueDeal.NO -> player
    }
}
