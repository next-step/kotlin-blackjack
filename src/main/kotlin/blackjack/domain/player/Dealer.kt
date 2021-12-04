package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Bet
import blackjack.domain.game.Credit
import blackjack.domain.game.GameResult
import blackjack.domain.game.Score
import blackjack.domain.game.ScoreResult

class Dealer(profile: Profile, cards: Cards = Cards.EMPTY, credit: Credit = Credit.from(0)) :
    BlackJackPlayer(profile, cards, credit) {

    override fun receiveCard(card: Card): Player {
        if (!canReceiveCard()) {
            return this
        }
        return Dealer(profile, cards.addCards(card), credit)
    }

    override fun turnOff(): Player {
        return Dealer(profile.turnOff(), cards, credit)
    }

    override fun turnOn(): Player {
        return Dealer(profile.turnOn(), cards, credit)
    }

    override fun canReceiveCard(): Boolean {
        return getHighestPoint() <= CAN_ACHIEVE_POINT
    }

    override fun holdBetting(getPlayerBetting: Bet): Player {
        return Dealer(profile, cards)
    }

    override fun winBetting(player: Player): Player {
        return Dealer(profile, cards, credit.receiveCredit(player.getPlayerCredit()))
    }

    override fun winBetting(): Player {
        return Dealer(profile, cards, credit)
    }

    override fun loseBetting(): Player {
        return Dealer(profile, cards, credit.minus(credit))
    }

    override fun loseBetting(player: Player): Player {
        return Dealer(profile, cards, credit.minus(player.getPlayerCredit()))
    }

    override fun winBlackJack(): Player {
        return Dealer(profile, cards, credit.receiveBlackJackCredit())
    }

    override fun loseBlackJack(player: Player): Player {
        return Dealer(profile, cards, credit.subtractBlackJack(player.getPlayerCredit()))
    }

    override fun minusCredit(credit: Credit): Player {
        return Dealer(profile, cards, credit.minus(credit))
    }

    override fun receiveCredit(credit: Credit): Player {
        return Dealer(profile, cards, credit.receiveCredit(credit))
    }

    fun judge(gamers: List<Player>): GameResult {
        val result = mutableMapOf<Player, List<Score>>()
        if (getHighestPoint() > MAX_SCORE) {
            return setGamerWin(gamers, result)
        }
        return judgeGamerScore(gamers, result)
    }

    private fun judgeGamerScore(
        gamers: List<Player>,
        result: MutableMap<Player, List<Score>>,
    ): GameResult {
        require(gamers.isNotEmpty())
        val dealerPoint = MAX_SCORE - getHighestPoint()
        var bettingResult = listOf<Player>(this)

        gamers.forEach {
            val gamerPoint = MAX_SCORE - it.getHighestPoint()
            bettingResult = putIfGamerOverMaxScore(it, result, bettingResult)
            bettingResult = putIfDealerLose(gamerPoint, dealerPoint, result, it, bettingResult)
            bettingResult = putIfBlackJack(it, bettingResult)
            bettingResult = putIfGamerLose(dealerPoint, gamerPoint, result, it, bettingResult)
            bettingResult = putIfDraw(dealerPoint, it, result, bettingResult)
        }
        return GameResult(ScoreResult(result), Players.from(bettingResult))
    }

    private fun putIfGamerLose(
        dealerPoint: Int,
        gamerPoint: Int,
        result: MutableMap<Player, List<Score>>,
        it: Player,
        bettingResult: List<Player>
    ): List<Player> {
        var betting = bettingResult
        if (dealerPoint < gamerPoint) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.WIN
            result[it] = listOf(Score.LOSE)
            betting = betting + it.loseBetting()
            betting = updateDealerWin(betting, it)
        }
        return betting
    }

    private fun putIfBlackJack(
        it: Player,
        bettingResult: List<Player>
    ): List<Player> {
        var betting = bettingResult
        if (it.hasBlackJack() && !this.hasBlackJack()) {
            betting = betting + it.winBlackJack()
            betting = updateDealerBlackJackLose(betting, it)
        }
        return betting
    }

    private fun putIfDealerLose(
        gamerPoint: Int,
        dealerPoint: Int,
        scoreResult: MutableMap<Player, List<Score>>,
        it: Player,
        bettingResult: List<Player>
    ): List<Player> {
        var betting = bettingResult
        if (gamerPoint in 1 until dealerPoint) {
            scoreResult[this] = scoreResult.getOrDefault(this, emptyList()) + Score.LOSE
            scoreResult[it] = listOf(Score.WIN)
            betting = betting + it.winBetting()
            betting = updateDealerLose(betting, it)
        }
        return betting
    }

    private fun putIfGamerOverMaxScore(
        gamer: Player,
        scoreResult: MutableMap<Player, List<Score>>,
        bettingResult: List<Player>
    ): List<Player> {
        var betting = bettingResult
        if (gamer.getHighestPoint() > MAX_SCORE) {
            scoreResult[this] = scoreResult.getOrDefault(this, emptyList()) + Score.WIN
            scoreResult[gamer] = listOf(Score.LOSE)
            betting = betting + gamer.loseBetting()
            betting = updateDealerWin(betting, gamer)
        }
        return betting
    }

    private fun updateDealerWin(players: List<Player>, gamer: Player): List<Player> {
        val playersList = players.toMutableList()
        playersList.find { it is Dealer }?.let { it ->
            playersList.remove(it)
            playersList.add(it.winBetting(gamer))
            return playersList.toList()
        } ?: return playersList + this.winBetting(gamer)
    }

    private fun updateDealerLose(players: List<Player>, gamer: Player): List<Player> {
        val playersList = players.toMutableList()
        playersList.find { it is Dealer }?.let { it ->
            playersList.remove(it)
            playersList.add(it.loseBetting(gamer))
            return playersList.toList()
        } ?: return playersList + this.loseBetting(gamer)
    }

    private fun updateDealerBlackJackLose(players: List<Player>, gamer: Player): List<Player> {
        val playersList = players.toMutableList()
        playersList.find { it is Dealer }?.let { it ->
            playersList.remove(it)
            playersList.add(it.loseBlackJack(gamer))
            return playersList.toList()
        } ?: return playersList + this.loseBetting(gamer)
    }

    private fun setGamerWin(
        gamers: List<Player>,
        result: MutableMap<Player, List<Score>>
    ): GameResult {
        val players = mutableListOf<Player>()
        for (player in gamers) {
            result[player] = listOf(Score.WIN)
            result[this] = result.getOrDefault(this, emptyList()) + Score.LOSE

            updateGamerWin(players, player)
            updateDealerLose(players, player)
        }
        return GameResult(ScoreResult(result), Players.from(players))
    }

    private fun updateGamerWin(
        players: MutableList<Player>,
        player: Player
    ) {
        players.add(player.winBetting())
    }

    private fun updateDealerLose(players: MutableList<Player>, player: Player) {
        players.find { it is Dealer }?.let { it ->
            players.remove(it)
            players.add(it.loseBetting(player))
        } ?: players.add(this.loseBetting(player))
    }

    private fun putIfDraw(
        dealerPoint: Int,
        gamer: Player,
        result: MutableMap<Player, List<Score>>,
        resultList: List<Player>
    ): List<Player> {
        val gamerPoint = MAX_SCORE - gamer.getHighestPoint()
        if (dealerPoint == gamerPoint) {
            result[this] = result.getOrDefault(this, emptyList()) + Score.DRAW
            result[gamer] = listOf(Score.DRAW)
            return resultList + gamer
        }
        return resultList
    }

    companion object {
        private const val CAN_ACHIEVE_POINT = 16
        private const val MAX_SCORE = 21

        fun of(): Dealer {
            return Dealer(Profile.createDealer())
        }
    }
}
