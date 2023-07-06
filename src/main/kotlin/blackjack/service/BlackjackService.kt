package blackjack.service

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Score.Companion.BLACK_JACK_SCORE
import blackjack.domain.enums.Condition
import blackjack.domain.enums.MatchResult
import blackjack.dto.BlackjackGameMoneyResult
import blackjack.dto.BlackjackGameResult
import blackjack.dto.PlayerInfo
import blackjack.view.InputView

class BlackjackService {

    fun initBlackjackGame(players: List<PlayerInfo>): BlackjackGame {
        val deck = Deck()
        val dealer = Dealer(deck = deck, cards = deck.drawCard(BASIC_CARD_COUNT))
        dealer.checkBlackjack()
        val blackJackPlayers = players.map { player ->
            val cards = dealer.draw(BASIC_CARD_COUNT)
            Player(name = player.name, cards = cards, betAmount = player.betAmount)
        }
        blackJackPlayers.forEach { it.checkBlackjack() }
        return BlackjackGame(blackJackPlayers, dealer)
    }

    fun raceBlackjack(player: Player, blackjackGame: BlackjackGame, answer: String) {

        if (answer == InputView.go && player.currentCondition() == Condition.PLAY) {
            val card = blackjackGame.dealer.draw(ONE_MORE_CARD_COUNT).pick()
            player.hit(card)
            checkCondition(player)
        } else if (answer == InputView.stop) {
            player.changeCondition(Condition.STAY)
        }
    }

    fun resultBlackjackGameMoney(players: List<Player>, dealer: Dealer): List<BlackjackGameMoneyResult> {
        val result = mutableListOf<BlackjackGameMoneyResult>()

        players.forEach { player ->
            when (dealer.determineResult(player)) {
                MatchResult.WIN -> result.add(BlackjackGameMoneyResult(player.name, -player.betAmount))
                MatchResult.LOSE -> result.add(BlackjackGameMoneyResult(player.name, player.betAmount))
                MatchResult.DRAW -> result.add(BlackjackGameMoneyResult(player.name, 0.0))
            }
        }
        return result
    }

    fun resultBlackjackGame(players: List<Player>, dealer: Dealer): List<BlackjackGameResult> {
        val result = mutableListOf<BlackjackGameResult>()
        var (dealerWinCount, dealerDrawCount, dealerLoseCount) = listOf(0, 0, 0)

        players.forEach { player ->
            val resultMatch = dealer.determineResult(player)
            addGameResult(result, player.name, resultMatch)
            when (resultMatch) {
                MatchResult.WIN -> dealerLoseCount++
                MatchResult.LOSE -> dealerWinCount++
                MatchResult.DRAW -> dealerDrawCount++
            }
        }
        result.add(0, BlackjackGameResult(name = dealer.name, win = "${dealerWinCount}${MatchResult.WIN.match}", draw = "${dealerDrawCount}${MatchResult.DRAW.match}", lose = "${dealerLoseCount}${MatchResult.LOSE.match}"))
        return result
    }

    fun checkAllPlayersBlackjack(blackjackGame: BlackjackGame): Boolean {
        var blackjackFlag = false
        val blackjackPlayers = blackjackGame.players.filter { it.condition == Condition.BLACKJACK }

        if (blackjackPlayers.isNotEmpty()) {
            blackjackGame.players.filter { it.condition != Condition.BLACKJACK }.forEach { player -> player.loseAllBets() }
            if (blackjackGame.dealer.condition != Condition.BLACKJACK) {
                blackjackPlayers.forEach { player -> player.blackjack() }
            }
            blackjackFlag = true
        }
        return blackjackFlag
    }

    private fun addGameResult(
        result: MutableList<BlackjackGameResult>,
        playerName: String,
        resultMatch: MatchResult
    ) {
        when (resultMatch) {
            MatchResult.WIN -> result.add(BlackjackGameResult(name = playerName, win = resultMatch.match))
            MatchResult.LOSE -> result.add(BlackjackGameResult(name = playerName, lose = resultMatch.match))
            MatchResult.DRAW -> result.add(BlackjackGameResult(name = playerName, draw = resultMatch.match))
        }
    }

    fun raceDealer(dealer: Dealer) {
        val card = dealer.draw(Dealer.ONE_DRAW_COUNT).pick()
        dealer.hit(card)
    }

    private fun checkCondition(player: Player) {
        if (player.cards.calculateScore() == BLACK_JACK_SCORE) {
            player.changeCondition(Condition.BLACKJACK)
        } else if (player.cards.calculateScore() > BLACK_JACK_SCORE) {
            player.changeCondition(Condition.BUST)
            player.loseAllBets()
        }
    }

    companion object {
        const val BASIC_CARD_COUNT = 2
        private const val ONE_MORE_CARD_COUNT = 1
    }
}
