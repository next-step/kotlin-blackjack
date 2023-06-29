package blackjack.domain

class BlackJackTable {

    fun startGame(players: Players, gameConditionNotify: GameConditionNotify) {

        gameConditionNotify.giveDefaultCardsToPlayerDone(players)

        players.giveMoreCard(gameConditionNotify)

        players.judgeGameResult()

        gameConditionNotify.finishBlackJackGame(players)
    }
}
