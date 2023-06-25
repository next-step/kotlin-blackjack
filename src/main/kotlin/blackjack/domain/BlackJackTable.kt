package blackjack.domain

import blackjack.view.GameConditionNotify

class BlackJackTable {

    private val blackJackCards: Cards = CardType.getCardDeck(CardNumber.values())

    fun hitCard(): Card {
        return blackJackCards.getCard()
    }

    fun startGame(players: Players, gameConditionNotify: GameConditionNotify) {
        players.giveDefaultCards()

        gameConditionNotify.showPlayerCardSet(players)

        players.giveMoreCard(gameConditionNotify)

        players.judgeGameResult()

        gameConditionNotify.showGameResult(players)
    }
}
