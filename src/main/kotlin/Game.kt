import blackjack.Card
import blackjack.CardShuffleStrategy
import blackjack.ContinueDeal
import blackjack.DefaultGameBlackjack
import blackjack.GameBlackjack
import blackjack.GameDealer
import blackjack.GameParticipant
import blackjack.GameParticipants
import blackjack.GameScoreCalculator
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
    Output.printParticipantsInitialDealing(playing)

    val updatedPlayers = playing.players.map {
        dealing(it, gameBlackjack)
    }
    val updatedDealer = dealing(playing.dealer, gameBlackjack)

    val gameParticipants = GameParticipants(updatedPlayers, updatedDealer)
    Output.printParticipantResult(gameParticipants)

    val gameScoreCalculator = GameScoreCalculator(gameParticipants)
    val gameParticipantResults = gameScoreCalculator.getMatchResult()

    Output.printGameParticipantResults(gameParticipantResults)
}

private fun dealing(participant: GameParticipant, blackjack: GameBlackjack) =
    when (participant) {
        is GameParticipant.Dealer -> dealerDealing(participant, blackjack)
        is GameParticipant.Player -> playerDealing(participant, blackjack)
    }

private fun dealerDealing(participant: GameParticipant, blackjack: GameBlackjack): GameParticipant {
    return if (participant.isNotAllowedDealing()) participant
    else {
        Output.printMessage(Message.PRINT_DEALER_DEALING)
        blackjack.continueDealing(participant)
    }
}

private tailrec fun playerDealing(participant: GameParticipant, blackjack: GameBlackjack): GameParticipant {
    if (participant.isNotAllowedDealing()) return participant
    Output.printParticipantAction(participant)
    return when (ContinueDeal.of(Input.getLine())) {
        ContinueDeal.YES -> playerDealing(blackjack.continueDealing(participant), blackjack)
        ContinueDeal.MISS -> playerDealing(participant, blackjack)
        ContinueDeal.NO -> participant
    }
}
