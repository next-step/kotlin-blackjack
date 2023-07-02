package blackjack.domain

import blackjack.domain.card.BlackCardDeck
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJackTable(val players: MutableList<Player>, val blackJackCardDeck: BlackCardDeck) {

    fun startGame(gameConditionNotify: GameConditionNotify) {

        val blackJackPlayers = Players(
            players,
            object : CardDeck {
                override fun getCard(): Card {
                    return blackJackCardDeck.hitCard()
                }
            }
        )
        gameConditionNotify.giveDefaultCardsToPlayerDone(blackJackPlayers)

        blackJackPlayers.giveMoreCard(gameConditionNotify)

        blackJackPlayers.judgeGameResult()

        gameConditionNotify.finishBlackJackGame(blackJackPlayers)
    }
}
