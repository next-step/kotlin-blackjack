package blackjack.domain

interface GameConditionNotify {
    fun giveDefaultCardsToPlayerDone(players: Players)
    fun isNeedMoreCard(player: Player): Boolean
    fun giveCardToPlayerDone(player: Player)
    fun finishBlackJackGame(players: Players)
}
