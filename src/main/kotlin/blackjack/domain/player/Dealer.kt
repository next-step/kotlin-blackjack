package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Score

class Dealer(profile: Profile, cards: Cards = Cards.EMPTY) : BlackJackPlayer(profile, cards) {

    override fun receiveCard(card: Card): Player {
        if (!canReceiveCard()) {
            return this
        }
        return Dealer(profile, cards.addCards(card))
    }

    override fun turnOff(): Player {
        return Dealer(profile.turnOff(), cards)
    }

    override fun turnOn(): Player {
        return Dealer(profile.turnOn(), cards)
    }

    override fun canReceiveCard(): Boolean {
        return getHighestPoint() <= CAN_ACHIEVE_POINT
    }

    override fun judge(gamers: List<Player>): Map<Player, List<Score>> {
        val result = mutableMapOf<Player, List<Score>>()
        if (getHighestPoint() > MAX_SCORE) {
            return setGamerWin(gamers, result)
        }
        return judgeGamerScore(gamers, result)
    }

    private fun judgeGamerScore(
        gamers: List<Player>,
        result: MutableMap<Player, List<Score>>,
    ): MutableMap<Player, List<Score>> {
        require(gamers.isNotEmpty())
        val dealerPoint = MAX_SCORE - getHighestPoint()

        gamers.forEach {
            putIfOverMaxScore(it, result)
            putIfGamerWin(dealerPoint, it, result)
            putIfDealerWin(dealerPoint, it, result)
            putIfDraw(dealerPoint, it, result)
        }
        return result
    }

    private fun setGamerWin(
        gamers: List<Player>,
        result: MutableMap<Player, List<Score>>
    ): MutableMap<Player, List<Score>> {
        for (player in gamers) {
            result[player] = listOf(Score.WIN)
            result[this] = result.getOrDefault(this, emptyList()) + Score.LOSE
        }
        return result
    }

    private fun putIfDraw(
        dealerPoint: Int,
        gamer: Player,
        result: MutableMap<Player, List<Score>>
    ) {
        val gamerPoint = MAX_SCORE - gamer.getHighestPoint()
        if (dealerPoint == gamerPoint) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.DRAW
            result[gamer] = listOf(Score.DRAW)
        }
    }

    private fun putIfDealerWin(
        dealerPoint: Int,
        gamer: Player,
        result: MutableMap<Player, List<Score>>
    ) {
        val gamerPoint = MAX_SCORE - gamer.getHighestPoint()
        if (dealerPoint < gamerPoint) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.WIN
            result[gamer] = listOf(Score.LOSE)
        }
    }

    private fun putIfGamerWin(
        dealerPoint: Int,
        gamer: Player,
        result: MutableMap<Player, List<Score>>
    ) {
        val gamerPoint = MAX_SCORE - gamer.getHighestPoint()
        if (gamerPoint in 1 until dealerPoint) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.LOSE
            result[gamer] = listOf(Score.WIN)
        }
    }

    private fun putIfOverMaxScore(
        gamer: Player,
        result: MutableMap<Player, List<Score>>
    ) {
        if (gamer.getHighestPoint() > MAX_SCORE) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.WIN
            result[gamer] = listOf(Score.LOSE)
        }
    }

    companion object {
        private const val CAN_ACHIEVE_POINT = 16
        private const val MAX_SCORE = 21

        fun of(): Dealer {
            return Dealer(Profile.createDealer())
        }
    }
}
