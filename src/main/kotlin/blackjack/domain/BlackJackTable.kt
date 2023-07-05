package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNotify
import blackjack.domain.player.Players

class BlackJackTable private constructor(private val players: List<Player>) {

    fun startGame(gameConditionNotify: GameConditionNotify) {

        val cardDeck = CardType.getCardDeck(CardNumber.values())
        val blackJackPlayers = Players(players, cardDeck)

        gameConditionNotify.giveDefaultCardsToPlayerDone(blackJackPlayers)

        blackJackPlayers.giveMoreCard(gameConditionNotify)

        blackJackPlayers.judgeGameResult()

        gameConditionNotify.finishBlackJackGame(blackJackPlayers)
    }

    companion object {
        fun of(playerNotify: PlayerNotify): BlackJackTable {
            val players = playerNotify.generatePlayers()
            return BlackJackTable(players)
        }
    }
}
