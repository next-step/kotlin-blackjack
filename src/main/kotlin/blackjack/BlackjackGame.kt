package blackjack

import blackjack.dto.PlayersDto

class BlackjackGame(private val gamers: List<UserRole>) {

    init {
        validate(gamers.size)

        repeat(BASIC_RULE_COUNT) {
            gamers.forEach {
                it.draw(cards.removeAt(TOP_CARD))
            }
        }

        val playersDto = PlayersDto(gamers)
        playersDto.showPlayerNames()
        playersDto.showInitCards()
    }

    fun play(): List<UserRole> {
        val players = gamers.filter { !it.isDealer() }
        val results = mutableListOf<UserRole>()
        for (player in players) {
            results.add(playPlayerTurn(player))
        }

        results.add(playDealerTurn())
        return results.toList()
    }

    private fun validate(playerCount: Int) {
        require(BLACKJACK_PLAY_MIN_PLAYER_COUNT > playerCount) { "플레이어가 너무 많습니다" }
    }

    private fun playPlayerTurn(player: UserRole): UserRole {
        var p = player
        while (!p.isFinish()) {
            p = deal(p) as Player
        }
        return p
    }

    private fun playDealerTurn(): UserRole {
        var dealer = gamers.first { it.isDealer() }
        while (!dealer.isFinish()) {
            dealer = dealer.draw(cards.removeAt(TOP_CARD))
        }
        return dealer
    }

    private fun deal(player: UserRole): UserRole {
        println("%s 님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".format(player.name))
        return when (readln()) {
            "y" -> player.draw(cards.removeAt(TOP_CARD))
            else -> player.stand()
        }
    }

    companion object {
        private const val BASIC_RULE_COUNT = 2
        private const val BLACKJACK_CARD_COUNT = 52
        private const val BLACKJACK_PLAY_MIN_PLAYER_COUNT = BLACKJACK_CARD_COUNT / BASIC_RULE_COUNT
        const val TOP_CARD = 0
    }
}
