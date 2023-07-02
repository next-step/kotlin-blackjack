package blackjack.domain

import blackjack.domain.player.Players

class BlackJackTable {

    fun startGame(players: Players, gameConditionNotify: GameConditionNotify) {

        gameConditionNotify.giveDefaultCardsToPlayerDone(players)

        players.giveMoreCard(gameConditionNotify)

        players.judgeGameResult()

        gameConditionNotify.finishBlackJackGame(players)
    }
}
