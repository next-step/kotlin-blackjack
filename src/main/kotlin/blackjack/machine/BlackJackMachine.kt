package blackjack.machine

import blackjack.card.Card
import blackjack.deck.Deck
import blackjack.player.Player
import blackjack.player.Players
import blackjack.round.Round
import blackjack.round.RoundResult
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackMachine(
    private val deck: Deck,
) {
    fun play() {
        var players = Players.generateFromNames(playerNames = InputView.inputPlayerNames())
        ResultView.printPlayersName(players = players)
        ResultView.printPlayersCardStatus(players = players)

        var roundResults: List<RoundResult>
        while (true) {
            roundResults = players.players.map { playRoundByPlayer(player = it) }
            players = Players.generateFromRoundResults(roundResults = roundResults)
            ResultView.printPlayersCardStatusAndSum(players = players)

            if (roundResults.stream().allMatch { it is RoundResult.Bust }) {
                return
            }
        }
    }

    private fun playRoundByPlayer(player: Player): RoundResult {
        return when {
            !player.isHitCard() -> RoundResult.Bust(bustedPlayer = player)
            !InputView.isHitCard(player) ->
                RoundResult.Success(
                    player
                        .also { ResultView.printPlayerCard(player = player) },
                )

            else -> {
                var roundResult: RoundResult = RoundResult.AlreadyDrawnCard(attemptedPlayer = player)
                while (roundResult is RoundResult.AlreadyDrawnCard) {
                    roundResult = Round.run(player = player, card = Card.random(), deck = deck)
                }

                return roundResult
            }
        }
    }

    companion object {
        const val BLACKJACK = 21
    }
}
