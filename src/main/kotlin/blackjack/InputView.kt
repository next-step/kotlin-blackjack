package blackjack

object InputView {
    fun enterPlayers(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readln().split(",").map { Player(it.trim()) }

        setPlayerBetAmount(players)
        return players
    }

    private fun setPlayerBetAmount(players: List<Player>) {
        for (player in players) {
            println("${player.name}의 배팅 금액은?")
            val bet = BetAmount(readln().toInt())
            player.betAmount = bet
        }
    }

    fun isHitCalled(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().trim() == "y"
    }
}
