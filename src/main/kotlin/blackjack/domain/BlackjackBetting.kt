package blackjack.domain

import blackjack.BlackjackBettingDto
import blackjack.domain.player.UserRole

class BlackjackBetting(private val users: List<UserRole>) {

    fun getGameResult(): List<BlackjackBettingDto> {

        if (users.count { it.isBlackjack() } == users.size) {
            return allBlackjack()
        }

        val dealer = users.first { it.isDealer() }
        val players: List<BlackjackBettingDto> = mutableListOf()
        val bettingFinishedPlayers = bettingPlayers(players).toMutableList()
        val dealerDto = bettingDealer(dealer, bettingFinishedPlayers)

        bettingFinishedPlayers.add(dealerDto)
        return bettingFinishedPlayers.toList()
    }

    private fun allBlackjack(): List<BlackjackBettingDto> =
        users.map { BlackjackBettingDto(it.userSetting.name, 0, it.cards, it.getScore()) }.toList()

    private fun bettingDealer(dealer: UserRole, bettingFinishedPlayers: MutableList<BlackjackBettingDto>) =
        BlackjackBettingDto(
            dealer.userSetting.name,
            getRewardForDealer(bettingFinishedPlayers),
            dealer.cards,
            dealer.getScore()
        )

    private fun bettingPlayers(players: List<BlackjackBettingDto>) = players.asSequence()
        .plus(getWinners())
        .plus(getWinnersWithBlackjack())
        .plus(getLooser())
        .toList()

    private fun getRewardForDealer(users: List<BlackjackBettingDto>): Int {
        return users.sumOf { -it.money }
    }

    private fun getLooser(): List<BlackjackBettingDto> =
        users
            .filter { !it.isDealer() && !it.isWinner() }
            .map { BlackjackBettingDto(it.userSetting.name, -it.getBettingMoney(), it.cards, it.getScore()) }
            .toList()

    private fun getWinners(): List<BlackjackBettingDto> =
        users
            .filter { !it.isDealer() && it.isWinner() && !it.isBlackjack() }
            .map { BlackjackBettingDto(it.userSetting.name, it.getBettingMoney(), it.cards, it.getScore()) }
            .toList()

    private fun getWinnersWithBlackjack(): List<BlackjackBettingDto> {
        return users
            .filter { !it.isDealer() && it.isBlackjack() && it.isWinner() }
            .map {
                BlackjackBettingDto(
                    it.userSetting.name,
                    (it.getBettingMoney() * 1.5).toInt(),
                    it.cards,
                    it.getScore()
                )
            }
            .toList()
    }
}
