import blackjack.Card
import blackjack.CardShuffleStrategy
import blackjack.ContinueDeal
import blackjack.GameBlackjack
import blackjack.GameDealer
import blackjack.GamePlayer
import blackjack.GamePlayers
import blackjack.Message
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
    val gameBlackjack = GameBlackjack(gameDealer)

    val playing = gameBlackjack.initialDealing(playerNames)
    Output.printPlayersInitialDealing(playing)

    val updatedPlayers = playing.players.map {
        var player: GamePlayer = it
        do {
            Output.printPlayerAction(player)
            when (ContinueDeal.of(Input.getLine())) {
                ContinueDeal.YES -> {
                    player = gameBlackjack.continueDealing(player)
                    Output.printPlayerCards(player)
                }

                ContinueDeal.NO -> break
                ContinueDeal.MISS -> continue
            }
        } while (!player.isBust)
        player
    }

    Output.printPlayersResult(GamePlayers(updatedPlayers))
}
