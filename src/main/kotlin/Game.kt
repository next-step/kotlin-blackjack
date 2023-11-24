import blackjack.Card
import blackjack.CardShuffleStrategy
import blackjack.DefaultGameBlackjack
import blackjack.GameBlackjack
import blackjack.GameCardDealer
import blackjack.GameParticipants
import blackjack.Message
import blackjack.InputOutputStrategy
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
    val gameCardDealer = GameCardDealer(cardShuffleStrategy)

    Output.printMessage(Message.INPUT_PLAYER_NAMES)
    val playerNames = Input.getLine()
    val gameBlackjack: GameBlackjack = DefaultGameBlackjack(gameCardDealer, InputOutputStrategy())

    val playing = gameBlackjack.initialDealing(playerNames)
    Output.printParticipantsInitialDealing(playing)

    val updatedPlayers = playing.players.map {
        gameBlackjack.continueDealing(it)
    }
    val updatedDealer = gameBlackjack.continueDealing(playing.dealer)

    val gameParticipants = GameParticipants(updatedPlayers, updatedDealer)
    Output.printParticipantResult(gameParticipants)

    val gameParticipantResults = gameParticipants.calcMatchResult()

    Output.printGameParticipantResults(gameParticipantResults)
}
