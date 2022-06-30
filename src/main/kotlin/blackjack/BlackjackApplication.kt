package blackjack

import blackjack.dto.CandidateGameResultsDto
import blackjack.model.Game
import blackjack.model.candidate.Candidate
import blackjack.model.candidate.Candidates
import blackjack.model.candidate.Dealer
import blackjack.model.candidate.Player
import blackjack.view.InputView
import blackjack.view.MoreCardMark
import blackjack.view.ResultView

object BlackjackApplication {
    fun run(inputView: InputView, resultView: ResultView) {
        val game = startGame(inputView)
        resultView.printCandidatesCardStatus(game.candidates)

        playGame(game, inputView, resultView)
        resultView.printCardGameResult(CandidateGameResultsDto.from(game.candidates))
    }

    private fun startGame(inputView: InputView): Game {
        inputView.printPlayerNamesInputMessage()
        val players = inputView.inputPlayerNames()
            .map { playerName ->
                inputView.printPlayerBettingAmountInputMessage(playerName)
                Player.from(playerName, inputView.inputPlayerBettingAmount())
            }

        val candidates = Candidates(players + Dealer())
        val game = Game(candidates = candidates)

        game.start()

        return game
    }

    private fun playGame(game: Game, inputView: InputView, resultView: ResultView) {
        while (game.isNotEnd()) {

            val needMoreCardMark = printAndInputWhetherNeedMoreCard(inputView, resultView, game.turnCandidate)
            if (MoreCardMark.needMoreCard(needMoreCardMark)) {
                game.provideCardToTurnPlayer()
                resultView.printCandidateCardStatus(game.turnCandidate)
                continue
            }

            game.changeTurn()
        }
    }

    private fun printAndInputWhetherNeedMoreCard(
        inputView: InputView,
        resultView: ResultView,
        turnCandidate: Candidate
    ): String {
        if (turnCandidate.isDealer && turnCandidate.needMoreCard) {
            resultView.printDealerReceiveMoreCardMessage(turnCandidate.candidateName)
            return MoreCardMark.YES.mark
        }

        inputView.printNeedMoreCardAskMessage(turnCandidate.candidateName)
        return inputView.inputWhetherNeedMoreCard()
    }
}
