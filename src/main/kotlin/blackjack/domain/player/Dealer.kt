package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Bettings
import blackjack.domain.game.GameResult
import blackjack.domain.game.Score
import blackjack.domain.game.ScoreResult

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

    fun judge(bettings: Bettings, gamers: List<Player>): GameResult {
        val result = mutableMapOf<Player, List<Score>>()
        if (getHighestPoint() > MAX_SCORE) {
            return setGamerWin(gamers, bettings, result)
        }
        return judgeGamerScore(gamers, bettings, result)
    }

    private fun judgeGamerScore(
        gamers: List<Player>,
        bettings: Bettings,
        result: MutableMap<Player, List<Score>>,
    ): GameResult {
        require(gamers.isNotEmpty())
        val dealerPoint = MAX_SCORE - getHighestPoint()
        var bet = bettings

        gamers.forEach {
            val gamerPoint = MAX_SCORE - it.getHighestPoint()
            bet = putIfGamerOverMaxScore(it, result, bet)
            bet = putIfDealerLose(gamerPoint, dealerPoint, result, it, bet)
            bet = putIfBlackJack(it, bet)
            bet = putIfGamerLose(dealerPoint, gamerPoint, result, it, bet)
            putIfDraw(dealerPoint, it, result)
        }
        return GameResult(ScoreResult(result), bet)
    }

    private fun putIfGamerLose(
        dealerPoint: Int,
        gamerPoint: Int,
        result: MutableMap<Player, List<Score>>,
        it: Player,
        bettings: Bettings
    ): Bettings {
        var bet = bettings
        if (dealerPoint < gamerPoint) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.WIN
            result[it] = listOf(Score.LOSE)
            bet = bet.loseGamer(it)
        }
        return bet
    }

    private fun putIfBlackJack(
        it: Player,
        bettings: Bettings
    ): Bettings {
        var bet = bettings
        if (it.hasBlackJack() && !this.hasBlackJack()) {
            bet = bet.winBlackJack(it)
        }
        return bet
    }

    private fun putIfDealerLose(
        gamerPoint: Int,
        dealerPoint: Int,
        result: MutableMap<Player, List<Score>>,
        it: Player,
        bettings: Bettings
    ): Bettings {
        var bet = bettings
        if (gamerPoint in 1 until dealerPoint) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.LOSE
            result[it] = listOf(Score.WIN)
            bet = bet.winGamer(it)
        }
        return bet
    }

    private fun putIfGamerOverMaxScore(
        it: Player,
        result: MutableMap<Player, List<Score>>,
        bettings: Bettings
    ): Bettings {
        var bet = bettings
        if (it.getHighestPoint() > MAX_SCORE) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.WIN
            result[it] = listOf(Score.LOSE)
            bet = bet.loseGamer(it)
        }
        return bet
    }

    private fun setGamerWin(
        gamers: List<Player>,
        bettings: Bettings,
        result: MutableMap<Player, List<Score>>
    ): GameResult {
        var playersBettings = bettings
        for (player in gamers) {
            result[player] = listOf(Score.WIN)
            result[this] = result.getOrDefault(this, emptyList()) + Score.LOSE
            playersBettings = playersBettings.winGamer(player)
        }
        return GameResult(ScoreResult(result), playersBettings)
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

    companion object {
        private const val CAN_ACHIEVE_POINT = 16
        private const val MAX_SCORE = 21

        fun of(): Dealer {
            return Dealer(Profile.createDealer())
        }
    }
}
