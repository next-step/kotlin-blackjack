package blackjack.domain

class BlackJackTable {

    private val blackJackCards: BlackCardDeck = CardType.getCardDeck(CardNumber.values())

    fun hitCard(): Card {
        return blackJackCards.hitCard()
    }

    fun startGame(players: Players, gameConditionNotify: GameConditionNotify) {
        players.giveDefaultCards()

        gameConditionNotify.giveDefaultCardsToPlayerDone(players)

        players.giveMoreCard(gameConditionNotify)

        players.judgeGameResult()

        gameConditionNotify.finishBlackJackGame(players)
    }
}
