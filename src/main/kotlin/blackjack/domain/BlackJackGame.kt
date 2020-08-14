package blackjack.domain

class BlackJackGame(
    private val deck: DrawStrategy
) {
    fun dealWith(players: List<String>): List<Player> {
        return players.map { Challenger(it) }
            .map { it.deal(deck) }
    }

    tailrec fun play(
        players: List<Player>,
        acc: List<Player> = emptyList(),
        isAgreedHit: (Player) -> Boolean
    ): List<Player> {
        if (players.isEmpty()) return acc
        val player = askHit(players.first()) { isAgreedHit(it) }
        if (player.state == State.Playing) {
            return play(player + players.drop(1), acc, isAgreedHit)
        }
        return play(players.drop(1), acc + player, isAgreedHit)
    }
    // 이걸 안드로이드에 포팅한다면.. 재귀를 쓸수있게 되는걸까? 콘솔에 의존적이게
    // 된게 아닌가 하는 생각이 문득 들었다.

    private fun askHit(player: Player, isAgreedHit: (Player) -> Boolean): Player {
        if (isAgreedHit(player)) return player.hit(deck)
        return player.stand()
    }
}
